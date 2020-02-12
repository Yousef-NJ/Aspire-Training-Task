package com.training.session11.cartfullreqresp.adapter.rest;

import com.training.common.command.CommandBus;
import com.training.session11.cartfullreqresp.adapter.rest.dto.ItemQtyDTO;
import com.training.session11.cartfullreqresp.aggregate.Cart;
import com.training.session11.cartfullreqresp.command.*;
import com.training.session11.cartfullreqresp.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final CommandBus commandBus;

    public CartController(CartService cartService,CommandBus commandBus) {
        this.cartService = cartService;
        this.commandBus = commandBus;
    }

    @GetMapping("/{cartNo}")
    public Cart getCart(@PathVariable("cartNo") String cartNo){
        return cartService.getCartByCartNo(cartNo);
    }

    @PostMapping
    public void createCart(OpenCartCommand openCartCommand){
        commandBus.execute(openCartCommand);
    }

    @PatchMapping("/{cartNo}")
    public void addItemToCart(@PathVariable("cartNo") String cartNo, ItemQtyDTO itemQtyDTO){
        AddItemToCartCommand addItemToCartCommand = new AddItemToCartCommand(cartNo, itemQtyDTO.getItemNo(), itemQtyDTO.getQty());
        commandBus.execute(addItemToCartCommand);
    }

    @PatchMapping("/{cartNo}/items/{itemNo}")
    public void removeItemFromCart(@PathVariable("cartNo") String cartNo,@PathVariable("itemNo") String itemNo){
        RemoveItemFromCartCommand removeItemFromCartCommand = new RemoveItemFromCartCommand(cartNo,itemNo);
        commandBus.execute(removeItemFromCartCommand);
    }

    @PatchMapping("/{cartNo}/clear")
    public void cleanCart(@PathVariable("cartNo") String cartNo){
        ClearCartCommand clearCartCommand = new ClearCartCommand(cartNo);
        commandBus.execute(clearCartCommand);
    }

    @PutMapping("/{cartNo}/checkout")
    public void checkout(@PathVariable("cartNo") String cartNo){
        CartCheckoutCommand cartCheckoutCommand = new CartCheckoutCommand(cartNo);
        commandBus.execute(cartCheckoutCommand);
    }

    @PutMapping("/{cartNo}/binding")
    public void binding(@PathVariable("cartNo") String cartNo){
        CartBindingCommand cartBindingCommand = new CartBindingCommand(cartNo);
        commandBus.execute(cartBindingCommand);
    }

    @PutMapping("/{cartNo}/paid")
    public void paid(@PathVariable("cartNo") String cartNo){
        CartPaidCommand cartPaidCommand = new CartPaidCommand(cartNo);
        commandBus.execute(cartPaidCommand);
    }

}
