package com.example.ecommerce.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.enable())

        .authorizeHttpRequests(auth -> auth
            // Public endpoints
            .requestMatchers("/api/v1/products/**").permitAll()
            .requestMatchers("/api/v1/auth/register").permitAll()

            // Protected endpoints
            .requestMatchers("/api/v1/orders/**").authenticated()
            .requestMatchers("/api/v1/products/**").authenticated()
            .anyRequest().authenticated()
        )

        .formLogin(form -> form
            .defaultSuccessUrl("/api/v1/products", true)
            .permitAll()
        )

        .logout(logout -> logout.permitAll());

    return http.build();
}
