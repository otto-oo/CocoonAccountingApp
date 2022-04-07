package com.cocoon.exception;

// todo can be extended from RuntimeException
public class CocoonException extends RuntimeException {
    // todo bussiness exceptions needs to be add
    public CocoonException(String message) {
        super(message);
    }
}
