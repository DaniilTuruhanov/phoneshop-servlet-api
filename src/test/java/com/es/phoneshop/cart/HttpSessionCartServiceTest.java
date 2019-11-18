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
import javax.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.LinkedList;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)

public class HttpSessionCartServiceTest {
    private HttpSessionCartService cartService = HttpSessionCartService.getInstance();

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @Test
    public void getNewEmptyCartWhenRequestDoesNotExistCart() {
        Cart cart = new Cart();

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("cart")).thenReturn(cart);

        Cart result = cartService.getCart(session);
        assertEquals(cart, result);
    }

    @Test
    public void returnCartWhenRequestExistCart() {
        Currency usd = Currency.getInstance("USD");
        Cart cart = new Cart();
       //cartService.addCartItem(cartItem);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("cart")).thenReturn(cart);

        Cart result = cartService.getCart(session);
        assertEquals(cart, result);
    }
}
