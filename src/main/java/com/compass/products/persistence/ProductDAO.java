package com.compass.products.persistence;

import java.util.ArrayList;
import java.util.List;

import com.compass.products.domain.Product;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class ProductDAO {
    private EntityManagerFactory emf;

    public ProductDAO() {
        this.emf = Persistence.createEntityManagerFactory("mpu");
    }

    public Product save(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
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
            System.err.println(e);
        } finally {
            em.close();
        }
        
        return products;
    }

}