package com.training.session11.cartfullreqresp.exception;

import com.training.common.exceptions.AbstractTrainingException;

public class ItemNotFoundException extends AbstractTrainingException {
    public ItemNotFoundException(String itemNo) {
        super(String.format("Item `%s` Not Found", itemNo));
    }
}
