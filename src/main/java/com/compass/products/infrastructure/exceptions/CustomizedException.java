package com.compass.products.infrastructure.exceptions;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomizedException {
    private Integer code;
    private String status;
    private String message;
    private Map<String, String> details;
}
