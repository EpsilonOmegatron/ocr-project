package com.personal.ocr_project.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {

    // Secret key used to sign and verify JWT (must be Base64 encoded)
    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration-milliseconds}")
    private Long jwtExpiryInMillisecond;

    // Utility to generate key from the Base64 secret
    private Key key() {
        // Decode Base64 string into bytes, then create HMAC SHA key
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    // Generate JWT token after successful authentication
    public String generateToken(Authentication authentication) {

        // Extract username from Spring Security Authentication object
        String username = authentication.getName();

        // Current timestamp
        Date currentDate = new Date();

        // Expiration timestamp
        Date expireDate = new Date(currentDate.getTime() + jwtExpiryInMillisecond);

        // Build the JWT token
        return Jwts.builder()
                .subject(username) // "sub" claim → identifies the user
                
                .issuedAt(currentDate) // "iat" claim → token creation time
                .expiration(expireDate) // "exp" claim → token expiration time
                .signWith(key()) // Sign token with secret key
                .compact(); // Convert to String format (HEADER.PAYLOAD.SIGNATURE)
    }

    // Extract username (subject) from JWT token
    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) key()) // Verify signature using secret key
                .build()
                .parseSignedClaims(token) // Parse and validate token
                .getPayload() // Get claims (payload)
                .getSubject(); // Extract "sub" (username)
    }

    // Validate JWT token (signature + expiration)
    public boolean validateToken(String token) {
        Jwts.parser()
                .verifyWith((SecretKey) key()) // Verify signature
                .build()
                .parse(token); // Validate token structure & expiration
        return true; // Valid token
    }
}