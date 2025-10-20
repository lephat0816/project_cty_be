package com.example.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
    // Exception {
    // http
    // .authorizeHttpRequests((request) -> request
    // .requestMatchers("/admin/**").hasAnyAuthority("ADMIN")
    // // .requestMatchers("/user/**").authenticated()
    // .requestMatchers("/**").permitAll()
    // .anyRequest().authenticated()
    // )

    // .formLogin((form) -> form
    // .loginPage("/login")
    // .defaultSuccessUrl("/", true)
    // .permitAll()
    // )
    // .logout((logout) -> logout.logoutSuccessUrl("/").permitAll());

    // return http.build();
    // }

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll());
        return http.build();
    }
}
