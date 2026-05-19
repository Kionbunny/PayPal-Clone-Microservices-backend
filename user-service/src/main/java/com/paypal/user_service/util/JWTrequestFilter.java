package com.paypal.user_service.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTrequestFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTrequestFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String path = request.getServletPath();

        // Skip JWT validation for auth APIs
        if (path.startsWith("/auth")) {
            chain.doFilter(request, response);
            return;
        }

        final String authorizationHeader =
                request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // Check Authorization header
        if (authorizationHeader != null &&
                authorizationHeader.startsWith("Bearer ")) {

            jwt = authorizationHeader.substring(7);

            try {

                // Extract username from token
                username = jwtUtil.extractUsername(jwt);

                // Validate only if not already authenticated
                if (username != null &&
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication() == null) {

                    if (jwtUtil.validateToken(jwt, username)) {

                        // Extract role
                        String role = jwtUtil.extractRole(jwt);

                        // Create authentication token
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(
                                        username,
                                        null,
                                        List.of(new SimpleGrantedAuthority(role))
                                );

                        authToken.setDetails(
                                new WebAuthenticationDetailsSource()
                                        .buildDetails(request)
                        );

                        // Set authentication in security context
                        SecurityContextHolder
                                .getContext()
                                .setAuthentication(authToken);
                    }
                }

            } catch (Exception e) {
                logger.error("JWT Token validation failed", e);
            }
        }

        // Continue filter chain
        chain.doFilter(request, response);
    }
}