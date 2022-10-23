package com.example.chuchu.common.jwt;

import com.example.chuchu.common.errors.exception.ForbiddenException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서버에 요청 시 액세스 불가능한 권한의 유저가 요청했을 경우 동작하는 핸들러
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        throw new ForbiddenException("Forbidden error");
    }
}
