package com.example.chuchu.member.service;

import com.example.chuchu.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    public void sendMail(Member member) throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, member.getEmail()); // 보내는 대상
        message.setSubject("chu-chu 회원가입 이메일 인증"); // 제목

        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3>회원가입 인증 링크를 클릭하세요.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "<a href=http://localhost/member/checkEmail?email=" + member.getEmail() + "&code=" + member.getEmailCode();
        msg += ">이메일 인증하기</a><div><br/> ";
        msg += "</div>";

        message.setText(msg, "UTF-8", "html");// 내용, charset 타입, subtype
        message.setFrom(new InternetAddress("", "ChuChu_Admin")); // 보내는 사람

        javaMailSender.send(message); // 메일 발송
    }

}
