package com.es.phoneshop.order;

import com.es.phoneshop.cart.Cart;

import java.math.BigDecimal;
import java.util.Objects;

public class Order extends Cart {
    private BigDecimal deliveryCost;
    private BigDecimal subtotal;
    private String id;
    private OrderCreateForm orderCreateForm;

    public Order(Cart cart) {
        this.setListCartItems(cart.getListCartItems());
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderCreateForm getOrderCreateForm() {
        return orderCreateForm;
    }

    public void setOrderCreateForm(OrderCreateForm orderCreateForm) {
        this.orderCreateForm = orderCreateForm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
