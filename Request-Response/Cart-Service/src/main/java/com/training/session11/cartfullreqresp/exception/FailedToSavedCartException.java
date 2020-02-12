package com.training.session11.cartfullreqresp.exception;

import com.training.common.exceptions.AbstractTrainingException;

public class FailedToSavedCartException extends AbstractTrainingException {
    public FailedToSavedCartException(String status) {
        super(String.format("Cart is in `%s` Status", status));
    }
}
