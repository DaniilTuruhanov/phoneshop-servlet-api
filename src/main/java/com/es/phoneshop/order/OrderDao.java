package com.es.phoneshop.order;

import java.util.Optional;

public interface OrderDao {
    void saveOrder(Order order);

    Optional<Order> getOrder(String secureId);
}
