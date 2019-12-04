package com.es.phoneshop.web;

import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.ParseString;
import com.es.phoneshop.model.product.Comment;
import com.es.phoneshop.model.product.CommentValidator;
import com.es.phoneshop.model.product.PriceRecord;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class CommentServletTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private ProductService productService;

    @Mock
    private CommentValidator commentValidator;

    @Mock
    private PrintWriter printWriter;

    @Mock
    private ParseString parseString;

    private CommentServlet servlet = new CommentServlet();

    private ObjectMapper obj = new ObjectMapper();

    private List<Product> products = Collections.singletonList(new Product());

    @Test
    public void doPostTest() throws ServletException, IOException, ProductNotFoundException {
        String idProduct = "1L";
        Currency usd = Currency.getInstance(Locale.US);
        servlet.setProductService(productService);
        servlet.setCommentValidator(commentValidator);
        Product product = new Product(idProduct, "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        String name = "name";
        String rating = "5";
        String comment = "comment";
        ErrorMap errorMap = new ErrorMap();

        when(request.getParameter("Name")).thenReturn(name);
        when(request.getParameter("Rating")).thenReturn(rating);
        when(request.getParameter("Comment")).thenReturn(comment);
        when(productService.getProduct(idProduct.substring(idProduct.lastIndexOf("/") + 1))).thenReturn(product);

        servlet.doPost(request, response);

        verify(request).setAttribute("errorMap", errorMap);
    }
}
