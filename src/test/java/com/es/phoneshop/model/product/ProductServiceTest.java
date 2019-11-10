package com.es.phoneshop.model.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest {
    private static ProductService productService = ProductService.getInstance();

    @Mock
    private ArrayListProductDao productDao;

    @Test
    public void testGetProduct() throws ProductNotFoundException {
        Currency usd = Currency.getInstance("USD");
        Product product = new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");

        assertEquals(productService.getProduct("1L"), product);
    }

    @Test
    public void testFindProducts() {
        Currency usd = Currency.getInstance("USD");
        ArrayList<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg")));
        ArrayList<Product> findProductArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"), new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")));

        when(productDao.getProductList()).thenReturn(findProductArrayList);

        productService.getProductDao().setProductList(productDao.getProductList());

        assertEquals(productService.findProducts(), productArrayList);
    }

    @Test
    public void testSaveProducts() {
        Currency usd = Currency.getInstance("USD");
        Product product = new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");

        productService.save(product);

        assertTrue(productService.getProductDao().getProductList().contains(product));
    }

    @Test
    public void testDeleteProduct() {
        Currency usd = Currency.getInstance("USD");
        ArrayList<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<PriceRecord>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg")));

        when(productDao.getProductList()).thenReturn(productArrayList);

        productService.getProductDao().setProductList(productDao.getProductList());
        productService.delete("1L");

        assertTrue(productService.getProductDao().getProductList().isEmpty());
    }
}
