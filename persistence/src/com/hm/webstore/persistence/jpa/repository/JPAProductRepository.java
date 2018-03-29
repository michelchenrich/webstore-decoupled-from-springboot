package com.hm.webstore.persistence.jpa.repository;

import com.hm.webstore.persistence.jpa.entity.JPAProduct;
import org.springframework.data.repository.CrudRepository;

public interface JPAProductRepository extends CrudRepository<JPAProduct, Long> {
}
