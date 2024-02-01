package com.compass.products.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private String name;

    @Positive(message = "The value must be positive.")
    private BigDecimal value;
    
    @Size(min = 10, message = "The field must have at least 10 characters.")
    private String description;

}
