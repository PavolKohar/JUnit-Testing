package com.palci.Exceptions;

public class NotEnoughFundsException extends RuntimeException {
    public NotEnoughFundsException(String message) {
        super(message);
    }
}
