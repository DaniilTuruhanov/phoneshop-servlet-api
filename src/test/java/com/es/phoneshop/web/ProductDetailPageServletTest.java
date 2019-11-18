package com.es.phoneshop.web;

import com.es.phoneshop.cart.Cart;
import com.es.phoneshop.cart.CartService;
import com.es.phoneshop.cart.HttpSessionCartService;
import com.es.phoneshop.cart.OutOfStockException;
import com.es.phoneshop.cart.QuantityValidator;
import com.es.phoneshop.cart.RecentlyViewedProducts;
import com.es.phoneshop.cart.Validator;
import com.es.phoneshop.model.product.PriceRecord;
import com.es.phoneshop.model.product.Product;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;

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

    @Mock
    private ProductService productService;

    @Mock
    private HttpSessionCartService cartService;

    @Mock
    private HttpSession session;

    private ProductDetailPageServlet servlet = new ProductDetailPageServlet();

    @Before
    public void setup() {
        when(request.getRequestDispatcher(path)).thenReturn(requestDispatcher);
    }

    @Test
    public void setAttributeWhenProductDetailPageServletDoGet() throws ServletException, IOException, ProductNotFoundException {
        String idProduct = "idProductValue";
        Currency usd = Currency.getInstance("USD");
        Product product = new Product(idProduct, "Samsung Galaxy S", new BigDecimal(100), usd, 100, new ArrayList<>(Arrays.asList(new PriceRecord(LocalDate.of(2019, 1, 10), new BigDecimal(100), usd), new PriceRecord(LocalDate.of(2018, 9, 10), new BigDecimal(110), usd), new PriceRecord(LocalDate.of(2018, 8, 1), new BigDecimal(150), usd))), "https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/manufacturer/Samsung/Samsung%20Galaxy%20S.jpg");
        RecentlyViewedProducts recentlyViewedProducts = new RecentlyViewedProducts();
        servlet.setProductService(productService);

        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn(idProduct);
        when(productService.getProduct(idProduct.substring(idProduct.lastIndexOf("/") + 1))).thenReturn(product);
        when(session.getAttribute("objectRecentlyViewed")).thenReturn(recentlyViewedProducts);

        servlet.doGet(request, response);
        verify(session).setAttribute("recentlyViewed", recentlyViewedProducts.getProductQueue());
        verify(session).setAttribute("objectRecentlyViewed", recentlyViewedProducts);
        verify(request).setAttribute("product", product);
    }

    @Test
    public void addCartItemWhenProductDetailPageDoPost() throws ServletException, IOException, ProductNotFoundException, OutOfStockException {
        String quantityString = "1";
        String id = "1L";
        Cart cart = new Cart();
        Locale locale = Locale.US;
        servlet.setProductService(productService);
        servlet.setCartService(cartService);

        when(request.getLocale()).thenReturn(locale);
        when(request.getParameter("quantity")).thenReturn(quantityString);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn(id);
        when(cartService.getCart(session)).thenReturn(cart);

        servlet.doPost(request, response);
        verify(cartService).addCartItem(cart, id, Integer.valueOf(quantityString));
        verify(session).setAttribute("cart", cart);
    }

    @Test
    public void setErrorNotANumberWhenProductDetailPageDoPost() throws ServletException, IOException, ProductNotFoundException {
        String quantityString = "a";
        String id = "1L";
        String error = "Not a number!!!";
        Cart cart = new Cart();
        Locale locale = Locale.US;
        servlet.setProductService(productService);
        servlet.setCartService(cartService);

        when(request.getLocale()).thenReturn(locale);
        when(request.getParameter("quantity")).thenReturn(quantityString);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn(id);
        when(cartService.getCart(session)).thenReturn(cart);

        servlet.doPost(request, response);
        verify(session).setAttribute("cart", cart);
        verify(request).setAttribute("error", error);
    }

    @Test
    public void setErrorNotEnoughStockWhenProductDetailPageDoPost() throws ServletException, IOException, ProductNotFoundException {
        String quantityString = "1000";
        String id = "1L";
        String error = "Not enough stock. " + "Stock: " + 100;
        Cart cart = new Cart();
        Locale locale = Locale.US;
        servlet.setProductService(productService);
        servlet.setCartService(cartService);

        when(request.getLocale()).thenReturn(locale);
        when(request.getParameter("quantity")).thenReturn(quantityString);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestURI()).thenReturn(id);
        when(cartService.getCart(session)).thenReturn(cart);

        servlet.doPost(request, response);
        verify(session).setAttribute("cart", cart);
        verify(request).setAttribute("error", error);
    }
}