package com.compass.products.controller;

import java.util.List;

import com.compass.products.domain.Product;
import com.compass.products.dto.ProductDTO;
import com.compass.products.service.ProductService;

import jakarta.validation.Valid;

public class ProductController {
    
    private ProductService productService;

    public ProductController() {
        this.productService = new ProductService();
    }

    // GET
    public List<Product> listProducts() {
        return productService.listProducts();
    }

    // POST
    public Product saveProduct(@Valid ProductDTO dto) {
        return productService.saveProduct(dto);
    }
    
}
