package com.rapidshelf.rapidshelf_bff.modules.users.controllers;

import com.rapidshelf.rapidshelf_bff.config.commons.AppError;
import com.rapidshelf.rapidshelf_bff.config.security.TokenService;
import com.rapidshelf.rapidshelf_bff.modules.users.dto.LoginDTO;
import com.rapidshelf.rapidshelf_bff.modules.users.dto.ProfileDTO;
import com.rapidshelf.rapidshelf_bff.modules.users.dto.TokenDTO;
import com.rapidshelf.rapidshelf_bff.modules.users.dto.UserRegisterDTO;
import com.rapidshelf.rapidshelf_bff.modules.users.entities.User;
import com.rapidshelf.rapidshelf_bff.modules.users.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserRepository repository;
    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity getProfile(){
        User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ProfileDTO profile = new ProfileDTO(user);
        return ResponseEntity.ok(profile);
    }


}