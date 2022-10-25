package com.example.chuchu.member.service;

import com.example.chuchu.common.errors.exception.DuplicateResourceException;
import com.example.chuchu.common.errors.exception.NotFoundException;
import com.example.chuchu.common.errors.exception.UserNotFoundException;
import com.example.chuchu.common.jwt.JwtTokenProvider;
import com.example.chuchu.common.jwt.dto.TokenResponseDTO;
import com.example.chuchu.common.jwt.entity.LogoutToken;
import com.example.chuchu.common.jwt.entity.RefreshToken;
import com.example.chuchu.common.jwt.repository.LogoutTokenRedisRepository;
import com.example.chuchu.common.jwt.repository.RefreshTokenRedisRepository;
import com.example.chuchu.member.dto.EmailCheckDTO;
import com.example.chuchu.member.dto.JoinDTO;
import com.example.chuchu.member.dto.LoginDTO;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.mapper.JoinMapper;
import com.example.chuchu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.example.chuchu.common.jwt.JwtTokenExpireEnum.REFRESH_TOKEN_EXPIRE_TIME;
import static com.example.chuchu.common.jwt.JwtTokenExpireEnum.REISSUE_EXPIRATION_TIME;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JoinMapper joinMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final LogoutTokenRedisRepository logoutTokenRedisRepository;
    private final MailService mailService;

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Can't find User"));
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    /**
     * 리프레시 토큰 발급 후 redis에 저장
     *
     * @param username 계정 email 주소
     * @return
     */
    private RefreshToken saveRefreshToken(String username) {
        return refreshTokenRedisRepository.save(RefreshToken.createRefreshToken(username,
                jwtTokenProvider.generateRefreshToken(username), REFRESH_TOKEN_EXPIRE_TIME.getValue()));
    }

    @Transactional(readOnly = true)
    public TokenResponseDTO login(LoginDTO loginDTO) {
        String email = loginDTO.getEmail();
        Member member = findByEmail(email);

        if (BooleanUtils.isFalse(member.getEmailVerified())) {
            throw new NotFoundException("이메일 인증이 되지 않았습니다.");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 맞지 않습니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(email);
        RefreshToken refreshToken = saveRefreshToken(email);

        return TokenResponseDTO
                .builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    /**
     * 로그아웃 처리 -> 기존 access token을 더 이상 사용하지 못하도록 redis에 등록
     * redis에서 user 캐시 정보 제거
     *
     * @param tokenResponseDTO
     * @param email
     */
    @CacheEvict(value = "user", key = "#email")
    public void logout(TokenResponseDTO tokenResponseDTO, String email) {
        String accessToken = resolveToken(tokenResponseDTO.getAccessToken());
        long remainMilliSeconds = jwtTokenProvider.getRemainMilliSeconds(accessToken);

        // redis에서 refreshtoken 제거
        refreshTokenRedisRepository.deleteById(email);

        LogoutToken logoutToken = LogoutToken.builder()
                .id(accessToken)
                .username(email)
                .expiration(remainMilliSeconds / 1000)
                .build();

        logoutTokenRedisRepository.save(logoutToken);
    }

    /**
     * 토큰 재발급
     *
     * @param refreshToken 리프레시 토큰
     * @return
     */
    public TokenResponseDTO reissue(String refreshToken) {
        // 헤더
        refreshToken = resolveToken(refreshToken);
        String username = getCurrentUsername();

        // redis에서 리프레시 토큰 가져온다.
        RefreshToken redisRefreshToken = refreshTokenRedisRepository.findById(username)
                .orElseThrow(NoSuchElementException::new);

        // 리프레시 토큰 값이 같은지 확인
        if (refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            return reissueRefreshToken(refreshToken, username);
        }
        throw new IllegalArgumentException("토큰이 일치하지 않습니다.");
    }

    /**
     * refrseh token, access token 재발급 처리
     *
     * @param refreshToken
     * @param username
     * @return
     */
    private TokenResponseDTO reissueRefreshToken(String refreshToken, String username) {

        if (lessThanReissueExpirationTimesLeft(refreshToken)) {
            String accessToken = jwtTokenProvider.generateAccessToken(username);
            return TokenResponseDTO
                    .builder()
                    .accessToken(accessToken)
                    .refreshToken(saveRefreshToken(username).getRefreshToken())
                    .build();
        }

        return TokenResponseDTO
                .builder()
                .accessToken(jwtTokenProvider.generateAccessToken(username))
                .refreshToken(refreshToken)
                .build();
    }

    /**
     * 만료일이 3일 이하이면 Refresh 토큰 재발급 처리
     */
    private boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
        return jwtTokenProvider.getRemainMilliSeconds(refreshToken) < REISSUE_EXPIRATION_TIME.getValue();
    }

    /**
     * 현재 접속한 이메일 주소
     */
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        return principal.getUsername();
    }

    /**
     * 토큰 추출
     *
     * @param token 토큰
     * @return
     */
    private String resolveToken(String token) {
        return StringUtils.substring(token, 7);
    }

    /**
     * 회원 가입
     *
     * @param joinDTO
     * @return
     */
    @Transactional
    public Member join(JoinDTO joinDTO) {
        boolean isExist = existsByEmail(joinDTO.getEmail());

        if (isExist) {
            throw new DuplicateResourceException("이미 존재하는 이메일 입니다.");
        }

        Member member = joinMapper.toEntity(joinDTO);
        member.hashPassword(passwordEncoder);

        // 이메일 전송
        try {
            mailService.sendMail(member);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return memberRepository.save(member);
    }

    @Transactional
    public void completeSignUp(EmailCheckDTO emailCheckDTO) {
        Member member = findByEmail(emailCheckDTO.getEmail());

        if (!member.isValidEmailCode(emailCheckDTO.getCode())) {
            throw new NotFoundException("이메일 인증 코드가 틀립니다.");
        }

        member.completeSignUp();
    }
}
