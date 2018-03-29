package com.hm.webstore.delivery;

import com.hm.webstore.domain.entity.StorageUnit;
import com.hm.webstore.domain.entity.StorageUnitGateway;
import com.hm.webstore.domain.entity.StorageUnitPresenter;

public class StorageUnitOperations {
    private StorageUnitPresenter presenter;
    private StorageUnitGateway gateway;
    
    public StorageUnitOperations(StorageUnitPresenter presenter,
                                 StorageUnitGateway gateway) {
        this.presenter = presenter;
        this.gateway = gateway;
    }
    
    public Object createStorageUnit(String name) {
        StorageUnit storageUnit = gateway.makeNew();
        storageUnit.setName(name);
        gateway.save(storageUnit);
        return presenter.present(storageUnit);
    }
}
