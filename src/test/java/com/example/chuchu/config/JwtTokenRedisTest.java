package com.example.chuchu.config;

import com.example.chuchu.common.jwt.JwtTokenProvider;
import com.example.chuchu.common.utils.RedisUtils;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.entity.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JwtTokenRedisTest {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void jwt_토큰발급_redis_저장_테스트() {
        // given
        Member member = Member.builder().email("aaa@naver.com")
                .password("Qwe123!@#").nickName("aaa").userRole(UserRole.ROLE_USER).build();

        String refreshToken = jwtTokenProvider.generateRefreshToken(member);

        redisUtils.setData(refreshToken, member.getEmail());

        // when
        String email = redisUtils.getData(refreshToken);

        // then
        assertThat(email).isEqualTo("aaa@naver.com");
    }

}
