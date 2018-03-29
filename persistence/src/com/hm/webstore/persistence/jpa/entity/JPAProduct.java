package com.hm.webstore.persistence.jpa.entity;

import com.hm.webstore.domain.entity.Money;
import com.hm.webstore.domain.entity.Product;
import com.hm.webstore.domain.entity.ProductUnit;
import com.hm.webstore.domain.entity.StorageUnit;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "product")
public class JPAProduct extends Product {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<JPAProductUnit> units = new LinkedList<>();
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void addUnit(long quantity,
                        StorageUnit storageUnit,
                        Money purchasePrice) {
        JPAProductUnit unit = new JPAProductUnit();
        unit.setProduct(this);
        unit.setQuantity(quantity);
        unit.setStorageUnit((JPAStorageUnit) storageUnit);
        unit.setPurchasePriceAmount(purchasePrice.getAmount());
        unit.setPurchasePriceCurrency(purchasePrice.getCurrency());
        units.add(unit);
    }
    
    public List<? extends ProductUnit> getUnits() {
        return units;
    }
}
