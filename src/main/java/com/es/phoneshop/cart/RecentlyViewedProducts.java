package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;

import java.util.LinkedList;
import java.util.Queue;

public class RecentlyViewedProducts {
    private Queue<Product> productQueue;

    public RecentlyViewedProducts() {
        productQueue = new LinkedList<>();
    }

    public Queue<Product> getProductQueue() {
        return productQueue;
    }

    public void setProductQueue(Queue<Product> productQueue) {
        this.productQueue = productQueue;
    }
}
