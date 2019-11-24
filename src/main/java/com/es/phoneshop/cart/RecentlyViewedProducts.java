package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class RecentlyViewedProducts implements Serializable {
    private Queue<Product> productQueue;

    public RecentlyViewedProducts() {
        productQueue = new LinkedList<>();
    }

    public void addProductInProductQueue(Product product) {
        if (productQueue.contains(product)) {
            productQueue.remove(product);
            productQueue.add(product);
        } else {
            if (productQueue.size() < 3) {
                productQueue.add(product);
            } else {
                productQueue.poll();
                productQueue.add(product);
            }
        }
    }

    public Queue<Product> getProductQueue() {
        return productQueue;
    }

    public void setProductQueue(Queue<Product> productQueue) {
        this.productQueue = productQueue;
    }
}
