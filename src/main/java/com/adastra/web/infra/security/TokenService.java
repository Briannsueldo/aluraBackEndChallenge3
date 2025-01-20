package com.adastra.web.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.adastra.web.domain.users.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;


@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String apiSecret;
    
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            return JWT.create()
                .withIssuer("adastra")
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(generateExpirationDate())
                .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String jwtToken) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(apiSecret);
            return JWT.require(algoritmo)
                    .withIssuer("adastra")
                    .build()
                    .verify(jwtToken)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token");
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(12).toInstant(ZoneOffset.of("-03:00"));
    }

}
