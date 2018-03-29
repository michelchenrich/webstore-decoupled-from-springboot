package com.hm.webstore.domain.entity;

public abstract class ProductUnit {
    public abstract long getQuantity();
    
    public abstract Money getPurchasePrice();
    
    public Money getTotalPurchasePrice() {
        return getPurchasePrice().multipliedBy(getQuantity());
    }
}
