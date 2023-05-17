package com.example.customer.exception;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String format, Object... parameters) {
        super(String.format(format, parameters));
    }
}
