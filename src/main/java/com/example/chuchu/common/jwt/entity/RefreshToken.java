package com.example.chuchu.common.jwt.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;

@Getter
@RedisHash("refreshToken")
@NoArgsConstructor
public class RefreshToken {

    @Id
    private String id;

    private String refreshToken;

    @TimeToLive
    private Long expiration;

    @Builder
    public RefreshToken(String id, String refreshToken, Long expiration) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.expiration = expiration;
    }

    public static RefreshToken createRefreshToken(String username, String refreshToken, Long remainingMilliSeconds) {
        return RefreshToken.builder()
                .id(username)
                .refreshToken(refreshToken)
                .expiration(remainingMilliSeconds / 1000)
                .build();
    }
}
