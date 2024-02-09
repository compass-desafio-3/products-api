package com.compass.products.infrastructure.exceptions;

public class ProductNameBlankOrEmptyException extends RuntimeException {
    
    private final Integer status;

    public ProductNameBlankOrEmptyException(Integer status, String message) {
        super(message);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
    
}
