package com.es.phoneshop.order;

import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.Validator;
import javafx.util.Pair;

import java.util.regex.Pattern;

public class OrderFieldsValidator implements Validator<OrderCreateForm> {
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
    public void validate(OrderCreateForm validateObject, ErrorMap errorMap) {
        if (validateObject.getFirstName() == null || validateObject.getFirstName().trim().equals("") || Pattern.matches("(.*[0-9]+.*)", validateObject.getFirstName())) {
            errorMap.addError("First name", "First name is required");
        }
        if (validateObject.getLastName() == null || validateObject.getLastName().trim().equals("") || Pattern.matches("(.*[0-9]+.*)", validateObject.getLastName())) {
            errorMap.addError("Last name", "Last name is required");
        }

        if (validateObject.getPhone() == null || validateObject.getPhone().trim().equals("") || Pattern.matches("(.*[a-zA-Zа-яА-Я]+.*)", validateObject.getPhone())) {
            errorMap.addError("Phone", "Phone is required");
        }

        if (validateObject.getDate() == null || validateObject.getDate().trim().equals("")) {
            errorMap.addError("Date", "Date is required");
        }

        if (validateObject.getPaymentMethod() == null || validateObject.getPaymentMethod().trim().equals("")) {
            errorMap.addError("Payment Method", "Payment Method is required");
        }

        if (validateObject.getAddress() == null || validateObject.getAddress().trim().equals("")) {
            errorMap.addError("Address", "Address is required");
        }
    }
}
