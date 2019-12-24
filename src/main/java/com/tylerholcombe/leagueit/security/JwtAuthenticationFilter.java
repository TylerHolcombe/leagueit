package com.tylerholcombe.leagueit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tylerholcombe.leagueit.user.data.ApplicationUser;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private String secret;
    private String header;
    private String prefix;
    private long expirationTime;

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationFilter withSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public JwtAuthenticationFilter withHeader(String header) {
        this.header = header;
        return this;
    }

    public JwtAuthenticationFilter withPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public JwtAuthenticationFilter withExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser credentials = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword(),
                            new ArrayList<>()));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        Date expiration = new Date(System.currentTimeMillis() + expirationTime);
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(expiration)
                .sign(Algorithm.HMAC512(secret));
        response.addHeader(header, prefix + token);
        try (PrintWriter writer = response.getWriter()) {
            writer.write("{\"username\":\"" + username + "\", \"expiration\":\"" + expiration + "\", \"token\":\"" + prefix + token + "\"}");
            writer.flush();
        }
    }
}
