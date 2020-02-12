package com.training.adapter.messages.inbound;

import com.training.model.Item;
import com.training.service.ItemService;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@EnableBinding(ItemInputChannel.class)
public class ItemListener {
	private final ItemService itemService;
	
	public ItemListener(ItemService itemService) {
		this.itemService = itemService;
	}

	@StreamListener(ItemInputChannel.INPUT)
    public void handleMessage(Item item){
		itemService.addItem(item);
    }

}
