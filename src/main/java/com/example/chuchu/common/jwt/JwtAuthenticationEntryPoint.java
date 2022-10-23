package com.example.chuchu.common.jwt;


import com.example.chuchu.common.jwt.dto.AuthError;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증이 되지 않은 유저가 요청을 보냈을 때 동작하는 인터페이스
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        log.error("UnAuthorized message : {}", authException.getMessage());
        sendError(response, authException.getMessage());
    }

    private void sendError(HttpServletResponse response, String message) throws IOException {
        AuthError authError = AuthError.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(message)
                .result(false)
                .build();

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(response.getWriter(), authError);
    }

}
