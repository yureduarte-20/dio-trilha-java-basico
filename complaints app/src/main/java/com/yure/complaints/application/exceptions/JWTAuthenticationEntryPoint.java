package com.yure.complaints.application.exceptions;

import io.swagger.v3.core.util.Json;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
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
            AuthenticationCredentialsNotFoundException.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        var j = Json.pretty().writeValueAsString(new Object(){
            public  int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
            public String path = request.getServletPath();
            public String message = authException.getLocalizedMessage();
            public String cause = "UNAUTHORIZED";
        });
        response.getWriter().write(j);
        System.out.println("Caiu no entrypoint");
        authException.printStackTrace();
    }
}
