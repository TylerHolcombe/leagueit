package com.tylerholcombe.leagueit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private String header;
    private String prefix;
    private String secret;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    public JwtAuthorizationFilter withHeader(String header) {
        this.header = header;
        return this;
    }

    public JwtAuthorizationFilter withPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public JwtAuthorizationFilter withSecret(String secret) {
        this.secret = secret;
        return this;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(header);
        if (token == null || !token.startsWith(prefix)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authToken = getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
        String username = JWT.require(Algorithm.HMAC512(secret))
                .build()
                .verify(token.replace(prefix, ""))
                .getSubject();
        if (username == null || username.isEmpty()) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
    }
}
