package com.example.chuchu.member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDTO {
    private String nickName;
    private String password;

    @Builder
    public MemberUpdateDTO(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
    }
}
