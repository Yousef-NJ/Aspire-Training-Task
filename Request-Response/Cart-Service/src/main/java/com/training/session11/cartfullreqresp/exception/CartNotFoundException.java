package com.training.session11.cartfullreqresp.exception;

import com.training.common.exceptions.AbstractTrainingException;

public class CartNotFoundException extends AbstractTrainingException {
    public CartNotFoundException(String id) {
        super(String.format("Cart with id `%s` Not Found!!!", id));
    }
}
