package com.restaurant.menuservice.exception;

public class MenuNotAvailableException extends RuntimeException {
    public MenuNotAvailableException(String message) {
        super(message);
    }
}
