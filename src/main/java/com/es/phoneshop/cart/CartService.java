package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.ProductNotFoundException;

import javax.servlet.http.HttpSession;

public interface CartService {
    Cart getCart(HttpSession session);

    void addCartItem(Cart cart, String idProduct, int quantity) throws ProductNotFoundException, OutOfStockException;

    void updateCartItem(Cart cart, String idProduct, int quantity) throws ProductNotFoundException, OutOfStockException;

    void deleteCartItem(Cart cart, String idProduct);

    void clearCart(Cart cart);

}
