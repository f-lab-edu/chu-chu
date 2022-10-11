package com.example.chuchu.common.jwt;

import com.example.chuchu.member.entity.Member;
import com.example.chuchu.member.entity.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor
public class JwtTokenProvider {

    //todo secretKey 변경
    private static final String secretKey = "Thi$i$JwtP@s!!sw0rd0pEn$e$ame!!~";
    private static final Key key;

    static {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public static String generateJwtToken(Member member) {
        return Jwts.builder()
                .setSubject(member.getEmail())
                .setHeader(createHeader())
                .setClaims(createClaims(member))
                .setExpiration(createExpireDate())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public static boolean isValidToken(String token) {
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

    public static String getTokenFromHeader(String header) {
        return header.split(" ")[1];
    }

    private static Date createExpireDate() {
        // 토큰 만료시간 설정
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);
        return c.getTime();
    }

    private static Map<String, Object> createHeader() {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        header.put("regDate", System.currentTimeMillis());

        return header;
    }

    private static Map<String, Object> createClaims(Member member) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("email", member.getEmail());
        claims.put("role", member.getUserRole());

        return claims;
    }

    private static Jws<Claims> getClaimsFormToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key)
                .build().parseClaimsJws(token);
    }

    public static String getUserEmailFromToken(String token) {
        return (String) getClaimsFormToken(token).getBody().get("email");
    }

    public static UserRole getRoleFromToken(String token) {
        return (UserRole) getClaimsFormToken(token).getBody().get("role");
    }

    // 유효 한 토큰 인지 확인
    public static boolean isValidateToken(String jwtToken) {
        try {
            Jws<Claims> claims = getClaimsFormToken(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
