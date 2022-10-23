package com.example.chuchu.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginDTO {
    private String email;
    private String password;

    @Builder
    public LoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
