package com.es.phoneshop.model.product;

import com.es.phoneshop.cart.ErrorMap;
import com.es.phoneshop.cart.QuantityValidator;
import com.es.phoneshop.cart.Validator;

import java.util.regex.Pattern;

public class CommentValidator implements Validator<Comment> {

    private static CommentValidator commentValidator;

    private CommentValidator() {
    }

    public static CommentValidator getInstance() {
        if (commentValidator == null) {
            commentValidator = new CommentValidator();
        }
        return commentValidator;
    }
    @Override
    public void validate(Comment validateObject, ErrorMap errorMap) {
        if (validateObject.getName().trim().equals("") || Pattern.matches("(.*[0-9]+.*)", validateObject.getName() )){
            errorMap.addError("Name", "Name is required");
        }
    }
}
