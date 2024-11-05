package com.rapidshelf.rapidshelf_bff.config.advice;

import com.rapidshelf.rapidshelf_bff.config.commons.AppError;
import com.rapidshelf.rapidshelf_bff.modules.products.exceptions.UnauthorizedProductCreationException;
import com.rapidshelf.rapidshelf_bff.modules.users.exceptions.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> handleGlobalException(Exception ex, WebRequest request) {
        AppError response = new AppError(
                "Ocorreu um erro interno. Por favor, verifique os detalhes.",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AppError> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }

        AppError response = new AppError(
                "Erro de validação",
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                validationErrors
        );

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<AppError> handleIntegrityViolation(){

        AppError response = new AppError(
                "Usuário já cadastrado!",
                HttpStatus.CONFLICT.value()

        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<AppError> handleBadCredentials() {
        AppError response = new AppError(
                "Credenciais inválidas",
                HttpStatus.UNAUTHORIZED.value()
        );

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedProductCreationException.class)
    public ResponseEntity<AppError> handleUnauthorizedProductCreation(UnauthorizedProductCreationException ex) {
        AppError response = new AppError(
                ex.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AppError> handleUserNotFound(UserNotFoundException ex) {
        AppError response = new AppError(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}