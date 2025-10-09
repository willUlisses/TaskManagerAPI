package com.TaskManager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityFilter tokenFilter;

    public SecurityConfig(SecurityFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/tasks/find").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tasks/find/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/tasks/find/title").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/tasks").hasAnyRole("ADMIN, USER")
                        .requestMatchers(HttpMethod.GET, "/categories/find").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/find/name").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/categories").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.DELETE, "/categories/delete/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/find").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/find/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/users/delete/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authManager(AuthenticationConfiguration authConfiguration)
            throws Exception {
        return authConfiguration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
