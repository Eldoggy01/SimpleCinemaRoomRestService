package com.khatsukov.cinemaroomrestservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * todo Document type ErrorHandler
 */
@RestControllerAdvice
public class ErrorHandler {

    private final String ERROR = "error";

    @ExceptionHandler({PasswordNotCorrectException.class,TokenExpiredException.class,SeatNotAvailableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlePasswordNotCorrectException(Exception exception) {
        return Map.of(ERROR, exception.getMessage());
    }
}
