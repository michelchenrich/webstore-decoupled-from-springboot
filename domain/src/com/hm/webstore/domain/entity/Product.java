package com.hm.webstore.domain.entity;

import java.util.List;

public abstract class Product {
    public abstract long getId();
    
    public abstract String getName();
    
    public abstract String getDescription();
    
    public long getNumberOfUnits() {
        return getUnits().stream()
                         .map(ProductUnit::getQuantity)
                         .reduce((a, b) -> a + b)
                         .orElse((long) 0);
    }
    
    public Money getAverageUnitCost() {
        return getTotalCostOfStock().dividedBy(getNumberOfUnits());
    }
    
    public Money getTotalCostOfStock() {
        return getUnits().stream()
                         .map(ProductUnit::getTotalPurchasePrice)
                         .reduce(Money::plus)
                         .orElse(Money.ZERO);
    }
    
    public abstract List<? extends ProductUnit> getUnits();
    
    public abstract void setName(String name);
    
    public abstract void setDescription(String description);
    
    public abstract void addUnit(long quantity,
                                 StorageUnit storageUnit,
                                 Money purchasePrice);
}
