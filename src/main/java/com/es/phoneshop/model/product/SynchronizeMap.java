package com.es.phoneshop.model.product;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SynchronizeMap {
    private static Map<String, Object> uniqueObject = new HashMap<>();

    public static Object findKey(String id) {
        if (id == null) {
            Object object = new Object();
            return object;
        }
        if (uniqueObject.containsKey(id)) {
            return uniqueObject.get(id);
        } else {
            Object object = new Object();
            uniqueObject.put(id, object);
            return object;
        }
    }
}