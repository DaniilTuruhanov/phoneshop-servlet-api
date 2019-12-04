package com.es.phoneshop.order;

import com.es.phoneshop.cart.Cart;

public interface OrderService {
    Order getOrder(Cart cart);

    Order getOrder(String secureId) throws OrderNotFoundException;

    String placeOrder(Order order);
}
