package com.es.phoneshop.model.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Objects;

public class Product implements Serializable {
    private String id;
    private String description;
    private BigDecimal price;
    public ArrayList<PriceRecord> priceHistory;
    private Currency currency;
    private int stock;
    private ArrayList<Comment> commentArrayList = new ArrayList<>();
    private String imageUrl;

    public Product() {
    }

    public Product(String id, String description, BigDecimal price, Currency currency, int stock, ArrayList<PriceRecord> priceRecords, String imageUrl) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.stock = stock;
        this.imageUrl = imageUrl;
        this.priceHistory = new ArrayList<>();
        for (PriceRecord s : priceRecords) {
            this.priceHistory.add(s);
        }
    }

    public void addComment(Comment comment) {
        commentArrayList.add(comment);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<PriceRecord> getPriceHistory() {
        return priceHistory;
    }

    public void setPriceHistory(ArrayList<PriceRecord> priceHistory) {
        this.priceHistory = priceHistory;
    }

    public ArrayList<Comment> getCommentArrayList() {
        return commentArrayList;
    }

    public void setCommentArrayList(ArrayList<Comment> commentArrayList) {
        this.commentArrayList = commentArrayList;
    }

    @Override
    public boolean equals(Object o) {
        Product product = (Product) o;
        return (this.id == product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
