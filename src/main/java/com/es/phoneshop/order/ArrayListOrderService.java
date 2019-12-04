package com.es.phoneshop.order;

import java.util.Optional;

public class ArrayListOrderService {
    private static OrderDao orderDao;

    private static ArrayListOrderService ArrayListOrderService;

    private ArrayListOrderService() {
        orderDao = ArrayListOrderDao.getInstance();
    }

    public static ArrayListOrderService getInstance() {
        if (ArrayListOrderService == null) {
            ArrayListOrderService = new ArrayListOrderService();
        }
        return ArrayListOrderService;
    }

    public Order getOrder(String secureId) throws OrderNotFoundException {
        Optional<Order> order = orderDao.getOrder(secureId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFoundException();
        }
    }

    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }
}
