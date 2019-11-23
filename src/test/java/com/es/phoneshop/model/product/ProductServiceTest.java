package com.es.phoneshop.model.product;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ProductServiceTest {
    private static ProductService productService = ProductService.getInstance();

    @Mock
    private ArrayListProductDao productDao;

    @Test
    public void returnEmptyWhenSomeProductsDoesNotExistInDao() {
        when(productDao.getProductList()).thenReturn(Collections.emptyList());

        productService.getProductDao().setProductList(productDao.getProductList());
        assertFalse(productService.findProducts().isEmpty());
    }

    @Test
    public void returnProductWhenProductExistInService() throws ProductNotFoundException {
        Currency usd = Currency.getInstance("USD");
        String idProduct = "1L";
        Product product = new Product(idProduct, "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");

        when(productDao.getProduct(idProduct)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(idProduct);
        assertEquals(product, result);
    }

    @Test(expected = ProductNotFoundException.class)
    public void throwExceptionWhenProductDoesNotExistInService() throws ProductNotFoundException {
        String idProduct = "1";

        when(productDao.getProduct(idProduct)).thenReturn(Optional.empty());

        productService.getProduct(idProduct);
    }

    @Test
    public void returnProductsWhenServiceHasSomeProducts() {
        Currency usd = Currency.getInstance("USD");
        List<Product> productArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg")));
        List<Product> findProductArrayList = new ArrayList<>(Arrays.asList(new Product("1L", "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg"), new Product("2L", "Samsung Galaxy S II", new BigDecimal(200), usd, 0, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(200), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(210), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(250), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S%20II.jpg")));

        when(productDao.getProductList()).thenReturn(findProductArrayList);

        productService.getProductDao().setProductList(productDao.getProductList());
        assertEquals(new HashSet<>(productArrayList), new HashSet<>(productService.findProducts()));
    }
}
