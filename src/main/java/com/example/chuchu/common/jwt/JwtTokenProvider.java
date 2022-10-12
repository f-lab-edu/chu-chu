package com.example.chuchu.common.jwt;

import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.entity.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenProvider {

    //todo secretKey 변경
    private final String ACCESS_TOKEN_KEY = "Thi$i$JwtP@s!!sw0rd0pEn$e$ame!!~";
    private final String REFRESH_TOKEN_KEY = "Thi$i$JwtP@s!!sw0rd0pEn$e$ame!!!";

    private final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 5;
    private final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;

    private Key createSigningKey(String signKey) {
        byte[] keyBytes = signKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateAccessToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(createExpireDate(ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(createSigningKey(ACCESS_TOKEN_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(createExpireDate(REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(createSigningKey(REFRESH_TOKEN_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = getClaimsFormToken(token).getBody();
            log.info("expireTime :" + claims.getExpiration());
            log.info("email :" + claims.get("email"));
            log.info("role :" + claims.get("role"));
            return true;

        } catch (ExpiredJwtException exception) {
            log.error("Token Expired");
            return false;
        } catch (JwtException exception) {
            log.error("Token Tampered");
            return false;
        } catch (NullPointerException exception) {
            log.error("Token is null");
            return false;
        }
    }

    public String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private Date createExpireDate(long expireTime) {
        // 토큰 만료시간 설정
        long curTime = System.currentTimeMillis();
        return new Date(curTime + expireTime);
    }

    private Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private Map<String, Object> createClaims(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", member.getEmail());
        claims.put("role", member.getUserRole());

        return claims;
    }

    private Jws<Claims> getClaimsFormToken(String token) {
        return Jwts.parserBuilder().setSigningKey(createSigningKey(ACCESS_TOKEN_KEY))
                .build().parseClaimsJws(token);
    }

    public String getUserEmailFromToken(String token) {
        return (String) getClaimsFormToken(token).getBody().get("email");
    }

    public UserRole getRoleFromToken(String token) {
        return (UserRole) getClaimsFormToken(token).getBody().get("role");
    }

}
