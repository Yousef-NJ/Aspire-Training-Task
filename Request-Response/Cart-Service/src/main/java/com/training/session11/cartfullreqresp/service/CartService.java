package com.training.session11.cartfullreqresp.service;

import com.training.common.command.CommandHandler;
import com.training.session11.cartfullreqresp.adapter.outbound.ItemDTO;
import com.training.session11.cartfullreqresp.adapter.repository.CartAggregateRepository;
import com.training.session11.cartfullreqresp.aggregate.Cart;
import com.training.session11.cartfullreqresp.aggregate.CartAggregate;
import com.training.session11.cartfullreqresp.aggregate.CartStatus;
import com.training.session11.cartfullreqresp.command.*;
import com.training.session11.cartfullreqresp.exception.CartNotFoundException;
import com.training.session11.cartfullreqresp.exception.ItemNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static java.util.Collections.emptyList;

@Service
public class CartService {

    private final CartAggregateRepository cartAggregateRepository;
    private final ItemService itemservice;

    public CartService(CartAggregateRepository cartAggregateRepository, ItemService itemservice) {
        this.cartAggregateRepository = cartAggregateRepository;
        this.itemservice = itemservice;
    }

    public Cart getCartByCartNo(String cartNo){
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartNo)
                .orElseThrow(() -> new CartNotFoundException(cartNo));
        return mapToCart(cartAggregate);
    }

    @CommandHandler
    public String openNewCart(OpenCartCommand openCartCommand) {
        String cartId = UUID.randomUUID().toString();
        CartAggregate cartAggregate =
                new CartAggregate(emptyList()
                        , openCartCommand.getUserId()
                        , cartId
                        , CartStatus.CREATED);

        cartAggregateRepository
                .insert(cartAggregate);

        return cartId;
    }

    @CommandHandler
    public void clearCart(ClearCartCommand clearCartCommand){
        String cartId = clearCartCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.clearCart();

        cartAggregateRepository.insert(cartAggregate);

    }

    @CommandHandler
    public void addItemToCart(AddItemToCartCommand addItemToCArtCommand){
        String cartId = addItemToCArtCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        ItemDTO itemDTO = itemservice.getItemByItemNo(addItemToCArtCommand.getItemNo()).orElseThrow(()->new ItemNotFoundException(addItemToCArtCommand.getItemNo()));

        cartAggregate.addItem(addItemToCArtCommand.getItemNo(),
                addItemToCArtCommand.getQty(),
                itemDTO.getPrice());

        cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public void removeItemFromCart(RemoveItemFromCartCommand removeItemFromCArtCommand){
        String cartId = removeItemFromCArtCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.removeItem(removeItemFromCArtCommand.getItemNo());

        cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public void cartCheckout(CartCheckoutCommand cartCheckoutCommand){
        String cartId = cartCheckoutCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.checkout();

        cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public void cartBinding(CartBindingCommand cartBindingCommand){
        String cartId = cartBindingCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.binding();

        cartAggregateRepository.insert(cartAggregate);
    }

    @CommandHandler
    public void cartPaid(CartPaidCommand cartPaidCommand){
        String cartId = cartPaidCommand.getCartId();
        CartAggregate cartAggregate = cartAggregateRepository
                .findById(cartId)
                .orElseThrow(() -> new CartNotFoundException(cartId));

        cartAggregate.paid();

        cartAggregateRepository.insert(cartAggregate);
    }

    private Cart mapToCart(CartAggregate cartAggregate){
        return new Cart(cartAggregate.getUserId(),cartAggregate.getCartId(),cartAggregate.getCartStatus().toString(),cartAggregate.getTotalPrice());
    }

//
//    public void moveItemToWishList(MoveItemToWishListCommand moveItemToWishListCommand){
//
//    }
//
//    public void moveItemFromWishListToCart(MoveItemFromWishListCommand moveItemFromWishListCommand){
//
//    }

}
