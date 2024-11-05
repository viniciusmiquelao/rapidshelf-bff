package com.rapidshelf.rapidshelf_bff.modules.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UnauthorizedProductCreationException extends RuntimeException {
    public UnauthorizedProductCreationException(String message) {
        super(message);
    }
}
