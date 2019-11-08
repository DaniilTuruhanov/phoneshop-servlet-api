package com.es.phoneshop.web;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpUpgradeHandler;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class ProductDetailPageServletTest {
    private final static String path = "/WEB-INF/pages/productPage.jsp";
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private RequestDispatcher requestDispatcher;

    private ProductDetailPageServlet servlet = new ProductDetailPageServlet();
    @Mock
    private ProductService productService;

    @Before
    public void setup() {
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
    }

    @Test
    public void testDoGet() throws ServletException, IOException, ProductNotFoundException {
        String idProduct = "idProductValue";
        Product product = new Product();
        servlet.setProductService(productService);

        when(request.getRequestURI()).thenReturn(idProduct);
        when(productService.getProductFromDao(idProduct.substring(idProduct.lastIndexOf("/") + 1))).thenReturn(product);

        servlet.doGet(request, response);

        verify(request).setAttribute("product", product);
    }
}