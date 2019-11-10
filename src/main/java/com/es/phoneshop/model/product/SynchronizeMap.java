package com.es.phoneshop.model.product;

import java.util.HashMap;
import java.util.Map;

public class SynchronizeMap {
    private Map<String, Object> uniqueObject = new HashMap<>();

    public String findKey(String id) {
        if (uniqueObject.containsKey(id)) {
            return id;
        } else {
            Product product = new Product();
            product.setId(id);
            uniqueObject.put(product.getId(), product);
            return product.getId();
        }
    }
}
