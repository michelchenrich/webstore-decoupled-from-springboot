package com.hm.webstore.domain.entity;

import java.util.List;

public interface ProductGateway {
    List<Product> findAll();
    
    Product findById(long id);
    
    Product makeNew();
    
    void save(Product product);
}
