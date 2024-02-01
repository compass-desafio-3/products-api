package com.compass.products.service;

import java.util.List;

import com.compass.products.domain.Product;
import com.compass.products.dto.ProductDTO;
import com.compass.products.persistence.ProductDAO;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public Product saveProduct(ProductDTO dto) {
        Product product = new Product(dto);
        return productDAO.save(product);
    }

    public List<Product> listProducts() {
        return productDAO.listProducts();
    }
    
}
