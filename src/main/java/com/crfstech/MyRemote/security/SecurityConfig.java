//package com.crfstech.MyRemote.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
//    @Autowired
//    private BCryptPasswordEncoder bCryptEncoder;
//
//    @Autowired
//    private UnAuthorizedUserAuthenticationEntryPoint authenticationEntryPoint;
//
//    @Autowired
//    private SecurityFilter secFilter;
//
//    //Required in case of Stateless Authentication
//
//
//
//
//    //    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////
////        auth.userDetailsService(userDetailsService)
////                .passwordEncoder(bCryptEncoder);
////    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    private static final List<String> AUTH_LIST = Arrays.asList(
//            "/swagger-resources/**",
//            "/swagger-ui.html**",
//            "/webjars/**",
//            "/swagger-ui/**",
//            "favicon.ico");
//
//    @Bean
//    public BasicAuthenticationEntryPoint swaggerAuthenticationEntryPoint() {
//        BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
//        entryPoint.setRealmName("Swagger Realm");
//        return entryPoint;
//    }
//
//    private class CustomRequestMatcher implements RequestMatcher {
//
//        private List<AntPathRequestMatcher> matchers;
//
//        private CustomRequestMatcher(List<String> matchers) {
//            this.matchers = matchers.stream().map(AntPathRequestMatcher::new).collect(Collectors.toList());
//        }
//
//        @Override
//        public boolean matches(HttpServletRequest request) {
//            return matchers.stream().anyMatch(a -> a.matches(request));
//        }
//
//    }
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .csrf(AbstractHttpConfigurer::disable)    //Disabling CSRF as not using form based login
//                .cors(AbstractHttpConfigurer::disable)
//                .authorizeRequests(auth -> auth.requestMatchers("/signup", "/login", "/images/**", "/swagger-ui/**"
//                        , "/swagger-resources/**", "/v2/**").permitAll().anyRequest().authenticated())
//                .exceptionHandling(e -> e.defaultAuthenticationEntryPointFor(swaggerAuthenticationEntryPoint(), new CustomRequestMatcher(AUTH_LIST))
//                        .authenticationEntryPoint(authenticationEntryPoint))
//                .sessionManagement(e -> e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                //To Verify user from second request onwards............
//                .addFilterBefore(secFilter, UsernamePasswordAuthenticationFilter.class).build();
//    }
//}
