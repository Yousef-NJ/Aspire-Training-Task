package com.training.common.exceptions;

public abstract class AbstractTrainingException extends RuntimeException {

    public AbstractTrainingException(String message){
        super(message);
    }
}
