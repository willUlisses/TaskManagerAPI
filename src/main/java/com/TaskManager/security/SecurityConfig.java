package com.TaskManager.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    // importar meu securityFilter pro addFilterBefore
    // adicionar meu securityFilterChain e meu filter before
    // Criar um bean de um encoder bcrypt

}
