package com.es.phoneshop.model.product;

import java.util.HashMap;
import java.util.Map;

public class SynchronizeMap {
    private Map<String, Object> uniqueObject = new HashMap<>();

    public Object findKey(String id) {
        if (uniqueObject.containsKey(id)) {
            return uniqueObject.get(id);
        } else {
            Object object = new Object();
            uniqueObject.put(id, object);
            return object;
        }
    }
}