package com.es.phoneshop.model.product;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private static ArrayListProductDao arrayListProductDao;

    private static ProductService productService;

    private ProductService() {
        arrayListProductDao = arrayListProductDao.getInstance();
    }

    public static ProductService getInstance() {
        if (productService == null) {
            if (productService == null) {
                productService = new ProductService();
            }
        }
        return productService;
    }

    public Product getProduct(String id) throws ProductNotFoundException {
        Optional<Product> product = arrayListProductDao.getProduct(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new ProductNotFoundException();
        }
    }

    public List<Product> findProducts(String query, String sortByField, String comparing) {
        return arrayListProductDao.findProducts(query, sortByField, comparing);
    }

    public List<Product> findProducts() {
        return arrayListProductDao.findProducts(null, null, null);
    }

    public void save(Product product) {
        arrayListProductDao.save(product);
    }

    public void delete(String id) {
        arrayListProductDao.delete(id);
    }

    public ArrayListProductDao getProductDao() {
        return arrayListProductDao;
    }

    public void setProductDao(ArrayListProductDao productDao) {
        this.arrayListProductDao = productDao;
    }
}