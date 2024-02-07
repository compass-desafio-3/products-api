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

    public ProductDAO() {
        this.emf = Persistence.createEntityManagerFactory("mpu");
    }

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