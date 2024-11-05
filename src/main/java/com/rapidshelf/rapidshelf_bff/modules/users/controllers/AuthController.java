package com.rapidshelf.rapidshelf_bff.modules.users.controllers;

import com.rapidshelf.rapidshelf_bff.config.commons.AppError;
import com.rapidshelf.rapidshelf_bff.config.security.TokenService;
import com.rapidshelf.rapidshelf_bff.modules.users.dto.LoginDTO;
import com.rapidshelf.rapidshelf_bff.modules.users.dto.TokenDTO;
import com.rapidshelf.rapidshelf_bff.modules.users.dto.UserRegisterDTO;
import com.rapidshelf.rapidshelf_bff.modules.users.entities.User;
import com.rapidshelf.rapidshelf_bff.modules.users.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new TokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegisterDTO data){
        if(this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity
                    .status(409)
                    .body(new AppError("Usuário já cadastrado", 400));
        }


        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.name(),data.email(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}