package com.rapidshelf.rapidshelf_bff.modules.products.exceptions;

public class InvalidProductStatusException extends RuntimeException {
    public InvalidProductStatusException() {
        super("Status inválido. Use ACTIVED ou REPROVED.");
    }
}