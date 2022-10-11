package com.example.chuchu.member.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    @Column(name = "nick_name")
    private String nickName;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Builder
    public Member(Long id, String email, String nickName, String password, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.userRole = userRole;
    }
}
