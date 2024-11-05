package com.rapidshelf.rapidshelf_bff.modules.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

public record ProductRequestDTO(
        @NotBlank(message = "O nome do produto é obrigatório")
        String name,

        @NotBlank(message = "A descrição do produto é obrigatório")
        String description,

        @Min(value = 100, message = "O produto deve valer no mínimo 1 real")
        Integer price
) {
}
