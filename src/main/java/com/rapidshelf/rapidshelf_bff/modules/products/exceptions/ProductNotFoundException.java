package com.rapidshelf.rapidshelf_bff.modules.products.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("Produto não encontrado.");
    }
}