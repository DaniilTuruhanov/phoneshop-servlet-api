package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;

import java.io.Serializable;
import java.util.Objects;

public class CartItem implements Serializable {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        CartItem cartItem = (CartItem) o;
        return product.equals(cartItem.getProduct());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product.getId());
    }
}
