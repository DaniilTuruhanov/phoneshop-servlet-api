package com.es.phoneshop.cart;

public interface Validator<T> {
    void validate(T validateObject, ErrorMap errorMap);
}
