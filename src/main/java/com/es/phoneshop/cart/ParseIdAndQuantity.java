package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParseIdAndQuantity {
    public static String getId(HttpServletRequest request) {
        String idProduct = request.getRequestURI();
        return idProduct.substring(idProduct.lastIndexOf("/") + 1);
    }

    public static int getQuantity(Locale locale, String stringQuantity) {
        int quantity = 0;
        try {
            quantity = NumberFormat.getInstance(locale).parse(stringQuantity).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return quantity;
    }
}
