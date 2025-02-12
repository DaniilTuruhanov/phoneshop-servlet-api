package com.es.phoneshop.model.product;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ArrayListProductDaoTest {
    private ArrayListProductDao productDao = ArrayListProductDao.getInstance();

    @Test
    public void returnEmptyWhenSomeProductsDoesNotExistInDao() {
        productDao.setProductList(Collections.emptyList());

        assertTrue(productDao.findProducts().isEmpty());
    }

    @Test
    public void returnEmptyWhenProductsHaveEmptyStock() {
        Currency usd = Currency.getInstance("USD");
        List<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 0, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"), (new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg"))));

        productDao.setProductList(productArrayList);

        System.out.println(productDao.findProducts());
        assertTrue(productDao.findProducts().isEmpty());
    }

    @Test
    public void returnProductWhenProductExistInDao() {
        Currency usd = Currency.getInstance("USD");
        List<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"), (new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")), (new Product("3L", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(300), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(310), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(350), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg")), (new Product("4L", "Apple iPhone", new BigDecimal(200), usd, 10, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg"))));
        Product product = new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");

        productDao.setProductList(productArrayList);

        assertEquals(product, productDao.getProduct("1L").get());
    }

    @Test
    public void isPresentReturnFalseWhenProductDoesNotExistInDao() {
        Currency usd = Currency.getInstance("USD");
        List<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"), (new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")), (new Product("3L", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(300), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(310), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(350), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg")), (new Product("4L", "Apple iPhone", new BigDecimal(200), usd, 10, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg"))));

        productDao.setProductList(productArrayList);

        assertFalse(productDao.getProduct("1").isPresent());
    }

    @Test
    public void returnProductsWhenDaoHasSomeProducts() {
        Currency usd = Currency.getInstance("USD");
        List<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg")));
        List<Product> findProductArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"), new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")));

        productDao.setProductList(findProductArrayList);

        assertEquals(productArrayList, productDao.findProducts());
    }

    @Test
    public void saveProductWhenThisProductExistInDao() {
        Currency usd = Currency.getInstance("USD");
        List<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg")));
        Product product = new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");

        productDao.setProductList(productArrayList);
        productDao.save(product);

        assertTrue(productDao.getProductList().contains(product));
    }

    @Test
    public void deleteProductWhenThisProductExistInDao() {
        Currency usd = Currency.getInstance("USD");
        List<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg")));

        productDao.setProductList(productArrayList);
        productDao.delete("1L");

        assertTrue(productDao.getProductList().isEmpty());
    }
}
