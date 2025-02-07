package com.example.MessegingApp.Configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeHttpRequests(authorizeRequests->
                authorizeRequests.requestMatchers("/signUp").permitAll().anyRequest().authenticated()
        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
