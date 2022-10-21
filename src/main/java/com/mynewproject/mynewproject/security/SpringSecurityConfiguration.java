package com.mynewproject.mynewproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration  {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //All request to be authenticated
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated());
        //basic check
        http.httpBasic(Customizer.withDefaults());
        //enabling post
        http.csrf().disable();
        return http.build();
    }
}
