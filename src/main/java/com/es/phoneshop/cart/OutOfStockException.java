package com.es.phoneshop.cart;

public class OutOfStockException extends Exception {
    private int totalQuantity;

    public OutOfStockException(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
