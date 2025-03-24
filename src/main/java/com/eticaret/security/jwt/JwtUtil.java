package com.eticaret.security.jwt;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private static final String SECRET_KEY = "MySuperSecretKeyForJwtAuthMySuperSecretKeyForJwtAuth";
    private static final long EXPIRATION_TIME = 86400000; // 1 gün

    private final SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY));

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username) 
                .issuedAt(new Date()) 
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) 
                .signWith(key) 
                .compact();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) { // ✅ Yeni metod eklendi!
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(key) 
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
