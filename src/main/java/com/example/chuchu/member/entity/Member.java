package com.example.chuchu.member.entity;

import com.example.chuchu.common.global.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false)
    private Level level;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Builder
    public Member(Long id, String email, String nickName, String password, Level level, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.level = level;
        this.userRole = userRole;
    }
}
