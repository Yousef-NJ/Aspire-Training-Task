package com.training.adapter.messages.inbound;

import com.training.service.CartService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.training.adapter.messages.DTO.CartDTO;

@EnableBinding(CartInputChannel.class)
public class CartListener {
	
	private CartService cartService;

    public CartListener(CartService cartService) {
		this.cartService = cartService;
	}

	@StreamListener(CartInputChannel.INPUT)
    public void handleMessage(CartDTO cart){
        cartService.payment(cart.getCartNo(),cart.getState());
    }

}
