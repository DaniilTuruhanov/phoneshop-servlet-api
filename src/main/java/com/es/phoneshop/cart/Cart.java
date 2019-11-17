package com.es.phoneshop.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> listCartItems;
    private int totalQuantity;
    private BigDecimal totalCost;

    public Cart() {
        listCartItems = new ArrayList<>();
        totalCost = new BigDecimal(0);
        totalQuantity = 0;
    }

    public Cart(List<CartItem> listCartItems, int totalQuantity, BigDecimal totalCost) {
        this.listCartItems = listCartItems;
        this.totalQuantity = totalQuantity;
        this.totalCost = totalCost;
    }

    public List<CartItem> getListCartItems() {
        return listCartItems;
    }

    public void setListCartItems(List<CartItem> listCartItems) {
        this.listCartItems = listCartItems;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
}
