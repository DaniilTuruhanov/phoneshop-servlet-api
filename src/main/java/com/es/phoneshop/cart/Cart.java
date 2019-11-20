package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;

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

    public void addToListCartItems(Product product, int quantity) throws OutOfStockException {
        CartItem cartItem = new CartItem(product, quantity);
        if (listCartItems.contains(cartItem)) {
            int id = listCartItems.indexOf(cartItem);
            if (listCartItems.get(id).getQuantity() + quantity <= product.getStock()) {
                listCartItems.get(id).setQuantity(listCartItems.get(id).getQuantity() + quantity);
            } else {
                throw new OutOfStockException(product.getStock());
            }
        } else {
            listCartItems.add(cartItem);
        }
        recalculateTotals(product, quantity);
    }

    public void recalculateTotals(Product product, int quantity) {
        BigDecimal totalProductPrice = product.getPrice();
        BigDecimal totalResultProductPrice = totalProductPrice.multiply(new BigDecimal(quantity));

        totalCost = totalCost.add(totalResultProductPrice);
        totalQuantity = totalQuantity + quantity;
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
