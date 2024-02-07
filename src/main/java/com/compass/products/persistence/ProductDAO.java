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

     /**
     * Returns a list of all products stored in the database, or an error message
     * with stack trace if an error occurs in the sql query
     * 
     * @return the list of all products stored in the database
     */
    public List<Product> list() {
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

    /**
     * Returns a product by its id
     * 
     * @param id the id of the product
     * @return the product with the given id, or null if the product was not found
     */
    public Product findById(Integer id) {
        EntityManager em = emf.createEntityManager();
        Product product = new Product();

        try {
            String jpql = "SELECT p FROM Product p WHERE p.id = :id";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("id", id);
            product = query.getSingleResult();
        } catch (ProductNotFoundException e) {
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }

        return product;
    }

     /**
     * Returns a product by its name
     * 
     * @param name the name of the product
     * @return the product with the given name, or null if the product was not found
     */
    public Product findByName(String name) {
        EntityManager em = emf.createEntityManager();
        Product product;

        try {
            String jpql = "SELECT p FROM Product p WHERE p.name = :name";
            TypedQuery<Product> query = em.createQuery(jpql, Product.class);
            query.setParameter("name", name);
            product = query.getSingleResult();
            return product;
        } catch (ProductNotFoundException e) {
            return null;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

     /**
     * Updates a product in the database
     * 
     * @param id the id of the product that will be updated
     * @param updatedProduct the new product data
     * @return the updated product
     */
    public Product update(Integer id, ProductDTO updatedProduct) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product product = findById(id);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            product.setValue(updatedProduct.getValue());

            em.getTransaction().commit();
        }
        em.close();

        return product;
    }

    /**
     * Deletes a product from the database
     * 
     * @param id the id of the product that will be deleted
     * @return true if the product was deleted, false if the product was not found
     */
    public boolean delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.remove(product);
            em.getTransaction().commit();
            return true;
        }
        em.close();
        return false;
    }

}