package com.training.common.exceptions;

public class NoHandlerFoundForCommandException extends AbstractTrainingException{

    private final String className;
    public NoHandlerFoundForCommandException(String message
            , Class<?> className) {
        super(message);
        this.className = className.getSimpleName();
    }

    public String getClassName() {
        return className;
    }
}
