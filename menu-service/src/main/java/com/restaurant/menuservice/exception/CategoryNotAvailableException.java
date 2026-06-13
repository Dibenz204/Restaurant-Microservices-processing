package com.restaurant.menuservice.exception;

public class CategoryNotAvailableException extends RuntimeException {
    public CategoryNotAvailableException(String message) {
        super(message);
    }
}
