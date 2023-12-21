package com.example.techit7.global.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY = "abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890abcdefghijklmnopqrstuvwxyz1234567890";
    public static String encode(long expirationSeconds, Map<String, Object> data) {
        Claims claims = Jwts
                .claims()
                .setSubject("sb-23-11-30 jwt")
                .add("data", data)
                .build();

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 1000 * expirationSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims decode(String token) {
        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getPayload();
    }
}
