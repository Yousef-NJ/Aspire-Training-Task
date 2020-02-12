package com.training.adapter.rest;

import com.training.adapter.rest.dto.AddItemToCartCommandDTO;
import com.training.adapter.rest.dto.CreateCartCommandDTO;
import com.training.service.CartService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartController {
	private final CartService cartService;
	
	public CartController(CartService cartService) {
		this.cartService = cartService;
	}
	
	@PostMapping
	public String addCart(@RequestBody CreateCartCommandDTO createCartCommandDTO) {
		String cartNo = cartService.createCart(createCartCommandDTO.getUserId(),createCartCommandDTO.getCartNo());
		cartService.created(cartNo);
		return cartNo;
	}
	
	@PutMapping("/{cartNo}/clear")
	public boolean clearCart(@PathVariable("cartNo") String cartNo) {
		return cartService.cleared(cartNo);
	}
	
	@PutMapping("/{cartNo}/checkout")
	public boolean checkout(@PathVariable("cartNo") String cartNo) {
		return cartService.checkout(cartNo);
	}
	
	@PutMapping("/{cartNo}/pending")
	public boolean pending(@PathVariable("cartNo") String cartNo) {
		return cartService.pending(cartNo);
	}
	
	@PostMapping("/{cartNo}/items")
	public boolean addItem(@PathVariable("cartNo") String cartNo, @RequestBody AddItemToCartCommandDTO addItemToCartCommandDTO) {
		return cartService.addItemToCart(cartNo,addItemToCartCommandDTO.getItemNo(),addItemToCartCommandDTO.getQuantity());
	}
	
	@DeleteMapping("/{cartNo}/items/{itemNo}")
	public boolean removeItem(@PathVariable("cartNo") String cartNo,@PathVariable("itemNo") String itemNo) {
		return cartService.removeItemToCart(cartNo,itemNo);
	}

}
