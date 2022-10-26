package com.example.chuchu.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailCheckDTO {

    private String email;
    private String code;

    @Builder
    public EmailCheckDTO(String email, String code) {
        this.email = email;
        this.code = code;
    }
}
