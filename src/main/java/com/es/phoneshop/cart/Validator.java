package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public interface Validator {
    void validate(HttpServletRequest request, HttpServletResponse response, Map<String, ArrayList<String>> errorMap);
}
