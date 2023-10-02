package com.luizcarlos.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(VotoDuplicadoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleVotoDuplicadoException(VotoDuplicadoException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());


    }

    @ExceptionHandler(IdNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> idNaoEncontradoException(IdNaoEncontradoException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());

    }
}