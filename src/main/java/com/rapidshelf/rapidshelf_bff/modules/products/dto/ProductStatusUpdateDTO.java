package com.rapidshelf.rapidshelf_bff.modules.products.dto;

import com.rapidshelf.rapidshelf_bff.modules.products.entities.ProductStatus;
import jakarta.validation.constraints.NotNull;

public class ProductStatusUpdateDTO {
    
    @NotNull(message = "O status não pode ser nulo.")
    private ProductStatus status;

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}