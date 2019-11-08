package com.es.phoneshop.model.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private List<Product> productList;

    private static volatile ArrayListProductDao arrayListProductDao;

    private ArrayListProductDao() {
        productList = new ArrayList<>();
    }

    public static ArrayListProductDao getInstance() {
        if (arrayListProductDao == null) {
            arrayListProductDao = new ArrayListProductDao();
        }
        return arrayListProductDao;
    }

    private boolean containsAll(Product product, String query) {
        List<String> queryList = Arrays.asList(query.trim().split(" "));
        return queryList.stream()
                .anyMatch(queryPart -> Arrays.asList(product.getDescription().split(" ")).contains(queryPart));
    }

    @Override
    public Optional<Product> getProduct(String id) {
        synchronized (id) {
            return productList.stream()
                    .filter(product -> product.getId().equals(id))
                    .findAny();
        }
    }

    @Override
    public List<Product> findProducts(String query, String sortByField, String upOrDown) {
        synchronized (Product.class) {
            return productList.stream()
                    .filter(product -> product.getPrice() != null && product.getStock() > 0)
                    .filter(product -> query == null || query.trim().equals("") || containsAll(product, query)  /*!Collections.disjoint(Arrays.asList(product.getDescription().split(" ")), Arrays.asList(query.split(" "))*/)
                    .sorted((product1, product2) -> {
                        if (sortByField == null || upOrDown == null)
                            return 0;
                        if (sortByField.equals("DESCRIPTION") && upOrDown.equals("UP"))
                            return product1.getDescription().compareTo(product2.getDescription());
                        else if (sortByField.equals("DESCRIPTION") && upOrDown.equals("DOWN"))
                            return product2.getDescription().compareTo(product1.getDescription());
                        else if (sortByField.equals("PRICE") && upOrDown.equals("UP"))
                            return product1.getPrice().compareTo(product2.getPrice());
                        else if (sortByField.equals("PRICE") && upOrDown.equals("DOWN"))
                            return product2.getPrice().compareTo(product1.getPrice());
                        return 0;
                    })
                    .collect(Collectors.toList());
        }
    }

    public List<Product> findProducts() {
        synchronized (Product.class) {
            return productList.stream()
                    .filter(product -> product.getPrice() != null && product.getStock() > 0)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public void save(Product product) {
        synchronized (product.getId()) {
            if (product.getId() == null) {
                product.setId(UUID.randomUUID().toString());
                productList.add(product);
            }
            Set<Product> setProductList = new LinkedHashSet(productList);
            if (setProductList.contains(product)) {
                setProductList.remove(product);
                setProductList.add(product);
                productList = new ArrayList<>(setProductList);
            } else
                productList.add(product);
        }
    }

    @Override
    public void delete(String id) {
        synchronized (id) {
            Optional<Product> optionalProduct = productList.stream()
                    .filter(product -> product.getId().equals(id))
                    .findAny();
            if (optionalProduct.isPresent()) {
                productList.remove(optionalProduct.get());
            }
        }
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
