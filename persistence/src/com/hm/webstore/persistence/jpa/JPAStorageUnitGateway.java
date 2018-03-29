package com.hm.webstore.persistence.jpa;

import com.hm.webstore.domain.entity.StorageUnit;
import com.hm.webstore.domain.entity.StorageUnitGateway;
import com.hm.webstore.persistence.jpa.entity.JPAStorageUnit;
import com.hm.webstore.persistence.jpa.repository.JPAStorageUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JPAStorageUnitGateway implements StorageUnitGateway {
    private JPAStorageUnitRepository repository;
    
    @Autowired
    public JPAStorageUnitGateway(JPAStorageUnitRepository repository) {
        this.repository = repository;
    }
    
    
    public StorageUnit findById(long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    public StorageUnit makeNew() {
        return new JPAStorageUnit();
    }
    
    public void save(StorageUnit storageUnit) {
        repository.save((JPAStorageUnit) storageUnit);
    }
}
