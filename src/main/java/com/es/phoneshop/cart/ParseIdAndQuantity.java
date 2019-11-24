package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;

public class ParseIdAndQuantity {
    public static String getId(HttpServletRequest request) {
        String idProduct = request.getRequestURI();
        return idProduct.substring(idProduct.lastIndexOf("/") + 1);
    }

    public static int getQuantity(String stringQuantity) {
        int quantity = 0;
        try {
            quantity = Integer.valueOf(stringQuantity);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return quantity;
    }
}
