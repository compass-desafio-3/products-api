package com.compass.products.infrastructure.exceptions;

public class ProductNotFoundException extends RuntimeException {

    private final Integer status;

    public ProductNotFoundException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}