package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;

public class ParseIdClass {
    public String getId(HttpServletRequest request) {
        String idProduct = request.getRequestURI();
        return idProduct.substring(idProduct.lastIndexOf("/") + 1);
    }
}
