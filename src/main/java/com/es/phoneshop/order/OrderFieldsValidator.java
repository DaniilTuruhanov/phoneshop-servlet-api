package com.es.phoneshop.order;

import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.Validator;
import javafx.util.Pair;

import java.util.regex.Pattern;

public class OrderFieldsValidator implements Validator<Pair<String, String>> {
    private static OrderFieldsValidator orderFieldsValidator;

    private OrderFieldsValidator() {
    }

    public static OrderFieldsValidator getInstance() {
        if (orderFieldsValidator == null) {
            orderFieldsValidator = new OrderFieldsValidator();
        }
        return orderFieldsValidator;
    }

    @Override
    public void validate(Pair<String, String> validateObject, ErrorMap errorMap) {
        if (validateObject.getKey() == null || validateObject.getKey().trim().equals("")) {
            errorMap.addError(validateObject.getValue(), validateObject.getValue() + " is required");
        } else {
            if (validateObject.getValue().equals("Phone")) {
                if (Pattern.matches("(.*[a-zA-Zа-яА-Я]+.*)", validateObject.getKey())) {
                    errorMap.addError(validateObject.getValue(), validateObject.getValue() + " is required");
                }

            } else {
                if (validateObject.getValue().equals("First name") || validateObject.getValue().equals("Last name")) {
                    if (Pattern.matches("(.*[0-9]+.*)", validateObject.getKey())) {
                        errorMap.addError(validateObject.getValue(), validateObject.getValue() + " is required");
                    }
                }
            }
        }
    }
}
