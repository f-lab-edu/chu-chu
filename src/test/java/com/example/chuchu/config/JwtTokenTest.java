package com.example.chuchu.config;

import com.example.chuchu.common.jwt.JwtTokenProvider;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTokenTest {

    @Test
    public void Jwt_test() {

        // Member
        Member member = Member.builder().email("aaa@naver.com")
                .password("Qwe123!@#").nickName("aaa").userRole(UserRole.ROLE_USER).build();

        String token = JwtTokenProvider.generateJwtToken(member);

        String email = JwtTokenProvider.getUserEmailFromToken(token);
        System.out.println(email);
    }

}
