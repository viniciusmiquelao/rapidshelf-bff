package com.rapidshelf.rapidshelf_bff.modules.users.repositories;

import com.rapidshelf.rapidshelf_bff.modules.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, String> {
    UserDetails findByEmail(String email);
}