package com.compass.products.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private String name;

    @Positive(message = "The value must be positive.")
    @DecimalMax(value = "999999.99", inclusive = true, message = "Value must be less than or equal to 999999.99")
    private BigDecimal value;

    @Size(min = 10, message = "The field must have at least 10 characters.")
    private String description;

}
