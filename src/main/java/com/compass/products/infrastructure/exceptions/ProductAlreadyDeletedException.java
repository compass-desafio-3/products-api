package com.compass.products.infrastructure.exceptions;

public class ProductAlreadyDeletedException extends RuntimeException {

    private final Integer status;

    public ProductAlreadyDeletedException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}