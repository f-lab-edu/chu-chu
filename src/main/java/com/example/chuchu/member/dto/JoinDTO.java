package com.example.chuchu.member.dto;

import com.example.chuchu.common.validation.annotation.LevelValid;
import com.example.chuchu.common.validation.annotation.Password;
import com.example.chuchu.common.validation.annotation.UserRoleValid;
import com.example.chuchu.member.entity.Level;
import com.example.chuchu.member.entity.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinDTO {
    @Email
    @NotBlank
    private String email;

    @Password
    private String password;

    @Length(min = 4, max = 15)
    @NotBlank
    private String nickName;

    @LevelValid
    private Level level;

    @UserRoleValid
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
