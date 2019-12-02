package com.es.phoneshop.order;

import com.es.phoneshop.model.product.ArrayListProductDao;
import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;

import java.util.Optional;

public class ArrayListOrderDaoService {
    private static OrderDao orderDao;

    private static ArrayListOrderDaoService arrayListOrderDaoService;

    private ArrayListOrderDaoService() {
        orderDao = ArrayListOrderDao.getInstance();
    }

    public static ArrayListOrderDaoService getInstance() {
        if (arrayListOrderDaoService == null) {
            if (arrayListOrderDaoService == null) {
                arrayListOrderDaoService = new ArrayListOrderDaoService();
            }
        }
        return arrayListOrderDaoService;
    }

    public Order getOrder(String secureId) throws OrderNotFoundException {
        Optional<Order> order = orderDao.getOrder(secureId);
        if (order.isPresent()) {
            return order.get();
        } else {
            throw new OrderNotFoundException();
        }
    }

    public void saveOrder(Order order){
        orderDao.saveOrder(order);
    }
}
