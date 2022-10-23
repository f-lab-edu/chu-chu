package com.example.chuchu.common.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum JwtTokenExpireEnum {

    ACCESS_TOKEN_EXPIRE_TIME(30 * 60 * 1000L), // 30분
    REFRESH_TOKEN_EXPIRE_TIME(7 * 24 * 60 * 60 * 1000L), // 7일
    REISSUE_EXPIRATION_TIME(1000L * 60 * 60 * 24 * 3); // 3일

    private final Long value;

}
