package com.training.common.command;

public interface CommandBus {


    public <U> U execute(Object commandObject);


}
