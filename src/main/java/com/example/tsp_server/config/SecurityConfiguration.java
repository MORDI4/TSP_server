package com.example.tsp_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeRequests(authorize -> authorize
                        .anyRequest().permitAll() // Pozwól na wszystkie żądania bez uwierzytelniania
                )
                .httpBasic().disable() // Wyłącz podstawowe uwierzytelnienie HTTP
                .formLogin().disable() // Wyłącz formularz logowania
                .logout().disable(); // Wyłącz wylogowywanie
        return http.build();
    }
}