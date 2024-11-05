package com.rapidshelf.rapidshelf_bff.modules.users.dto;


import com.rapidshelf.rapidshelf_bff.modules.users.entities.User;
import com.rapidshelf.rapidshelf_bff.modules.users.entities.UserRole;

public record ProfileDTO(
        String id,
        String name,
        String email, UserRole role) {

    public ProfileDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole());
    }

}
