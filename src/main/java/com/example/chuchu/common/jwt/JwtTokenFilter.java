package com.example.chuchu.common.jwt;

import com.example.chuchu.common.config.security.CustomUserDetailsService;
import com.example.chuchu.common.jwt.repository.LogoutTokenRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;
    private final LogoutTokenRedisRepository logoutAccessTokenRedisRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String accessToken = getToken(request);
        if (!StringUtils.isEmpty(accessToken)) {
            checkLogout(accessToken);
            String username = jwtTokenProvider.getUsername(accessToken);
            if (username != null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                validateAccessToken(accessToken, userDetails);
                successfulAuthentication(request, userDetails);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");
        if (!StringUtils.isEmpty(headerAuth) && StringUtils.startsWith(headerAuth, "Bearer ")) {
            return StringUtils.substring(headerAuth, 7);
        }
        return "";
    }

    private void checkLogout(String accessToken) {
        if (logoutAccessTokenRedisRepository.existsById(accessToken)) {
            throw new IllegalArgumentException("?????? ??????????????? ???????????????.");
        }
    }

    /**
     * access ?????? ??????
     */
    private void validateAccessToken(String accessToken, UserDetails userDetails) {
        if (!jwtTokenProvider.validateToken(accessToken, userDetails)) {
            throw new IllegalArgumentException("?????? ?????? ??????");
        }
    }

    /**
     * SecurityContext??? ?????? ???????????? ????????? ?????? ??????
     */
    private void successfulAuthentication(HttpServletRequest request, UserDetails userDetails) {
        // ?????? ?????????????????? race condition??? ???????????? ?????? ????????? SecurityContext ??? ???????????? ?????? ??????.
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authenticationToken);

        SecurityContextHolder.setContext(context);
    }
}
