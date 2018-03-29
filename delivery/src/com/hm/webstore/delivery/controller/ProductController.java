package com.hm.webstore.delivery.controller;

import com.hm.webstore.domain.gateway.ProductGateway;
import com.hm.webstore.domain.actions.ProductActions;
import com.hm.webstore.domain.presenter.ProductPresenter;
import com.hm.webstore.domain.gateway.StorageUnitGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController extends ProductActions {
    @Autowired
    public ProductController(ProductPresenter presenter,
                             ProductGateway gateway,
                             StorageUnitGateway storageUnitGateway) {
        super(presenter, gateway, storageUnitGateway);
    }
    
    @GetMapping("/products")
    public Object presentAllProducts() {
        return super.presentAllProducts().toString();
    }
    
    @GetMapping("/products/{id}")
    public Object presentProductDetail(@PathVariable("id") long id) {
        return super.presentProductDetail(id).toString();
    }
    
    @PostMapping("/products")
    public Object createProduct(@RequestParam("name") String name,
                                @RequestParam("description") String description) {
        return super.createProduct(name, description).toString();
    }
    
    @PostMapping("/products/{productId}/{storageUnitId}")
    public Object addProductUnit(@PathVariable("productId") long productId,
                                 @PathVariable("storageUnitId") long storageUnitId,
                                 @RequestParam(name = "quantity", defaultValue = "1") long quantity,
                                 @RequestParam(name = "purchasePriceAmount") double purchasePriceAmount,
                                 @RequestParam(name = "purchasePriceCurrency", defaultValue = "${default.currency}") String purchasePriceCurrency) {
        return super.addProductUnit(productId,
                                    quantity,
                                    storageUnitId,
                                    purchasePriceAmount,
                                    purchasePriceCurrency).toString();
    }
}
