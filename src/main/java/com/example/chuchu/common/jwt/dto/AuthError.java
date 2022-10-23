package com.example.chuchu.common.jwt.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthError {

    private Boolean result;
    private Integer status;
    private String message;

    @Builder
    public AuthError(Boolean result, Integer status, String message) {
        this.result = result;
        this.status = status;
        this.message = message;
    }
}
