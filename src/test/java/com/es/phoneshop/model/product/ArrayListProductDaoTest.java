package com.es.phoneshop.model.product;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

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
        assertTrue(!productDao.findProducts(null, null, null).isEmpty());
    }

    @Test
    public void testGetProduct() {
        assertEquals(productDao.getProduct(1L).get(), productDao.findProducts(null, null, null).get(0));
    }

    @Test
    public void testFindProducts() {
        assertTrue(!productDao.findProducts(null, null, null).stream()
                .filter(product -> product.getPrice() == null || product.getStock() <= 0)
                .findAny().isPresent());
    }

    @Test
    public void testSaveProducts() {
        Currency usd = Currency.getInstance("USD");
        Product product = new Product(14L, "Nokia 3521", new BigDecimal(200), usd, 300, "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        productDao.save(product);
        assertEquals(product, productDao.findProducts(null, null, null).get(productDao.findProducts(null, null, null).size() - 1));
    }

    @Test
    public void testDeleteProduct() {
        Product product = productDao.findProducts(null, null, null).get(0);
        productDao.delete(product.getId());
        assertTrue(productDao.findProducts(null, null, null).lastIndexOf(product) == -1);
    }
}
