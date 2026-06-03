package com.notaria.infrastructure.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
@Value("${jwt.secret}")
private String secret;

@Value("${jwt.expiration}")
private Long expiration;

private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
}

public String generarToken(String email) {
    Map<String, Object> claims = new HashMap<>();
    return Jwts.builder()
            .claims(claims)
            .subject(email)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey())
            .compact();
}

public String extraerEmail(String token) {
    return extraerClaims(token).getSubject();
}

public boolean esTokenValido(String token, String email) {
    return extraerEmail(token).equals(email) && !esTokenExpirado(token);
}

private boolean esTokenExpirado(String token) {
    return extraerClaims(token).getExpiration().before(new Date());
}

private Claims extraerClaims(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
}
}

