package com.training.session8.item_invetory.exception;

public class ItemNotFoundException extends RuntimeException {

    private final String id;

    public ItemNotFoundException(String id) {
        super(String.format("Item %s not found",id));
        this.id = id;
    }
}
