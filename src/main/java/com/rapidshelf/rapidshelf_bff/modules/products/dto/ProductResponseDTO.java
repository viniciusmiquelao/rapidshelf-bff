package com.rapidshelf.rapidshelf_bff.modules.products.dto;

import com.rapidshelf.rapidshelf_bff.modules.products.entities.Product;
import com.rapidshelf.rapidshelf_bff.modules.products.entities.ProductStatus;

public record ProductResponseDTO(String id, String name, String description, String userId, Integer price, ProductStatus status) {
    public ProductResponseDTO(Product product){
        this(product.getId(), product.getName(),  product.getDescription(), product.getUserId(),  product.getPrice(),  product.getStatus());
    }
}
