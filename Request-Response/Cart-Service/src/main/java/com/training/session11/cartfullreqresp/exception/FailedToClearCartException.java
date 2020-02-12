package com.training.session11.cartfullreqresp.exception;

import com.training.common.exceptions.AbstractTrainingException;

public class FailedToClearCartException extends AbstractTrainingException {
    public FailedToClearCartException(String status) {
        super(String.format("Cart is in `%s` Status", status));
    }
}
