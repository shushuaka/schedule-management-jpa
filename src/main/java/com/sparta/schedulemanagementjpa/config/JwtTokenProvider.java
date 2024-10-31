package com.sparta.schedulemanagementjpa.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final long TOKEN_VALIDITY = 60 * 60 * 1000L; // 1시간

    @PostConstruct
    public void init() {
        byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(secretKeyBytes);
    }

    // JWT 토큰 생성
    public String createToken(String username) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALIDITY))
                .signWith(key, signatureAlgorithm)
                .compact();
    }
}