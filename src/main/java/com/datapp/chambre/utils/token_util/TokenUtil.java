package com.datapp.chambre.utils.token_util;

import com.datapp.chambre.exception.runtime.UnCompletedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by kmluns on 4.10.2020
 */
@Service
public class TokenUtil<T> {

    @Value("${token.secretKey}")
    private String TOKEN_SECRET_KEY;

    public T extractSubject(String token, Class<T> classType) {
        String subjectString = extractClaim(token, Claims::getSubject);
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(subjectString, classType);
        } catch (JsonProcessingException e) {
            throw new UnCompletedException(); // TODO : Exception should be created.
        }
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <K> K extractClaim(String token, Function<Claims, K> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(TOKEN_SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(T object, long milliseconds) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, object, milliseconds);
    }

    private String createToken(Map<String, Object> claims, Object subject, long miliseconds) {
        ObjectMapper mapper = new ObjectMapper();
        String subjectString = null;
        try {
            subjectString = mapper.writeValueAsString(subject);
        } catch (JsonProcessingException e) {
            throw new UnCompletedException(); // TODO : Exception should be created.
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subjectString)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + miliseconds))
                .signWith(SignatureAlgorithm.HS256, TOKEN_SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token, T object) {
        boolean result = false;
        if (isTokenExpired(token)) {
            final T subject = extractSubject(token, (Class<T>) object.getClass());
            result = subject.equals(object);
        }
        return result;
    }
}
