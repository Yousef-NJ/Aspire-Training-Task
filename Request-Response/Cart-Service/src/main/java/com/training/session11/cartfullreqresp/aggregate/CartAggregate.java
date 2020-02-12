package com.training.session11.cartfullreqresp.aggregate;

import com.training.session11.cartfullreqresp.exception.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;

public class CartAggregate {

    private List<LineItem> lineItems = emptyList();
    private String userId;
    private String cartId;
    private CartStatus cartStatus;
//    private int version;
//    private ZonedDateTime lastActionTime;

    public CartAggregate(List<LineItem> lineItems, String userId, String cartId, CartStatus cartStatus) {
        this.lineItems = lineItems == null ? emptyList() : unmodifiableList(lineItems);
        this.userId = userId;
        this.cartId = cartId;
        this.cartStatus = cartStatus;
    }

    public void addItem(String itemNo, int qty, int price) {
        if(!(cartStatus.equals(CartStatus.SAVED) || cartStatus.equals(CartStatus.CREATED) || cartStatus.equals(CartStatus.CLEARED)))
            throw new FailedToSavedCartException(cartStatus.toString());
        LineItem lineItem1 =
                lineItems
                        .stream()
                        .filter(lItem ->
                                lItem.getItemNo()
                                        .equalsIgnoreCase(itemNo))
                        .findFirst()
                        .map(lineItem -> lineItem
                                .toBuilder()
                                .qty(lineItem.getQty() + qty)
                                .build())
                        .orElse(new LineItem(itemNo, price, qty));

        List<LineItem> itemList = lineItems
                .stream()
                .filter(lItem -> !lItem.getItemNo().equalsIgnoreCase(itemNo))
                .collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        itemList.add(lineItem1);
        cartStatus=CartStatus.SAVED;
        this.lineItems = unmodifiableList(itemList);

    }

    public void removeItem(String itemNo) {
        if(!(cartStatus.equals(CartStatus.SAVED) || cartStatus.equals(CartStatus.CREATED) || cartStatus.equals(CartStatus.CLEARED)))
            throw new FailedToSavedCartException(cartStatus.toString());
        List<LineItem> newList = lineItems.stream()
                .filter(lItem -> !lItem.getItemNo().equals(itemNo))
                .collect(Collectors.toList());
        cartStatus=CartStatus.SAVED;
        this.lineItems = unmodifiableList(newList);
    }

    public int getTotalPrice() {
        return lineItems.stream()
                .mapToInt(item -> item.getPrice() * item.getQty())
                .sum();
    }

    public CartStatus getCartStatus() {
        return this.cartStatus;
    }


    public void clearCart(){
        if(!cartStatus.equals(CartStatus.SAVED))
            throw new FailedToClearCartException(cartStatus.toString());
        this.lineItems = Collections.emptyList();
        this.cartStatus = CartStatus.CLEARED;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public String getUserId() {
        return userId;
    }

    public String getCartId() {
        return cartId;
    }

    public void checkout(){
        if(!cartStatus.equals(CartStatus.SAVED))
            throw new FailedToCheckoutCartException(cartStatus.toString());
        cartStatus=CartStatus.CHECKED_OUT;
    }

    public void binding(){
        if(!cartStatus.equals(CartStatus.CHECKED_OUT))
            throw new FailedToBindingCartException(cartStatus.toString());
        cartStatus=CartStatus.BINDING;
    }

    public void paid(){
        if(!cartStatus.equals(CartStatus.BINDING))
            throw new FailedToPaidCartException(cartStatus.toString());
        cartStatus=CartStatus.PAID_SUCCESSFULLY;
    }
}
