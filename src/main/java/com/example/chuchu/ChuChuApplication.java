package com.example.chuchu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ChuChuApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChuChuApplication.class, args);
    }

}
