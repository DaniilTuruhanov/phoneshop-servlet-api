package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuantityValidator implements Validator {
    @Override
    public void validate(HttpServletRequest request, HttpServletResponse response, Map<String, String> errorMap) {
        String quantity = request.getParameter("quantity");
        Locale locale = request.getLocale();
        Pattern pattern = Pattern.compile("[\\D&&[^.,]]");
        Matcher matcher = pattern.matcher(quantity);
        try {
            if (matcher.find()) {
                errorMap.put("quantity", "Not a number!!!");
            } else {
                int intQuantity = NumberFormat.getInstance(locale).parse(quantity).intValue();
            }
        } catch (ParseException e) {
            errorMap.put("quantity", "Not a number!!!");
        }
    }
}
