package com.khatsukov.cinemaroomrestservice.api;

/**
 * todo Document type SeatNotAvailableException
 */
public class SeatNotAvailableException extends RuntimeException {
    public SeatNotAvailableException(String reason) {
        super(reason);
    }
}
