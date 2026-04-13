package com.personal.ocr_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.personal.ocr_project.exception.CustomAccessDeniedHandler;
import com.personal.ocr_project.security.JwtAuthenticationEntryPoint;
import com.personal.ocr_project.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Password Encoder bean to decide on encoding strategy
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Dependancy injection for AuthServiceImpl
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // The main configuration bean for spring security by introducing a
    // security filter chain bean that runs on every request
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Authorization permissions for different API paths
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated());

        // Disable cross site request forgery since
        // we'll implement stateless authentication (JWT)
        http.csrf(csrf -> csrf.disable());

        // Options for authentication
        http.formLogin(form -> form.disable());
        http.httpBasic(basicAuth -> basicAuth.authenticationEntryPoint(new JwtAuthenticationEntryPoint()));

        // Use the jwt authentication filter before the username password auth filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // Exception handling options
        http.exceptionHandling((exception) -> exception.accessDeniedHandler(new CustomAccessDeniedHandler()));

        return http.build();
    }
}
