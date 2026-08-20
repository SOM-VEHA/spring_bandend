package com.spring_bandend.spring_bandend.exception;

public class EncryptionException extends RuntimeException {
    public EncryptionException(String message, Throwable cause) {
        super(message, cause); // cause kept for server logs
    }
}
