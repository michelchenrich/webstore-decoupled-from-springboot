package com.hm.webstore.persistence.jpa.entity;

import com.hm.webstore.domain.entity.Money;
import com.hm.webstore.domain.entity.Product;
import com.hm.webstore.domain.entity.ProductUnit;
import com.hm.webstore.domain.entity.StorageUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "product_unit")
public class JPAProductUnit extends ProductUnit {
    @Id
    @GeneratedValue
    public long id;
    @ManyToOne
    public JPAProduct product;
    @ManyToOne
    private JPAStorageUnit storageUnit;
    private long quantity;
    private double purchasePriceAmount;
    private String purchasePriceCurrency;
    
    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
    
    public void setStorageUnit(JPAStorageUnit storageUnit) {
        this.storageUnit = storageUnit;
    }
    
    public void setProduct(JPAProduct product) {
        this.product = product;
    }
    
    public long getQuantity() {
        return quantity;
    }
    
    public Money getPurchasePrice() {
        return new Money(purchasePriceAmount, purchasePriceCurrency);
    }
    
    public void setPurchasePriceAmount(double purchasePriceAmount) {
        this.purchasePriceAmount = purchasePriceAmount;
    }
    
    public void setPurchasePriceCurrency(String purchasePriceCurrency) {
        this.purchasePriceCurrency = purchasePriceCurrency;
    }
}
