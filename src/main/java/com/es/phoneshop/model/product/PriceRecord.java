package com.es.phoneshop.model.product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class PriceRecord {
    private LocalDate data;
    private BigDecimal price;
    private Currency currency;

    public PriceRecord(LocalDate data, BigDecimal price, Currency currency) {
        this.data = data;
        this.price = price;
        this.currency = currency;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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
}
