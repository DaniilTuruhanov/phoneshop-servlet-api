package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.PriceRecord;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class QuantityValidatorTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private ProductService productService;

    private Validator validator = new QuantityValidator();

 /*   @Test
    public void setEmptyErrorMapWhenNoException() throws ProductNotFoundException {
        String idProduct = "1L";
        Locale locale = Locale.US;
        String quantity = "1";
        Map<String, String> errorMap = Collections.emptyMap();
        Cart cart = new Cart();

        when(request.getLocale()).thenReturn(locale);

        Map result = validator.validate(request.getLocale(), cart, idProduct, quantity);
        assertEquals(errorMap, result);
    }

    @Test
    public void setParseExceptionErrorInMapWhenQuantityString() throws ProductNotFoundException {
        Cart cart = new Cart();
        String idProduct = "1L";
        Locale locale = Locale.US;
        String quantity = "a";
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("error", "Not a number!!!");

        when(request.getLocale()).thenReturn(locale);

        Map result = validator.validate();
        assertEquals(errorMap, result);
    }

    @Test
    public void setOutOfStockErrorInMapWhenQuantityMoreThanStock() throws ProductNotFoundException {
        Cart cart = new Cart();
        String idProduct = "1L";
        Locale locale = Locale.US;
        String quantity = "10000";
        Currency usd = Currency.getInstance("USD");
        Product product = new Product(idProduct, "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        Map<String, String> errorMap = new HashMap<>();

        when(request.getLocale()).thenReturn(locale);
        when(productService.getProduct(idProduct)).thenReturn(product);
        errorMap.put("error", "Not enough stock. " + "Stock: " + productService.getProduct(idProduct).getStock());

        Map result = validator.validate(request.getLocale(), cart, idProduct, quantity);
        assertEquals(errorMap, result);
    }*/
}
