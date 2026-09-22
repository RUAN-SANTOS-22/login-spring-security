package com.example.login_spring_security.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service 
public class JwtService {

    public static final String SECRET = "MinhaChaveSuperSECRETA#123456789#876543210#";

    public String generateToken(String username, String role){
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("Role", role);
            return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30))
                .claims(claims)
                .signWith(getSignedKey())
                .compact();
    }

    private SecretKey getSignedKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public Claims verifySignatureAndExtractClaims(String token){
        return Jwts.parser()
            .verifyWith(getSignedKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
    //métodos para obtenção de claim
    public String extractUsername(String token){
        return verifySignatureAndExtractClaims(token).getSubject();
    }
    
    public Date extractExpiration(String token){
        return verifySignatureAndExtractClaims(token).getExpiration();
    }

    public Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}
