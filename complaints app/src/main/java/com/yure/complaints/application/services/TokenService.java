package com.yure.complaints.application.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yure.complaints.domain.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.TimeZone;

@Service
public class TokenService {
    @Value("{api.security.token.secret}")
    private  String JWT_SECRET;

    public String generateToken(User user){
        try{

            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);;
            String token = JWT.create().withIssuer("auth-api")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.expirationTime())
                    .sign(algorithm);
            return  token;
        }catch (JWTCreationException e){
            throw new RuntimeException("Error generate token");
        }

    }
    public String verifyToken(String token){
        try{
            return JWT.require(Algorithm.HMAC256(JWT_SECRET))
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();

        }catch (JWTVerificationException e){
            return null;
        }
    }
    private Instant expirationTime(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
