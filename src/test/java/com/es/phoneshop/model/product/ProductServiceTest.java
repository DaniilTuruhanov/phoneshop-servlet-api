package com.es.phoneshop.model.product;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProductServiceTest {
    private static ProductService productService = ProductService.getInstance();
    private static ArrayListProductDao productDao = ArrayListProductDao.getInstance();

    @Test
    public void testFindProductsHaveSomeResults() {
        Currency usd = Currency.getInstance("USD");
        productDao.setProductList(new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"),
                (new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(200), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")),
                (new Product("3L", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(300), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(310), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(350), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg")),
                (new Product("4L", "Apple iPhone", new BigDecimal(200), usd, 10, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(200), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg")))));
        productService.setProductDao(productDao);
        assertTrue(!productService.findProductsFromDao().isEmpty());
    }

    @Test
    public void testGetProduct() throws ProductNotFoundException {
        Currency usd = Currency.getInstance("USD");
        productDao.setProductList(new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"),
                (new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(200), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")),
                (new Product("3L", "Samsung Galaxy S III", new BigDecimal(300), usd, 5, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(300), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(310), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(350), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20III.jpg")),
                (new Product("4L", "Apple iPhone", new BigDecimal(200), usd, 10, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(200), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Apple/Apple%20iPhone.jpg")))));
        productService.setProductDao(productDao);
        assertEquals(productService.getProductFromDao("1L"), new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"));
    }

    @Test
    public void testFindProducts() {
        Currency usd = Currency.getInstance("USD");
        productDao.setProductList(new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"),
                (new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(200), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")))));
        productService.setProductDao(productDao);
        assertEquals(productService.findProductsFromDao(), new ArrayList<Product>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"))));
    }

    @Test
    public void testSaveProducts() {
        Currency usd = Currency.getInstance("USD");
        Product product = new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        productDao.setProductList(new ArrayList<>());
        productService.setProductDao(productDao);
        productService.saveFromDao(product);
        assertTrue(productService.getProductDao().getProductList().equals(new ArrayList<Product>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg")))));
    }

    @Test
    public void testDeleteProduct() {
        Currency usd = Currency.getInstance("USD");
        productDao.setProductList(new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(new GregorianCalendar(2019, 0, 10), new BigDecimal(100), usd), new PriceRecord(new GregorianCalendar(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(new GregorianCalendar(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"))));
        productService.setProductDao(productDao);
        productService.deleteFromDao("1L");
        assertTrue(productService.getProductDao().getProductList().isEmpty());
    }
}
