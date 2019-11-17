package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.LinkedList;
import java.util.Queue;

public class RecentlyViewedProducts {
    private Queue<Product> productQueue;

    public RecentlyViewedProducts() {
        productQueue = new LinkedList<>();
    }

    public void addProductInRecentlyViewes(Product product) {
        if (productQueue.contains(product)) {
            productQueue.remove(product);
            productQueue.add(product);
            return;
        }
        if (productQueue.size() < 3) {
            productQueue.add(product);
        } else {
            productQueue.poll();
            productQueue.add(product);
        }
    }

    public Queue<Product> getProductQueue() {
        return productQueue;
    }

    public void setProductQueue(Queue<Product> productQueue) {
        this.productQueue = productQueue;
    }
}
