package com.es.phoneshop.model.product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    Optional<Product> getProduct(String id);

    List<Product> findProducts(String query, String sortByField, String comparing);

    void save(Product product);

    void delete(String id);
}
