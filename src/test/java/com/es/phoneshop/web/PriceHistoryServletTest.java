package com.es.phoneshop.web;

import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class PriceHistoryServletTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private PriceHistoryServlet servlet = new PriceHistoryServlet();

    private static volatile ProductService productService;

    @Before
    public void setup() {
        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet() throws ServletException, IOException, ProductNotFoundException {
       /* servlet.init();
        servlet.doGet(request, response);
        verify(requestDispatcher).forward(request, response);
        verify(request).setAttribute("priceHistory", productService.getProductFromDao("1L"));
    */}
}