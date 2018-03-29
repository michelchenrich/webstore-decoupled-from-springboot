package com.hm.webstore.delivery.presenter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hm.webstore.domain.entity.Product;
import com.hm.webstore.domain.presenter.ProductPresenter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JSONProductPresenter implements ProductPresenter {
    public Object present(List<Product> all) {
        JsonArray array = new JsonArray();
        for (Product product : all)
            array.add((JsonElement) present(product));
        return array;
    }
    
    public Object present(Product product) {
        JsonObject object = new JsonObject();
        object.addProperty("id", product.getId());
        object.addProperty("name", product.getName());
        object.addProperty("description", product.getDescription());
        object.addProperty("numberOfUnits", product.getNumberOfUnits());
        object.addProperty("averageUnitCost", product.getAverageUnitCost().toString());
        object.addProperty("totalCostOfStock", product.getTotalCostOfStock().toString());
        return object;
    }
}
