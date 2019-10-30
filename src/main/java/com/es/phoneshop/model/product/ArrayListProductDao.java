package com.es.phoneshop.model.product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private List<Product> productList;

    public ArrayListProductDao() {
        productList = new ArrayList<>();
        fillProductList(productList);
    }

    public void fillProductList(List<Product> productList) {
        Currency usd = Currency.getInstance("USD");
        productList.add(new Product("Samsung Galaxy S", new BigDecimal(100), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
        productList.add(new Product("Samsung Galaxy S II", new BigDecimal(200), usd, 0, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg"));
        productList.add(new Product("Samsung Galaxy S III", new BigDecimal(300), usd, 5, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg"));
        productList.add(new Product("Apple iPhone", new BigDecimal(200), usd, 10, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg"));
        productList.add(new Product("Apple iPhone 6", new BigDecimal(1000), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone%206.jpg"));
        productList.add(new Product("HTC EVO Shift 4G", new BigDecimal(320), usd, 3, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/HTC/HTC%20EVO%20Shift%204G.jpg"));
        productList.add(new Product("Sony Ericsson C901", new BigDecimal(420), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg"));
        productList.add(new Product("Sony Xperia XZ", new BigDecimal(120), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Xperia%20XZ.jpg"));
        productList.add(new Product("Nokia 3310", new BigDecimal(70), usd, 100, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Nokia/Nokia%203310.jpg"));
        productList.add(new Product("Palm Pixi", new BigDecimal(170), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg"));
        productList.add(new Product("Siemens C56", new BigDecimal(70), usd, 20, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C56.jpg"));
        productList.add(new Product("Siemens C61", new BigDecimal(80), usd, 30, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C61.jpg"));
        productList.add(new Product("Siemens SXG75", new BigDecimal(150), usd, 40, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg"));
        productList.add(new Product("Siemens SXG75", new BigDecimal(150), usd, 40, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg"));
    }

    @Override
    public Optional<Product> getProduct(UUID id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id) && product.getPrice() != null && product.getStock() > 0)
                .findAny();
    }

    @Override
    public List<Product> findProducts() {
        return productList.stream()
                .filter(product -> product.getPrice() != null && product.getStock() > 0)
                .collect(Collectors
                        .toList());
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            product.setId(UUID.randomUUID());
            productList.add(product);
        }
        Optional<Product> optionalProduct = productList.stream()
                .filter(tempProduct -> tempProduct.getId().equals(product.getId()))
                .findAny();
        if (optionalProduct.isPresent()) {
            productList.add(productList.indexOf(optionalProduct.get()), product);
            productList.remove(optionalProduct.get());
        } else
            productList.add(product);
    }

    @Override
    public void delete(UUID id) {
        Optional<Product> optionalProduct = productList.stream()
                .filter(product -> product.getId().equals(id))
                .findAny();
        if (optionalProduct.isPresent()) {
            productList.remove(optionalProduct.get());
        }
    }
}
