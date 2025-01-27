package com.fitgo.project.security.util;

import java.io.IOException;
import java.util.Collections;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

private final JwtTokenUtil jwtToken;

public JwtAuthenticationFilter(JwtTokenUtil jwtToken) {
this.jwtToken = jwtToken;
}

@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
throws ServletException, IOException {

String authHeader = request.getHeader("Authorization");

if (authHeader == null || !authHeader.startsWith("Bearer ")) {
filterChain.doFilter(request, response);
return;
}

String token = authHeader.substring(7);
if (jwtToken.validateToken(token)) {
String email = jwtToken.getUserEmailFromToken(token);
System.out.println("Email extracted from token: " + email);

// Create an Authentication object and set it in the SecurityContext
UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null,
Collections.emptyList());

// Populate SecurityContext
SecurityContextHolder.getContext().setAuthentication(authentication);
} else {
response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
response.getWriter().write("Invalid or Expired Token");
return;
}

filterChain.doFilter(request, response);
}
}