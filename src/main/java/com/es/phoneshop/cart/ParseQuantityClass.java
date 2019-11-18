package com.es.phoneshop.cart;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParseQuantityClass {
    public int getQuantity(Locale locale, String stringQuantity) {
        int quantity = 0;
        try {
            quantity = NumberFormat.getInstance(locale).parse(stringQuantity).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quantity;
    }
}
