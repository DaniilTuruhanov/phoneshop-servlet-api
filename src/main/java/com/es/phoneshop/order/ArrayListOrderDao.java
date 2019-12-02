package com.es.phoneshop.order;

import com.es.phoneshop.model.product.SynchronizeMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ArrayListOrderDao implements OrderDao {
    private static OrderDao orderDao;
    private List<Order> orders;

    private ArrayListOrderDao() {
        orders = new ArrayList<>();
    }

    public static OrderDao getInstance() {
        if (orderDao == null) {
            orderDao = new ArrayListOrderDao();
        }
        return orderDao;
    }

    @Override
    public void saveOrder(Order order) {
        synchronized (SynchronizeMap.findKey(order.getSecureId())) {
            if (order.getSecureId() == null) {
                order.setSecureId(UUID.randomUUID().toString());
                orders.add(order);
            } else {
                if (orders.contains(order)) {
                    orders.set(orders.indexOf(order), order);
                } else {
                    orders.add(order);
                }
            }
        }
    }

    @Override
    public Optional<Order> getOrder(String secureId) {
        synchronized (SynchronizeMap.findKey(secureId)) {
            return orders.stream()
                    .filter(order -> order.getSecureId().equals(secureId))
                    .findAny();
        }
    }
}
