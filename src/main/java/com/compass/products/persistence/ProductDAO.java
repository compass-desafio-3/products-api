package com.compass.products.persistence;

import java.util.ArrayList;
import java.util.List;

import com.compass.products.domain.Product;
import com.compass.products.dto.ProductDTO;
import com.compass.products.infrastructure.exceptions.ProductNotFoundException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProductDAO {

    private EntityManagerFactory emf;

    /**
     * Constructor that creates the EntityManagerFactory
     */
    public ProductDAO() {
        this.emf = Persistence.createEntityManagerFactory("mpu");
    }

    /**
     * Persists the object in the database and returns it with the
     * auto-incrementable id created by the database.
     * 
     * @param product object that will be saved in the database
     * @return the object persisted in the database
     */
    public Product save(Product product) {
        try {
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    public List<Product> listProducts() {
        EntityManager em = emf.createEntityManager();
        List<Product> products = new ArrayList<>();

        try {
            String jpql = "SELECT p FROM Product p";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            List<Product> productList = query.getResultList();
            return productList;
        } catch (Exception e) {
            System.err.println("Error executing the query." + e);
        } finally {
            em.close();
        }

        return products;
    }

}