package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Product;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ProductListPageServletTest {
    private final static String path = "/WEB-INF/pages/productList.jsp";

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Mock
    private ProductService productService;

    private ProductListPageServlet servlet = new ProductListPageServlet();


    @Before
    public void setup() {
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
    }

    @Test
    public void setAttributeWhenProductListPageServletDoGet() throws ServletException, IOException {
        servlet.setProductService(productService);

        String field = "fieldValue";
        String query = "queryValue";
        String upOrDown = "upOrDownValue";
        List<Product> products = Collections.singletonList(new Product());

        when(request.getParameter("query")).thenReturn(query);
        when(request.getParameter("order")).thenReturn(field);
        when(request.getParameter("sort")).thenReturn(upOrDown);
        when(productService.findProducts(query, field, upOrDown)).thenReturn(products);

        servlet.doGet(request, response);

        verify(request).setAttribute("products", products);
    }
}