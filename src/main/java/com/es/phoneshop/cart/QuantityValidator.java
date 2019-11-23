package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuantityValidator implements Validator {

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
    public void validate(HttpServletRequest request, HttpServletResponse response, ErrorMap errorMap) {
        String quantity = request.getParameter("quantity");
        Locale locale = request.getLocale();
        Pattern pattern = Pattern.compile("[\\D&&[^.,]]");
        Matcher matcher = pattern.matcher(quantity);
        try {
            if (matcher.find()) {
                errorMap.addError("quantity", "Not a number!!!");
            } else {
                int intQuantity = NumberFormat.getInstance(locale).parse(quantity).intValue();
            }
        } catch (ParseException e) {
            errorMap.addError("quantity", "Not a number!!!");
        }
    }
}
