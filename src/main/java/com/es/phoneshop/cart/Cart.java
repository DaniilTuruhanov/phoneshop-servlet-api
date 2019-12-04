package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cart implements Serializable {
    private List<CartItem> listCartItems;
    private int totalQuantity;
    private BigDecimal totalCost;

    public Cart() {
        listCartItems = new ArrayList<>();
        totalCost = new BigDecimal(0);
        totalQuantity = 0;
    }

    public void add(Product product, int quantity) throws OutOfStockException {
        CartItem cartItem = new CartItem(product, quantity);
        if (listCartItems.contains(cartItem)) {
            int id = listCartItems.indexOf(cartItem);
            if (listCartItems.get(id).getQuantity() + quantity <= product.getStock()) {
                listCartItems.get(id).setQuantity(listCartItems.get(id).getQuantity() + quantity);
                recalculateTotals();
            } else {
                throw new OutOfStockException(product.getStock());
            }
        } else {
            if (product.getStock() < quantity) {
                throw new OutOfStockException(product.getStock());
            } else {
                listCartItems.add(cartItem);
                recalculateTotals();
            }
        }
    }

    public void update(Product product, int quantity) throws OutOfStockException {
        CartItem cartItem = new CartItem(product, quantity);
        int id = listCartItems.indexOf(cartItem);
        if (quantity == 0) {
            listCartItems.remove(listCartItems.get(id));
        } else {
            if (quantity <= product.getStock()) {
                listCartItems.get(id).setQuantity(quantity);
            } else {
                throw new OutOfStockException(product.getStock());
            }
        }
        recalculateTotals();
    }

    public void delete(String idProduct) {
        Optional<CartItem> item = listCartItems.stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(idProduct))
                .findAny();
        if (item.isPresent()) {
            listCartItems.remove(item.get());
        }
        recalculateTotals();
    }

    public void clear() {
        listCartItems = new ArrayList<>();
        totalCost = new BigDecimal(0);
        totalQuantity = 0;
    }

    public void recalculateTotals() {
        totalCost = BigDecimal.valueOf(0);
        totalQuantity = 0;
        for (CartItem cartItem : listCartItems) {
            totalQuantity += cartItem.getQuantity();
            totalCost = totalCost.add(cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity())));
        }
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
