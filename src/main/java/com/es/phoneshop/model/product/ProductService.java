package com.es.phoneshop.model.product;

import java.util.List;
import java.util.Optional;

public class ProductService {
    private static volatile ArrayListProductDao arrayListProductDao;

    private static volatile ProductService productServiceDao;

    private ProductService() {
        arrayListProductDao = arrayListProductDao.getInstance();
    }

    public static ProductService getInstance() {
        if (productServiceDao == null) {
            synchronized (ProductService.class) {
                if (productServiceDao == null)
                    productServiceDao = new ProductService();
            }
        }
        return productServiceDao;
    }

    public Product getProductFromDao(String id) throws ProductNotFoundException {
        Optional<Product> product = arrayListProductDao.getProduct(id);
        if (product.isPresent()) {
            return product.get();
        }
        else {
            throw new ProductNotFoundException();
        }
    }

    public List<Product> findProductsFromDao(String query, String sortByField, String comparing) {
        return arrayListProductDao.findProducts(query, sortByField, comparing);
    }

    public List<Product> findProductsFromDao() {
        return arrayListProductDao.findProducts(null, null, null);
    }

    public void saveFromDao(Product product) {
        arrayListProductDao.save(product);
    }

    public void deleteFromDao(String id) {
        arrayListProductDao.delete(id);
    }

    public ArrayListProductDao getProductDao() {
        return arrayListProductDao;
    }

    public void setProductDao(ArrayListProductDao productDao) {
        this.arrayListProductDao = productDao;
    }

    public static ProductService getProductServiceDao() {
        return productServiceDao;
    }

    public static void setProductServiceDao(ProductService productServiceDao) {
        ProductService.productServiceDao = productServiceDao;
    }
}