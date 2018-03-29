package com.hm.webstore.persistence.jpa.repository;

import com.hm.webstore.persistence.jpa.entity.JPAStorageUnit;
import org.springframework.data.repository.CrudRepository;

public interface JPAStorageUnitRepository extends CrudRepository<JPAStorageUnit, Long> {
}
