package com.hm.webstore.domain.entity;

public interface StorageUnitGateway {
    StorageUnit findById(long storageUnitId);
    
    StorageUnit makeNew();
    
    void save(StorageUnit storageUnit);
}
