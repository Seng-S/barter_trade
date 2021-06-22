/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.dao.impl;

import com.supinfo.dao.IProductDao;
import com.supinfo.entity.Product;
import com.supinfo.entity.Product_;
import com.supinfo.entity.User_;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Oudomsieng
 */
@Stateless
public class ProductJPADao implements IProductDao {
    @PersistenceContext(unitName = "SupBartering-ejbPU")
    private EntityManager em;
    
    @Override
    public Product getProductById(Long id){
        return em.find(Product.class, id);
    }

    @Override
    public List<Product> getProducts(String description, Float price) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        
        List<Predicate> predicates = new ArrayList<>();
       
        Predicate descriptionCond = cb.like(product.get(Product_.description), "%" + description + "%");
        predicates.add(descriptionCond);
        
        if(price != null){
            Predicate priceCond = cb.between(product.get(Product_.price), price - 10, price + 10);
            predicates.add(priceCond);
        }
        
        query.where(predicates.toArray(new Predicate[predicates.size()]))
                .orderBy(cb.desc(product.get(Product_.id)));;
        
        List<Product> products = em.createQuery(query).getResultList();
        return products;
    }

    @Override
    public List<Product> getProductsByUserId(Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        
        query.where(cb.equal(product.get(Product_.user).get(User_.id), userId));
        
        List<Product> products = em.createQuery(query).getResultList();
        
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        em.persist(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        em.merge(product);
        return product;
    }
    
    @Override
    public void deleteProduct(Long productId, Long userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> product = query.from(Product.class);
        
        Predicate productCond = cb.equal(product.get(Product_.id), productId);
        Predicate userCond = cb.equal(product.get(Product_.user).get(User_.id), userId);
        
        query.where(cb.and(productCond, userCond));
        
        List<Product> products = em.createQuery(query).getResultList();
        if(products.size() == 1){
            em.remove(products.get(0));
        }
    }
    
    @Override
    public Long countProducts() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        
        cq.select(cb.count(cq.from(Product.class)));
        
        return em.createQuery(cq).getSingleResult();
    }
}
