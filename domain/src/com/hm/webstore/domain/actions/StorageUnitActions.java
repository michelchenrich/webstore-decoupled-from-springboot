package com.hm.webstore.domain.actions;

import com.hm.webstore.domain.entity.StorageUnit;
import com.hm.webstore.domain.gateway.StorageUnitGateway;
import com.hm.webstore.domain.presenter.StorageUnitPresenter;

public class StorageUnitActions {
    private StorageUnitPresenter presenter;
    private StorageUnitGateway gateway;
    
    public StorageUnitActions(StorageUnitPresenter presenter,
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
