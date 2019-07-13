/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nihil.securityjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author Nihil
 */
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

//    private AuthenticationManager authManager;
//
//    private final JwtConfig jwtConfig;
//
//    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
//        this.authManager = authManager;
//        this.jwtConfig = jwtConfig;
//        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//            throws AuthenticationException {
//
//        try {
//            UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
//
//            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                    creds.getEmail(), creds.getPassword(), Collections.emptyList());
//
//            return authManager.authenticate(authToken);
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//            Authentication auth) throws IOException, ServletException {
//
//        Long now = System.currentTimeMillis();
//
//        System.out.println("Login Successful");
//
//        String token = Jwts.builder()
//                .setSubject(auth.getName())
//                .claim("authorities", auth.getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
//                .setIssuedAt(new Date(now))
//                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))
//                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
//                .compact();
//        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
//        Map resp = new HashMap();
//        resp.put("token", token);
//        resp.put("name", auth.getName());
//        resp.put("roles", auth.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
//        response.getWriter().print(new Gson().toJson(resp));
//
//    }
    private AuthenticationManager authManager;

    private final JwtConfig jwtConfig;

    public JwtUsernameAndPasswordAuthenticationFilter(AuthenticationManager authManager, JwtConfig jwtConfig) {
        this.authManager = authManager;
        this.jwtConfig = jwtConfig;

        this.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(jwtConfig.getUri(), "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
//            UserCredentials creds = new ObjectMapper().readValue(request.getInputStream(), UserCredentials.class);
            UserCredentials creds = new UserCredentials();
            creds.setEmail(request.getParameter("email"));
            creds.setPassword(request.getParameter("password"));
//            creds.setPassword("test");
            
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    creds.getEmail(), creds.getPassword(), Collections.emptyList());

            return authManager.authenticate(authToken);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        Long now = System.currentTimeMillis();

        System.out.println("Auth Successful");

        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration() * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret().getBytes())
                .compact();
        response.addHeader(jwtConfig.getHeader(), jwtConfig.getPrefix() + token);
        Map resp = new HashMap();
        resp.put("token", token);
        resp.put("name", auth.getName());
        resp.put("roles", auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        response.getWriter().print(new Gson().toJson(resp));
    }

}
