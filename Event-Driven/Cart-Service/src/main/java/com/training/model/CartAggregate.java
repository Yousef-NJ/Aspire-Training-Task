package com.training.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartAggregate {
	private String userId;
	private String cartNo;
	private String state ;
	private Map<String,CartItem> items = new ConcurrentHashMap<>();
	
	public CartAggregate(String userId,String cartNo) {
		this.userId=userId;
		this.cartNo=cartNo;
	}
	
	public void addItem(String itemNo,double price,int quantity) {
		CartItem cartItem = items.getOrDefault(itemNo, CartItem
				.builder()
				.itemNo(itemNo)
				.price(price)
				.quantity(quantity)
				.build());
		cartItem =cartItem.toBuilder().quantity(quantity).build();
		items.put(itemNo, cartItem);
	}
	
	public void removeItem(String itemNo) {
		items.remove(itemNo);
	}
	
	public void clearItem() {
		items.clear();
	}
	
	public String getCartNo() {
		return cartNo;
	}

	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state=state;
	}
	
	public double getTotalPrice() {
		return items.values().stream().mapToDouble(item->item.getPrice()*item.getQuantity()).sum();
	}

}
