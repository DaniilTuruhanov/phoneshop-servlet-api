package com.es.phoneshop.cart;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Validator {
    void validate(HttpServletRequest request, HttpServletResponse response, ErrorMap errorMap);
}
