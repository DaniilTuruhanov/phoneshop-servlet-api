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
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class QuantityValidatorTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProductService productService;

    private Validator validator = QuantityValidator.getInstance();

    @Test
    public void setEmptyErrorMapWhenNoException() throws ProductNotFoundException {
        Locale locale = Locale.US;
        String quantity = "1";
        Map<String, ArrayList<String>> errorMap = Collections.emptyMap();
        Map<String, ArrayList<String>> result = Collections.emptyMap();

        when(request.getLocale()).thenReturn(locale);
        when(request.getParameter("quantity")).thenReturn(quantity);

        validator.validate(request, response, errorMap);
        assertEquals(errorMap, result);
    }

    @Test
    public void setParseExceptionErrorInMapWhenQuantityString() throws ProductNotFoundException {
        Locale locale = Locale.US;
        String quantity = "a";
        ArrayList<String> errors = new ArrayList<>();
        errors.add("Not a number!!!");
        Map<String, ArrayList<String>> errorMap = new HashMap<>();
        Map<String, ArrayList<String>> result = new HashMap<>();
        result.put("quantity", errors);

        when(request.getLocale()).thenReturn(locale);
        when(request.getParameter("quantity")).thenReturn(quantity);

        validator.validate(request, response, errorMap);
        assertEquals(errorMap, result);
    }
}
