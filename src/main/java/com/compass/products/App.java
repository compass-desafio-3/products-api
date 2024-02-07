package com.compass.products;

import java.math.BigDecimal;

import com.compass.products.controller.ProductController;
import com.compass.products.dto.ProductDTO;

public class App {
    public static void main(String[] args) {

        ProductController pc = new ProductController();

        ProductDTO product = new ProductDTO("Grand Theft Auto V", new BigDecimal(48.15), "Grand Theft Auto V para PC oferece aos jogadores a opção de explorar o gigantesco e premiado mundo de Los Santos e Blaine County.");

        System.out.println("============Save===========");
        System.out.println(pc.saveProduct(product));
        System.out.println("============List all===========");
        System.out.println(pc.listProducts());
        System.out.println("============Get By Id===========");
        System.out.println(pc.getProductById(1));
        System.out.println("============Get By Name===========");
        System.out.println(pc.getProductByName("Grand Theft Auto V"));
        System.out.println("============Update===========");
        System.out.println(pc.updateProduct(1, product)); // Lança exceção pois não pode salvar com o mesmo nome.
        System.out.println("============Update 2===========");
        product.setName("The witcher 3");
        System.out.println(pc.updateProduct(1, product));
        System.out.println("============Delete 1===========");
        System.out.println(pc.deleteProduct(1));
        System.out.println("============Delete 2==========="); // Lança exceção pois já foi deletado.
        System.out.println(pc.deleteProduct(1));
        System.out.println("============Get By Id 2==========="); // Lança exceção pois já foi deletado.
        System.out.println(pc.getProductById(1));
        System.out.println("============Get By Name 2==========="); // Lança exceção pois já foi deletado.
        System.out.println(pc.getProductByName("The witcher 3"));

    }
}
