package com.es.phoneshop.model.product;

import java.util.List;

public class Service {
    private ArrayListProductDao productDao;

    public Service() {
        this.productDao = new ArrayListProductDao();
    }

    public List<Product> productListFromDao(String query, String sortByField, String comparing) {
        return productDao.findProducts(query, sortByField, comparing);
    }
}
