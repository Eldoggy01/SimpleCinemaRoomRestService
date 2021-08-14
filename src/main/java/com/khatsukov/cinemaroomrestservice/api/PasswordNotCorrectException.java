package com.khatsukov.cinemaroomrestservice.api;

/**
 * todo Document type PasswordNotCorrectException
 */
public class PasswordNotCorrectException extends RuntimeException{
    public PasswordNotCorrectException(String reason) {
        super(reason);
    }
}
