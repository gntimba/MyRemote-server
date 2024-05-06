package com.crfstech.MyRemote.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class JWTUtil {
    @Value("${app.secret.key}")
    private String secret_key;

    // code to generate Token
    public String generateToken(String subject) {
        String tokenId = String.valueOf(new Random().nextInt(10000));
        return Jwts.builder()
                .setId(tokenId)
                .setSubject(subject)
                .setIssuer("CRFS")
                .setAudience("CRFS")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(365)))
                .signWith(getSignInKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    // code to get Claims
    public Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // code to check if token is valid
    public boolean isValidToken(String token) {
        return getClaims(token).getExpiration().after(new Date(System.currentTimeMillis()));
    }

    // code to check if token is valid as per username
    public boolean isValidToken(String token, String username) {
        String tokenUserName = getSubject(token);
        return (username.equals(tokenUserName) && !isTokenExpired(token));
    }

    // code to check if token is expired
    public boolean isTokenExpired(String token) {
        return getExpirationDate(token).before(new Date(System.currentTimeMillis()));
    }

    //code to get expiration date
    public Date getExpirationDate(String token) {
        return getClaims(token).getExpiration();
    }

    //code to get expiration date
    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
