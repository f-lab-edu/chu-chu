package com.example.chuchu.member.controller;

import com.example.chuchu.common.jwt.JwtTokenProvider;
import com.example.chuchu.common.jwt.dto.TokenResponseDTO;
import com.example.chuchu.member.dto.JoinDTO;
import com.example.chuchu.member.dto.LoginDTO;
import com.example.chuchu.member.dto.MemberDTO;
import com.example.chuchu.member.mapper.MemberMapper;
import com.example.chuchu.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.example.chuchu.common.global.HttpResponseEntity.ResponseResult;
import static com.example.chuchu.common.global.HttpResponseEntity.success;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/member")
public class MemberController {

    private final MemberService memberService;

    private final MemberMapper memberMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/list")
    public ResponseResult<List<MemberDTO>> getList() {
        return success(memberMapper.toDtoList(memberService.findAll()));
    }

    @PostMapping("/login")
    public ResponseResult<TokenResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return success(memberService.login(loginDTO));
    }

    /**
     * 토큰 재발급
     */
    @PostMapping("/reissue")
    public ResponseResult<TokenResponseDTO> reissue(@RequestHeader("RefreshToken") String refreshToken) {
        return success(memberService.reissue(refreshToken));
    }

    @PostMapping("/join")
    public ResponseResult<MemberDTO> join(@RequestBody @Valid JoinDTO joinRequestDTO) {
        return success(memberMapper.toDto(memberService.join(joinRequestDTO)));
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken,
                       @RequestHeader("RefreshToken") String refreshToken) {
        String username = jwtTokenProvider.getUsername(accessToken.substring(7));
        TokenResponseDTO tokenResponseDTO = TokenResponseDTO.builder()
                .accessToken(accessToken).refreshToken(refreshToken).build();

        memberService.logout(tokenResponseDTO, username);
        log.info("logout: {}", username);
    }

}
