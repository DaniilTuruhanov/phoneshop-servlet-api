package com.es.phoneshop.order;

import com.es.phoneshop.cart.Cart;

import java.math.BigDecimal;
import java.util.UUID;

public class DefailtOrderService implements OrderService {
    private static OrderService orderService;

    private DefailtOrderService() {
    }

    public static OrderService getInstance() {
        if (orderService == null) {
            orderService = new DefailtOrderService();
        }
        return orderService;
    }

    @Override
    public Order getOrder(Cart cart) {
        Order order = new Order(cart);
        order.setSubtotal(cart.getTotalCost());
        order.setDeliveryCost(new BigDecimal(5));
        order.setTotalCost(order.getDeliveryCost().add(order.getSubtotal()));
        order.setTotalQuantity(cart.getTotalQuantity());
        return order;
    }

    @Override
    public Order getOrder(String secureId) throws OrderNotFoundException {
        return ArrayListOrderService.getInstance().getOrder(secureId);
    }

    @Override
    public String placeOrder(Order order) {
        String id = UUID.randomUUID().toString();
        order.setId(id);
        ArrayListOrderService.getInstance().saveOrder(order);
        return id;
    }
}
