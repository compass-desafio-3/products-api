package com.compass.products.service;

import java.util.List;

import com.compass.products.domain.Product;
import com.compass.products.dto.ProductDTO;
import com.compass.products.infrastructure.exceptions.ProductAlreadyExistsException;
import com.compass.products.persistence.ProductDAO;

public class ProductService {
    
    private ProductDAO productDAO;

    public ProductService() {
        this.productDAO = new ProductDAO();
    }

    public List<Product> listProducts() {
        return productDAO.list();
    }

    public Product findProductById(Integer id) {
       return productDAO.findById(id);
    }

    public Product findProductByName(String name) {
        return productDAO.findByName(name);
    }

    public Product saveProduct(ProductDTO dto) {
        if (findProductByName(dto.getName()) != null)
            throw new ProductAlreadyExistsException(409, String.format("Product with the name %s already exists in the database.", dto.getName()));

        Product product = new Product(dto);
        return productDAO.save(product);
    }

    public Product updateProduct(Integer id, ProductDTO dto) {
        if (findProductByName(dto.getName()) != null)
            throw new ProductAlreadyExistsException(409, String.format("Product with the name %s already exists in the database.", dto.getName()));

        return productDAO.update(id, dto);
    }

    public Boolean deleteProduct(Integer id) {      
        return productDAO.delete(id);
    }
    
}
