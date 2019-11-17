package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.ProductNotFoundException;

import java.util.Locale;
import java.util.Map;

public interface Validator {
    Map validate(Locale locale, Cart cart, String idProduct, String quantity) throws ProductNotFoundException;
}
