/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.beans;

import com.supinfo.entity.Product;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Oudomsieng
 */

@Remote
public interface ProductService {
    Product getProductById(Long id);
    List<Product> getProducts(String description, Float price);
    List<Product> getProductsByUserId(Long userId);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void deleteProduct(Long productId, Long userId);
    Long countProducts();
}
