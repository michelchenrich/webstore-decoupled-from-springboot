package com.hm.webstore.domain.presenter;

import com.hm.webstore.domain.entity.Product;

import java.util.List;

public interface ProductPresenter {
    Object present(List<Product> all);
    
    Object present(Product product);
}
