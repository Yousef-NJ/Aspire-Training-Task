package com.training.common.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TrainingExceptionHandler {

    private final AllExceptionsMessages allExceptionsMessages;

    public TrainingExceptionHandler(AllExceptionsMessages allExceptionsMessages) {
        this.allExceptionsMessages = allExceptionsMessages;
    }


    @ExceptionHandler(AbstractTrainingException.class)
    public ResponseEntity<Object> handleTrainingException(AbstractTrainingException e){

        ErrorInfo errorInfo = allExceptionsMessages.getExceptions().get(e.getClass().getSimpleName());

        //TODO handle null errorInfo
        return new ResponseEntity<>(errorInfo, new HttpHeaders(), HttpStatus.resolve(errorInfo.getRestErrorCode()));
    }
}
