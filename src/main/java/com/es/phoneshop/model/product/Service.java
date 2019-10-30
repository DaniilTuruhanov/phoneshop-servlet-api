package com.es.phoneshop.model.product;

import java.util.List;

public class Service {
    private ArrayListProductDao arrayListProductDao;

    public Service() {
        this.arrayListProductDao = new ArrayListProductDao();
    }

    public List<Product> productListFromDao() {
        return arrayListProductDao.findProducts();
    }
}
