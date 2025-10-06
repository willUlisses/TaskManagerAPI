package com.TaskManager.security;

import com.TaskManager.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Configuration
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("Auth0-API")
                    .withSubject(user.getUsername())
                    .withExpiresAt(generateTokenExpirationTime())
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            return null;
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("Auth0-API")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch(JWTVerificationException e) {
            return null;
        }
    }

    private Instant generateTokenExpirationTime() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
