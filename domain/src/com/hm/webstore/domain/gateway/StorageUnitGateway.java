package com.hm.webstore.domain.gateway;

import com.hm.webstore.domain.entity.StorageUnit;

public interface StorageUnitGateway {
    StorageUnit findById(long storageUnitId);
    
    StorageUnit makeNew();
    
    void save(StorageUnit storageUnit);
}
