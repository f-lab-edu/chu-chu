package com.example.chuchu.member.service;

import com.example.chuchu.common.errors.exception.UserNotFoundException;
import com.example.chuchu.common.jwt.JwtTokenProvider;
import com.example.chuchu.common.jwt.dto.TokenResponseDTO;
import com.example.chuchu.member.dto.LoginDTO;
import com.example.chuchu.member.dto.MemberDTO;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.mapper.MemberMapper;
import com.example.chuchu.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
/*
    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Can't find User"));
    }

    @Transactional(readOnly = true)
    public TokenResponseDTO login(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = loginDTO.toAuthentication();
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //jwtTokenProvider.generateAccessToken();
        return new TokenResponseDTO("", "");
    }

    @Transactional
    public Member join(MemberDTO memberDto) {
        Member member = MemberMapper.INSTANCE.toEntity(memberDto);





        return memberRepository.save(member);
    }*/

}
