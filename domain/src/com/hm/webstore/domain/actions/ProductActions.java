package com.hm.webstore.domain.actions;

import com.hm.webstore.domain.entity.Money;
import com.hm.webstore.domain.entity.Product;
import com.hm.webstore.domain.entity.StorageUnit;
import com.hm.webstore.domain.gateway.ProductGateway;
import com.hm.webstore.domain.gateway.StorageUnitGateway;
import com.hm.webstore.domain.presenter.ProductPresenter;

public class ProductActions {
    private ProductPresenter presenter;
    private ProductGateway gateway;
    private StorageUnitGateway storageUnitGateway;
    
    public ProductActions(ProductPresenter presenter,
                          ProductGateway gateway,
                          StorageUnitGateway storageUnitGateway) {
        this.presenter = presenter;
        this.gateway = gateway;
        this.storageUnitGateway = storageUnitGateway;
    }
    
    public Object presentAllProducts() {
        return presenter.present(gateway.findAll());
    }
    
    public Object presentProductDetail(long id) {
        return presenter.present(gateway.findById(id));
    }
    
    public Object createProduct(String name, String description) {
        Product product = gateway.makeNew();
        product.setName(name);
        product.setDescription(description);
        gateway.save(product);
        return presenter.present(product);
    }
    
    public Object addProductUnit(long productId,
                                 long quantity,
                                 long storageUnitId,
                                 double purchasePriceAmount,
                                 String purchasePriceCurrency) {
        StorageUnit storageUnit = storageUnitGateway.findById(storageUnitId);
        Product product = gateway.findById(productId);
        product.addUnit(quantity,
                        storageUnit,
                        new Money(purchasePriceAmount, purchasePriceCurrency));
        gateway.save(product);
        return presenter.present(product);
    }
}
