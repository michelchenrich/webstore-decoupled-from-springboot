package com.hm.webstore.domain.entity;

import java.util.List;

public interface ProductPresenter {
    Object present(List<Product> all);
    
    Object present(Product product);
}
