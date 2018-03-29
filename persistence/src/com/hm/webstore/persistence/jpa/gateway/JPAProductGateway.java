package com.hm.webstore.persistence.jpa.gateway;

import com.hm.webstore.domain.entity.Product;
import com.hm.webstore.domain.gateway.ProductGateway;
import com.hm.webstore.persistence.jpa.entity.JPAProduct;
import com.hm.webstore.persistence.jpa.repository.JPAProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Component
public class JPAProductGateway implements ProductGateway {
    private JPAProductRepository repository;
    
    @Autowired
    public JPAProductGateway(JPAProductRepository repository) {
        this.repository = repository;
    }
    
    public List<Product> findAll() {
        return stream(repository.findAll()).collect(toList());
    }
    
    public Product findById(long id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
    
    public Product makeNew() {
        return new JPAProduct();
    }
    
    public void save(Product product) {
        repository.save((JPAProduct) product);
    }
    
    private Stream<JPAProduct> stream(Iterable<JPAProduct> products) {
        return StreamSupport.stream(products.spliterator(), true);
    }
}
