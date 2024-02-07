package com.compass.products.infrastructure.exceptions;

public class ProductAlreadyExistsException extends RuntimeException {

    private final Integer status;

    public ProductAlreadyExistsException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}