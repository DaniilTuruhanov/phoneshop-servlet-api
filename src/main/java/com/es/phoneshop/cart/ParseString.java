package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;

public class ParseString {
    public static String getId(HttpServletRequest request) {
        String idProduct = request.getRequestURI();
        return idProduct.substring(idProduct.lastIndexOf("/") + 1);
    }

    public static int getQuantity(String stringQuantity) {
        return Integer.valueOf(stringQuantity);
    }
}
