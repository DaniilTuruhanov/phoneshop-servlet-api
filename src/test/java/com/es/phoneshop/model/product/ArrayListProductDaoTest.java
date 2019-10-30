package com.es.phoneshop.model.product;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ArrayListProductDaoTest {
    private ArrayListProductDao productDao;

    @Before
    public void setup() {
        productDao = new ArrayListProductDao();
    }

    @Test
    public void testFindProductsHaveSomeResults() {
        assertTrue(!productDao.findProducts().isEmpty());
    }

    @Test
    public void testGetProduct() {
        assertEquals(productDao.getProduct(productDao.findProducts().get(0).getId()).get().getId(), productDao.findProducts().get(0).getId());
    }

    @Test
    public void testFindProducts() {
        List<Product> productList = new ArrayList<>();
        Currency usd = Currency.getInstance("USD");
        assertTrue(!productDao.findProducts().stream().filter(product -> product.getPrice() == null || product.getStock() <= 0).findAny().isPresent());
    }

    @Test
    public void testSaveProducts() {
        Currency usd = Currency.getInstance("USD");
        Product product = new Product("Nokia 3521", new BigDecimal(200), usd, 300, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        productDao.save(product);
        assertEquals(product, productDao.findProducts().get(productDao.findProducts().size() - 1));
    }

    @Test
    public void testDeleteProduct() {
        Product product = productDao.findProducts().get(0);
        productDao.delete(product.getId());
        assertTrue(productDao.findProducts().lastIndexOf(product) == -1);
    }
}
