package com.compass.products.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.compass.products.dto.ProductDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
public class Product {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 100)
    private String name;

    @Column
    @Positive
    @DecimalMax(value = "999999.99", inclusive = true)
    private BigDecimal value;

    @Column
    private LocalDateTime releaseDate;

    @Column
    @Size(min = 10)
    private String description;
    
    public Product(ProductDTO dto) {
        this.name = dto.getName();
        this.value = dto.getValue();
        this.releaseDate = LocalDateTime.now();
        this.description = dto.getDescription();
    }
    
}
