package com.training.session11.cartfullreqresp.exception;

import com.training.common.exceptions.AbstractTrainingException;

public class FailedToBindingCartException extends AbstractTrainingException {
    public FailedToBindingCartException(String status) {
        super(String.format("Cart is in `%s` Status", status));
    }
}
