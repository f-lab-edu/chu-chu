package com.example.chuchu.member.entity;

import com.example.chuchu.common.global.BaseTimeEntity;
import com.example.chuchu.member.dto.MemberUpdateDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor
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

    @Column(name = "email_code")
    private String emailCode;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Builder
    public Member(Long id, String email, String nickName, String password, Level level, UserRole userRole) {
        this.id = id;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.level = level;
        this.userRole = userRole;
        this.emailCode = UUID.randomUUID().toString();
        this.emailVerified = false;
        this.level = Level.BRONZE;
        this.userRole = UserRole.ROLE_USER;
    }

    /**
     * 패스워드 암호화
     *
     * @param passwordEncoder 암호화 할 인코더 클래스
     * @return 변경된 유저 Entity
     */
    public Member hashPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }

    public boolean isValidEmailCode(String code) {
        return this.emailCode.equals(code);
    }

    public void completeSignUp() {
        this.emailVerified = true;
    }

    public Member updateMember(MemberUpdateDTO memberUpdateDTO, PasswordEncoder passwordEncoder) {
        this.nickName = memberUpdateDTO.getNickName();
        this.password = passwordEncoder.encode(memberUpdateDTO.getPassword());
        return this;
    }
}
