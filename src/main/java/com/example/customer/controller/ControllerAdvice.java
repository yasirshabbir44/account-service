package com.example.customer.controller;

import com.example.customer.exception.InsufficientBalanceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    /**
     * handles InvalidArgumentExceptions
     *
     * @param e the exception as cause
     */
    @ExceptionHandler(
            IllegalArgumentException.class
    )
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected String handleBadRequest2(RuntimeException e) {
        log.error("HttpStatus.BAD_REQUEST", e);
        return e.getMessage();
    }

    /**
     * handle other exceptions
     *
     * @param e the exception
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected void handleGeneric(Exception e) {
        log.error("HttpStatus.INTERNAL_SERVER_ERROR", e);
    }


    /**
     * handles InvalidArgumentExceptions
     *
     * @param e the exception as cause
     */
    @ExceptionHandler(
            InsufficientBalanceException.class
    )
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected void handleBadRequest(RuntimeException e) {
        log.error("HttpStatus.BAD_REQUEST", e);
    }
}
