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
    public void validate(Pair<String, String> fieldAndProductId, ErrorMap errorMap) {
        try {
            int intQuantity = Integer.valueOf(fieldAndProductId.getKey());
        } catch (NumberFormatException e) {
            errorMap.addError("quantity-" + fieldAndProductId.getValue(), "Not a number");
        }
    }
}
