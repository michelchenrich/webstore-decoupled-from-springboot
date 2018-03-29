package com.hm.webstore.domain.gateway;

import com.hm.webstore.domain.entity.Product;

import java.util.List;

public interface ProductGateway {
    List<Product> findAll();
    
    Product findById(long id);
    
    Product makeNew();
    
    void save(Product product);
}
