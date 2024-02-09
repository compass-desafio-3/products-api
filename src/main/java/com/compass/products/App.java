package com.compass.products;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Scanner;

import com.compass.products.controller.ProductController;
import com.compass.products.dto.ProductDTO;

public class App {
    public static void main(String[] args) {
        /**
         * SuppressWarnings used to avoid the open scanner warning. In this case, the
         * scanner cannot be closed within the while as it would cause an error in the
         * application, and outside the while the code would not reach.
         */
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        ProductController pc = new ProductController();

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. List all products");
            System.out.println("2. Get product by ID");
            System.out.println("3. Get product by Name");
            System.out.println("4. Create a new product");
            System.out.println("5. Delete product by ID");
            System.out.println("6. Update product by ID");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(pc.listProducts());
                    break;
                case 2:
                    System.out.print("Enter the ID: ");
                    int idToGet = scanner.nextInt();
                    System.out.println(pc.getProductById(idToGet));
                    break;
                case 3:
                    scanner.nextLine(); // nextLine bug correction.

                    System.out.print("Enter the Name: ");
                    String nameToGet = scanner.nextLine();
                    System.out.println(pc.getProductByName(nameToGet));
                    break;
                case 4:
                    scanner.nextLine(); // nextLine bug correction.

                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the value: ");
                    BigDecimal value = scanner.nextBigDecimal();

                    scanner.nextLine(); // nextLine bug correction.

                    System.out.print("Enter the description: ");
                    String description = scanner.nextLine();
                    ProductDTO dto = new ProductDTO(name, value, description);
                    System.out.println(pc.saveProduct(dto));
                    break;
                case 5:
                    System.out.print("Enter the ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    System.out.println(pc.deleteProduct(idToDelete));
                    break;
                case 6:
                    System.out.print("Enter the ID to update: ");
                    int idToUpdate = scanner.nextInt();

                    scanner.nextLine(); // nextLine bug correction.

                    System.out.print("Enter the new name: ");
                    String updatedName = scanner.nextLine();
                    System.out.print("Enter the new value: ");
                    BigDecimal updatedValue = scanner.nextBigDecimal();

                    scanner.nextLine(); // nextLine bug correction.
                    
                    System.out.print("Enter the new description: ");
                    String updatedDescription = scanner.nextLine();
                    ProductDTO dto2 = new ProductDTO(updatedName, updatedValue, updatedDescription);
                    System.out.println(pc.updateProduct(idToUpdate, dto2));
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
