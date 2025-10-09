package com.TaskManager.controllers;

import com.TaskManager.dtos.LoginDTO;
import com.TaskManager.dtos.LoginResponseDTO;
import com.TaskManager.dtos.RegisterDTO;
import com.TaskManager.models.User;
import com.TaskManager.repositories.UserRepository;
import com.TaskManager.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;
    private final UserRepository repository;
    private final AuthenticationManager authManager;

    public AuthController(TokenService tokenService, UserRepository repository, AuthenticationManager authManager) {
        this.tokenService = tokenService;
        this.repository = repository;
        this.authManager = authManager;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
        if (repository.findByUsername(data.username()) != null) return null;

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encryptedPassword, data.email(), data.role());

        repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(
                data.username(),
                data.password()
        );
        Authentication authManager = this.authManager.authenticate(usernamePassword);
        String token = tokenService.generateToken((User) usernamePassword.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
