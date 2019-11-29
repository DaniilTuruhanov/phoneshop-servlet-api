package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.ProductNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class QuantityValidatorTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    private Validator validator = QuantityValidator.getInstance();

    @Test
    public void setEmptyErrorMapWhenNoException() throws ProductNotFoundException {
        String quantity = "1";
        ErrorMap errorMap = new ErrorMap();
        ErrorMap result = new ErrorMap();

        when(request.getParameter("quantity")).thenReturn(quantity);

        validator.validate(quantity, errorMap);
        assertEquals(errorMap, result);
    }

    @Test
    public void setParseExceptionErrorInMapWhenQuantityString() throws ProductNotFoundException {
        String quantity = "a";
        ErrorMap errorMap = new ErrorMap();
        ErrorMap result = new ErrorMap();
        result.addError("quantity", "Not a number!!!");

        when(request.getParameter("quantity")).thenReturn(quantity);

        validator.validate(quantity, errorMap);
        assertEquals(errorMap, result);
    }
}
