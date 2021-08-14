package com.khatsukov.cinemaroomrestservice.api;

/**
 * todo Document type TokenExpiredException
 */
public class TokenExpiredException extends RuntimeException {
    public TokenExpiredException(String reason) {
        super(reason);
    }
}
