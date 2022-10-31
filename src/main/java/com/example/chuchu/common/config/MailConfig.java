package com.example.chuchu.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    @Value("${spring.mail.username}")
    private String email;

    @Value("${spring.mail.password}")
    private String password;

    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setUsername(email); // 계정 아이디
        javaMailSender.setPassword(password); // 계정 비밀번호
        javaMailSender.setHost("smtp.gmail.com"); // smtp 서버 주소
        javaMailSender.setPort(587); // 메일 인증서버 포트
        javaMailSender.setJavaMailProperties(getProperties()); // 메일 인증서버 정보

        return javaMailSender;
    }

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true"); // smtp 인증
        properties.setProperty("mail.smtp.starttls.enable", "true"); // smtp strattles 사용

        return properties;
    }
}
