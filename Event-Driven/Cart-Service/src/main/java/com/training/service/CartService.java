package com.training.service;


import com.training.model.Item;
import com.training.events.CartChangeStateEvent;
import com.training.model.CartAggregate;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.training.adapter.messages.outbound.CartEventChannel;
import com.training.adapter.repo.CartAggregateRepo;

import java.util.Optional;

@Service
@EnableBinding(CartEventChannel.class)
public class CartService {
	private final ItemService itemService;
	private final CartAggregateRepo cartAggregateRepo;
	private final CartEventChannel cartEventChannel;
	
    public CartService(CartAggregateRepo cartAggregateRepo, ItemService itemService,CartEventChannel cartEventChannel) {
		this.cartAggregateRepo = cartAggregateRepo;
		this.itemService = itemService;
		this.cartEventChannel = cartEventChannel;
	}

	public String createCart(String userId,String cartNo) {
		CartAggregate cartAggregate =new CartAggregate(userId, cartNo);
    	cartAggregateRepo.saveCartAggregate(cartAggregate);
    	created(cartNo);
    	return cartNo;
    }
    
    public boolean created(String cartNo) {
    	Optional<CartAggregate> optionalCartAggregate = cartAggregateRepo.loadCartAggregate(cartNo);
    	CartAggregate cartAggregate = optionalCartAggregate.orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
		if(cartAggregate.getState()==null || cartAggregate.getState().isEmpty()) {
			cartAggregate.setState("Created");
			cartAggregateRepo.saveCartAggregate(cartAggregate);
			sendCartChangeStateEvent(cartAggregate);
			return true;
		}
		return false;
    }
    
    public boolean saved(String cartNo) {
    	Optional<CartAggregate> optionalCartAggregate = cartAggregateRepo.loadCartAggregate(cartNo);
    	CartAggregate cartAggregate = optionalCartAggregate.orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
		if(cartAggregate.getState().equals("Created") ||cartAggregate.getState().equals("Cleared") || cartAggregate.getState().equals("Saved")) {
			cartAggregate.setState("Saved");
			cartAggregateRepo.saveCartAggregate(cartAggregate);
			sendCartChangeStateEvent(cartAggregate);
			return true;
		}
		return false;    	
    }
    
    public boolean cleared(String cartNo) {
    	Optional<CartAggregate> optionalCartAggregate = cartAggregateRepo.loadCartAggregate(cartNo);
    	CartAggregate cartAggregate = optionalCartAggregate.orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
		if(cartAggregate.getState().equals("Saved")) {
			cartAggregate.clearItem();
			cartAggregate.setState("Cleared");
			cartAggregateRepo.saveCartAggregate(cartAggregate);
			sendCartChangeStateEvent(cartAggregate);
			return true;
		}
		return false;    	
    }
    
    public boolean checkout(String cartNo) {
    	Optional<CartAggregate> optionalCartAggregate = cartAggregateRepo.loadCartAggregate(cartNo);
    	CartAggregate cartAggregate = optionalCartAggregate.orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
		if(cartAggregate.getState().equals("Saved")) {
			cartAggregate.setState("Checkout");
			cartAggregateRepo.saveCartAggregate(cartAggregate);
			sendCartChangeStateEvent(cartAggregate);
			return true;
		}
		return false;    	
    }
    
    public boolean pending(String cartNo) {
    	Optional<CartAggregate> optionalCartAggregate = cartAggregateRepo.loadCartAggregate(cartNo);
    	CartAggregate cartAggregate = optionalCartAggregate.orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
		if(cartAggregate.getState().equals("Checkout") || cartAggregate.getState().equals("Payment-Failed")) {
			cartAggregate.setState("Pending");
			cartAggregateRepo.saveCartAggregate(cartAggregate);
			sendCartChangeStateEvent(cartAggregate);
			return true;
		}
		return false;    	
    }
    
    public boolean payment(String cartNo,String state) {
    	Optional<CartAggregate> optionalCartAggregate = cartAggregateRepo.loadCartAggregate(cartNo);
    	CartAggregate cartAggregate = optionalCartAggregate.orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
		if(cartAggregate.getState().equals("Pending")) {
			cartAggregate.setState(state);
			cartAggregateRepo.saveCartAggregate(cartAggregate);
			if(state.equals("Payment-Failed")){
				pending(cartNo);
			}
			return true;
		}
		return false;
    }

    public boolean addItemToCart(String cartNo,String itemNo,int quantity){
    	Item item = itemService.getItem(itemNo).orElseThrow(()->new RuntimeException("Item Do Not Exist"));
    	CartAggregate cartAggregate = cartAggregateRepo.loadCartAggregate(cartNo).orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
    	cartAggregate.addItem(item.getItemNo(),item.getPrice(),quantity);
    	cartAggregateRepo.saveCartAggregate(cartAggregate);
    	saved(cartNo);
    	return true;
	}

	public boolean removeItemToCart(String cartNo,String itemNo){
		itemService.getItem(itemNo).orElseThrow(()->new RuntimeException("Item Do Not Exist"));
		CartAggregate cartAggregate = cartAggregateRepo.loadCartAggregate(cartNo).orElseThrow(()->new RuntimeException("Cart Do Not Exist"));
		cartAggregate.removeItem(itemNo);
		cartAggregateRepo.saveCartAggregate(cartAggregate);
		saved(cartNo);
		return true;
	}
	
	private void  sendCartChangeStateEvent(CartAggregate cart) {
		CartChangeStateEvent cartChangeStateEvent=CartChangeStateEvent
				.builder()
				.cartNo(cart.getCartNo())
				.state(cart.getState())
				.totalPrice(cart.getTotalPrice())
				.build();
		Message<CartChangeStateEvent> msg=MessageBuilder.withPayload(cartChangeStateEvent).build();
		cartEventChannel.outputEvents().send(msg);		
	}

}
