package com.hm.webstore.persistence.jpa.entity;

import com.hm.webstore.domain.entity.StorageUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "storage_unit")
public class JPAStorageUnit extends StorageUnit {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
}
