package com.example.springboot.security;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorization != null && authorization.startsWith("Bearer ")) {
            String bearerTokenValue = authorization.substring(7);

            String username = jwtService.getUsername(bearerTokenValue);
            List<GrantedAuthority> authorities = jwtService.getAuthority(bearerTokenValue);

            if (Objects.nonNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(username, null, authorities)
                );
            }
        }

        // f1 -> f2 -> f3 -> ... -> fn
        filterChain.doFilter(request, response);
    }
}
