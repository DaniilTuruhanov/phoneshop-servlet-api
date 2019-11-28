package com.es.phoneshop.cart;

import javafx.util.Pair;

public class QuantityValidator implements Validator<Pair<String, String>> {

    private static QuantityValidator quantityValidator;

    private QuantityValidator() {
    }

    public static QuantityValidator getInstance() {
        if (quantityValidator == null) {
            quantityValidator = new QuantityValidator();
        }
        return quantityValidator;
    }

    @Override
    public void validate(Pair<String, String> quantity, ErrorMap errorMap) {
        try {
            int intQuantity = Integer.valueOf(quantity.getKey());
        } catch (NumberFormatException e) {
            errorMap.addError("quantity-" + quantity.getValue(), "Not a number");
        }
    }
}
