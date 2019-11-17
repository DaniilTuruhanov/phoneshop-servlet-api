package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.ProductNotFoundException;

import javax.servlet.http.HttpServletRequest;

public interface CartService {
    Cart getCart(HttpServletRequest request);

    void addCartItem(Cart cart, String idProduct, int quantity) throws ProductNotFoundException;
}
