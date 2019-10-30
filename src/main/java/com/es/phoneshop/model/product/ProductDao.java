package com.es.phoneshop.model.product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductDao {
    Optional<Product> getProduct(UUID id);

    List<Product> findProducts();

    void save(Product product);

    void delete(UUID id);
}
