package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SortProductServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private ProductService productService;

    @Mock
    private PrintWriter printWriter;

    private SortProductServlet servlet = new SortProductServlet();

    private ObjectMapper obj = new ObjectMapper();

    private List<Product> products = Collections.singletonList(new Product());

    @Test
    public void testDoGet() throws ServletException, IOException {
        servlet.setProductService(productService);
        String field = "fieldValue";
        String query = "queryValue";
        String upOrDown = "upOrDownValue";

        when(request.getParameter("query")).thenReturn(query);
        when(request.getParameter("order")).thenReturn(field);
        when(request.getParameter("sort")).thenReturn(upOrDown);
        when(productService.findProducts(query, field, upOrDown)).thenReturn(products);
        when(response.getWriter()).thenReturn(printWriter);

        servlet.doGet(request, response);

        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");
        verify(printWriter).print((obj).writeValueAsString(products));
        verify(printWriter).flush();
    }
}