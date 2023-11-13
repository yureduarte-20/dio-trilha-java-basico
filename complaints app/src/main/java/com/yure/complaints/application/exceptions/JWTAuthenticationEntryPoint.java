package com.yure.complaints.application.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final List<Class<? extends AuthenticationException>> classes = List.of(
            CredentialsExpiredException.class, AccountExpiredException.class,
            AuthenticationCredentialsNotFoundException.class );
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        if(classes.stream().noneMatch(e -> e.isInstance(authException) )){
            response.addHeader("Content-Type", "application/json");
            System.out.println(authException.getMessage());
            response.sendError(401, "{\"msg\":\"Não autorizado\"}");
        }
        authException.printStackTrace();
    }
}
