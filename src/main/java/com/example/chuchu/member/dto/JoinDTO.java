package com.example.chuchu.member.dto;

import com.example.chuchu.member.entity.Level;
import com.example.chuchu.member.entity.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinDTO {

    private String email;
    private String password;
    private String nickName;
    private Level level;

    private UserRole userRole;

    @Builder
    public JoinDTO(String email, String password, String nickName, Level level, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.level = level;
        this.userRole = userRole;
    }
}
