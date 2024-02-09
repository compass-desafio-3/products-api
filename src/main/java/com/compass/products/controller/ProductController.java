package com.compass.products.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import com.compass.products.domain.Product;
import com.compass.products.dto.ProductDTO;
import com.compass.products.infrastructure.adapters.LocalDateTimeTypeAdapter;
import com.compass.products.infrastructure.exceptions.CustomizedException;
import com.compass.products.infrastructure.exceptions.ProductAlreadyDeletedException;
import com.compass.products.infrastructure.exceptions.ProductAlreadyExistsException;
import com.compass.products.infrastructure.exceptions.ProductNameBlankOrEmptyException;
import com.compass.products.infrastructure.exceptions.ProductNotFoundException;
import com.compass.products.service.ProductService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.validation.Valid;

public class ProductController {
    
    private ProductService productService;
    private Gson gson;

    public ProductController() {
        this.productService = new ProductService();
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter()).setPrettyPrinting().create();
    }

    // GET
    public String listProducts() {
        List<Product> products = productService.listProducts();
        return gson.toJson(products);
    }
    
    // GET/{id}
    public String getProductById(Integer id) {
        Product product;

        try {
            product = productService.findProductById(id);

            if (product == null)
                throw new ProductNotFoundException(404, "Product not found.");
            
            return gson.toJson(product);
        } catch (ProductNotFoundException e) {
            HashMap<String, String> details = new HashMap<String, String>() {{
                put("message", e.getMessage());
            }};

            CustomizedException exception = new CustomizedException(e.getStatus(), "Bad Request", "Product not found.", details);
            return gson.toJson(exception);
        }
    }

    // GET/name/{name}
    public String getProductByName(String name) {
        Product product;

        try {
            product = productService.findProductByName(name);

            if (product == null)
                throw new ProductNotFoundException(404, "Product not found.");
            
            return gson.toJson(product);
        } catch (ProductNotFoundException e) {
            HashMap<String, String> details = new HashMap<String, String>() {{
                put("message", e.getMessage());
            }};

            CustomizedException exception = new CustomizedException(e.getStatus(), "Bad Request", "Product not found.", details);
            return gson.toJson(exception);
        }
    }
    
    // POST
    public String saveProduct(@Valid ProductDTO dto) {
        Product product;
        try {
            if (dto.getName().isEmpty() || dto.getName().isBlank())
                throw new ProductNameBlankOrEmptyException(400, "Name is blank or empty.");
            
            product = productService.saveProduct(dto);
            return gson.toJson(product);
        } catch (ProductAlreadyExistsException e) {
            HashMap<String, String> details = new HashMap<String, String>() {{
                put("field", "name");
                put("message", e.getMessage());
            }};

            CustomizedException exception = new CustomizedException(e.getStatus(), "Bad Request", "Product already exists in the database.", details);
            return gson.toJson(exception);
        } catch (ProductNameBlankOrEmptyException e) {
            HashMap<String, String> details = new HashMap<String, String>() {{
                put("field", "name");
                put("message", e.getMessage());
            }};

            CustomizedException exception = new CustomizedException(e.getStatus(), "Bad Request", "Product name is blank or empty.", details);
            return gson.toJson(exception);
        }
    }

    // UPDATE/{id}
    public String updateProduct(Integer id, @Valid ProductDTO dto) {
        Product product;

        try {
            product = productService.updateProduct(id, dto);

            if (product == null)
                throw new ProductNotFoundException(404, "Product not found.");
            
            return gson.toJson(product);
        } catch (ProductAlreadyExistsException e) {
            HashMap<String, String> details = new HashMap<String, String>() {{
                put("field", "name");
                put("message", e.getMessage());
            }};

            CustomizedException exception = new CustomizedException(e.getStatus(), "Bad Request", "Product already exists in the database.", details);
            return gson.toJson(exception);
        } catch (ProductNotFoundException e) {
            HashMap<String, String> details = new HashMap<String, String>() {{
                put("message", e.getMessage());
            }};

            CustomizedException exception = new CustomizedException(e.getStatus(), "Bad Request", "Product not found.", details);
            return gson.toJson(exception);
        }
    }

    // DELETE/{id}
    public String deleteProduct(Integer id) {
        try {
            Boolean deleted = productService.deleteProduct(id);

            HashMap<String, String> response = new HashMap<String, String>();

            if (deleted) {
                response.put("success", "Product has been deleted.");
                return gson.toJson(response);
            } else {
                throw new ProductAlreadyDeletedException(400, "Product already deleted from the database.");
            }
        } catch (ProductAlreadyDeletedException e) {
            HashMap<String, String> details = new HashMap<String, String>() {{
                put("message", e.getMessage());
            }};

            CustomizedException exception = new CustomizedException(e.getStatus(), "Bad Request", "Product already deleted from the database.", details);
            return gson.toJson(exception);
        }
    }
    
}
