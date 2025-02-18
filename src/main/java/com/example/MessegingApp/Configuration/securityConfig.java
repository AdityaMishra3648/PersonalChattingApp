package com.example.MessegingApp.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@CrossOrigin
public class securityConfig {
    @Autowired
    private JWTAuthFilter filter;

    @Autowired
    private JWTAuthenticationEntry point;

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.csrf(csrf->csrf.disable())
     .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                 .authorizeHttpRequests(authorizeRequests->
                authorizeRequests.requestMatchers("/signUp/**","/signUp","/auth/login","/chat/**","/user/**")
                        .permitAll().anyRequest().authenticated())
                 .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                 .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                 .httpBasic(Customizer.withDefaults());
         http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
         return http.build();
     }

     @Bean
     public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
         return builder.getAuthenticationManager();
     }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://127.0.0.1:5500")); // Allow frontend URL
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true); // Allow cookies/session

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
