package com.hm.webstore;

import com.hm.webstore.domain.entity.Money;
import com.hm.webstore.persistence.jpa.entity.JPAProduct;
import com.hm.webstore.persistence.jpa.entity.JPAStorageUnit;
import com.hm.webstore.persistence.jpa.repository.JPAProductRepository;
import com.hm.webstore.persistence.jpa.repository.JPAStorageUnitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] arguments) {
        SpringApplication.run(Application.class);
    }
    
    @Bean public CommandLineRunner getRunner(JPAProductRepository productRepository,
                                             JPAStorageUnitRepository storageUnitRepository) {
        return arguments -> {
            JPAStorageUnit storageUnit = new JPAStorageUnit();
            storageUnit.setName("Shelf A");
            storageUnitRepository.save(storageUnit);
            
            JPAProduct product = new JPAProduct();
            product.setName("Banana");
            product.setDescription("From SAP's Coffee Corner");
            product.addUnit(10, storageUnit,
                            new Money(100.0, "USD"));
            productRepository.save(product);
        };
    }
}
