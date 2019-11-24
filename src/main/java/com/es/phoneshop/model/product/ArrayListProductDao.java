package com.es.phoneshop.model.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public class ArrayListProductDao implements ProductDao {
    private List<Product> productList;

    private static ArrayListProductDao arrayListProductDao;

    private ArrayListProductDao() {
        productList = new ArrayList<>();
        fillProductList();
    }

    public void fillProductList() {
        Currency usd = Currency.getInstance("USD");
        productList.add(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
        productList.add(new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg"));
        productList.add(new Product("3L", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(300), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(310), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(350), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg"));
        productList.add(new Product("4L", "Apple iPhone", new BigDecimal(200), usd, 10, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg"));
        productList.add(new Product("5L", "Apple iPhone 6", new BigDecimal(1000), usd, 30, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(1000), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(1100), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(1500), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone%206.jpg"));
        productList.add(new Product("6L", "HTC EVO Shift 4G", new BigDecimal(320), usd, 3, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(320), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(330), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(370), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/HTC/HTC%20EVO%20Shift%204G.jpg"));
        productList.add(new Product("7L", "Sony Ericsson C901", new BigDecimal(420), usd, 30, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(420), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(430), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(470), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Ericsson%20C901.jpg"));
        productList.add(new Product("8L", "Sony Xperia XZ", new BigDecimal(120), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(120), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(130), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(170), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Sony/Sony%20Xperia%20XZ.jpg"));
        productList.add(new Product("9L", "Nokia 3310", new BigDecimal(70), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(70), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(80), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(120), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Nokia/Nokia%203310.jpg"));
        productList.add(new Product("10L", "Palm Pixi", new BigDecimal(170), usd, 30, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(170), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(180), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(230), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Palm/Palm%20Pixi.jpg"));
        productList.add(new Product("11L", "Siemens C56", new BigDecimal(70), usd, 20, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(70), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(80), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(120), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C56.jpg"));
        productList.add(new Product("12L", "Siemens C61", new BigDecimal(80), usd, 30, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(80), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(90), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(130), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20C61.jpg"));
        productList.add(new Product("13L", "Siemens SXG75", new BigDecimal(150), usd, 40, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(150), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(160), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(200), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Siemens/Siemens%20SXG75.jpg"));
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

    public List<Product> findProducts() {
        return productList.stream()
                .filter(product -> product.getPrice() != null && product.getStock() > 0)
                .collect(Collectors.toList());
    }

    public int chooseComparator(Product product1, Product product2, String sortByField, String upOrDown) {
        if (sortByField == null || upOrDown == null) {
            return 0;
        }
        if (sortByField.equals("DESCRIPTION") && upOrDown.equals("UP")) {
            return product1.getDescription().compareTo(product2.getDescription());
        } else if (sortByField.equals("DESCRIPTION") && upOrDown.equals("DOWN")) {
            return product2.getDescription().compareTo(product1.getDescription());
        } else if (sortByField.equals("PRICE") && upOrDown.equals("UP")) {
            return product1.getPrice().compareTo(product2.getPrice());
        } else if (sortByField.equals("PRICE") && upOrDown.equals("DOWN")) {
            return product2.getPrice().compareTo(product1.getPrice());
        }
        return 0;
    }

    private List<Product> sortProducts(List<Product> productList, String sortByField, String upOrDown) {
        return productList.stream()
                .sorted((product1, product2) -> chooseComparator(product1, product2, sortByField, upOrDown))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getProduct(String id) {
        synchronized (SynchronizeMap.findKey(id)) {
            return productList.stream()
                    .filter(product -> product.getId().equals(id))
                    .findAny();
        }
    }

    @Override
    public List<Product> findProducts(String query, String sortByField, String upOrDown) {
        List<Product> products = findProducts();
        products = products.stream()
                .filter(product -> query == null || query.trim().equals("") || containsAll(product, query))
                .collect(Collectors.toList());
        return sortProducts(products, sortByField, upOrDown);
    }

    @Override
    public void save(Product product) {
        synchronized (SynchronizeMap.findKey(product.getId())) {
            if (product.getId() == null) {
                product.setId(UUID.randomUUID().toString());
                productList.add(product);
            } else {
                if (productList.contains(product)) {
                    productList.set(productList.indexOf(product), product);
                } else {
                    productList.add(product);
                }
            }
        }
    }

    @Override
    public void delete(String id) {
        synchronized (SynchronizeMap.findKey(id)) {
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
