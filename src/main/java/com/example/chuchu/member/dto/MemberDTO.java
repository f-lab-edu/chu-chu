package com.example.chuchu.member.dto;

import com.example.chuchu.member.entity.Level;
import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.entity.UserRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDTO {
    private Long id;
    private String email;
    private String nickName;
    private Level level;
    private UserRole userRole;

    public MemberDTO(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickName = member.getNickName();
        this.level = member.getLevel();
        this.userRole = member.getUserRole();
    }
}
