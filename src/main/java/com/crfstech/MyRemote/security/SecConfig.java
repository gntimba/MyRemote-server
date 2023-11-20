package com.crfstech.MyRemote.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecConfig {
    @Autowired
    private SecurityFilter secFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private UnAuthorizedUserAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        return http
                .csrf(AbstractHttpConfigurer::disable)    //Disabling CSRF as not using form based login
                .cors(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth.requestMatchers("/signup", "/login", "/images/**", "/swagger-ui/**"
                        , "/swagger-resources/**", "/v3/**").permitAll().anyRequest().authenticated())
                .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                                .exceptionHandling(e -> e.authenticationEntryPoint(authenticationEntryPoint))
                //To Verify user from second request onwards............
                .addFilterBefore(secFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
