/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.beans;

import com.supinfo.dao.IProductDao;
import com.supinfo.entity.Product;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author Oudomsieng
 */

@Stateful
public class ProductServiceBean implements ProductService {

    @EJB
    private IProductDao productDao;
    
    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getProducts(String description, Float price) {
        return productDao.getProducts(description, price);
    }

    @Override
    public List<Product> getProductsByUserId(Long userId) {
        return productDao.getProductsByUserId(userId);
    }

    @Override
    public Product addProduct(Product product) {
        return productDao.addProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productDao.updateProduct(product);
    }
    
    @Override
    public void deleteProduct(Long productId, Long userId) {
        productDao.deleteProduct(productId, userId);
    }

    @Override
    public Long countProducts() {
        return productDao.countProducts();
    }
}
