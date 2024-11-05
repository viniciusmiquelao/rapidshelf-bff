package com.rapidshelf.rapidshelf_bff.modules.users.dto;
import com.rapidshelf.rapidshelf_bff.modules.users.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(
        Long id,

        @NotBlank(message = "O nome do usuário é obrigatório")
        String name,

        @NotBlank(message = "O e-mail do usuário é obrigatório")
        @Email(message = "O e-mail do usuário informado não é válido")
        String email,

        @NotBlank(message = "A senha é obrigatório")
        @Size(min = 6, max = 20, message = "A senha deve conter entre 6 e 20 caracteres!")
        String password,

        UserRole role
) {
}
