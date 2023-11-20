package com.crfstech.MyRemote.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtil util;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request,@NotNull  HttpServletResponse response,@NotNull  FilterChain filterChain)
            throws ServletException, IOException {

        // Reading Token from Authorization Header
        String token = request.getHeader("Authorization");
        if (token != null) {
            String filter = token.substring(7);
            if (filter != null) {
                String username = util.getSubject(filter);
                //if username is not null & Context Authentication must be null
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails user = userDetailsService.loadUserByUsername(username);
                    boolean isValid = util.isValidToken(filter, user.getUsername());
                    if (isValid) {
                        UsernamePasswordAuthenticationToken authToken =
                                new UsernamePasswordAuthenticationToken(username, user.getPassword(), user.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);

                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
