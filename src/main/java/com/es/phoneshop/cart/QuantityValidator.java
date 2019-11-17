package com.es.phoneshop.cart;

import com.es.phoneshop.model.product.Product;
import com.es.phoneshop.model.product.ProductNotFoundException;
import com.es.phoneshop.model.product.ProductService;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class QuantityValidator implements Validator {
    private Map<String, String> errorMap = new HashMap();
    private static ProductService productService = ProductService.getInstance();

    public QuantityValidator() {
    }

    public QuantityValidator(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    @Override
    public Map validate(Locale locale, Cart cart, String idProduct, String quantity) throws ProductNotFoundException {
        try {
            int intQuantity = NumberFormat.getInstance(locale).parse(quantity).intValue();
            Product product = productService.getProduct(idProduct);
            CartItem cartItem = new CartItem(product, intQuantity);
            if (cart.getListCartItems().contains(cartItem)) {
                int id = cart.getListCartItems().indexOf(cartItem);
                if (cart.getListCartItems().get(id).getQuantity() + intQuantity > productService.getProduct(idProduct).getStock()) {
                    errorMap.put("error", "Not enough stock. " + "Stock: " + productService.getProduct(idProduct).getStock());
                }
            }
            if (productService.getProduct(idProduct).getStock() < intQuantity) {
                errorMap.put("error", "Not enough stock. " + "Stock: " + productService.getProduct(idProduct).getStock());
            }
        } catch (ParseException e) {
            errorMap.put("error", "Not a number!!!");
        }
        return errorMap;
    }
}
