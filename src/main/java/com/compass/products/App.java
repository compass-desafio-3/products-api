package com.compass.products;

import java.math.BigDecimal;

import com.compass.products.controller.ProductController;
import com.compass.products.dto.ProductDTO;

public class App 
{
    public static void main( String[] args )
    {

        ProductController pc = new ProductController();

        ProductDTO product = new ProductDTO("Ventilador", new BigDecimal(235.99), "Ventilador de 6 pás");
        
        pc.saveProduct(product);
        System.out.println(pc.listProducts());
        
    }
}
