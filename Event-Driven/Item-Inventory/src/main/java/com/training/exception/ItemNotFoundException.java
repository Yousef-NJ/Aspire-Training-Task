package com.training.exception;

public class ItemNotFoundException extends RuntimeException{
	
	private final String id;

	public ItemNotFoundException(String id) {
		 super("Item" + id + "not found");
	     this.id = id;
	}
	
	

}
