package com.hm.webstore.delivery.presenter;

import com.google.gson.JsonObject;
import com.hm.webstore.domain.entity.StorageUnit;
import com.hm.webstore.domain.presenter.StorageUnitPresenter;
import org.springframework.stereotype.Component;

@Component
public class JSONStorageUnitPresenter implements StorageUnitPresenter {
    public Object present(StorageUnit storageUnit) {
        JsonObject object = new JsonObject();
        object.addProperty("id", storageUnit.getId());
        object.addProperty("name", storageUnit.getName());
        return object;
    }
}
