package com.training.session8.item_invetory.adapter.rest;

import com.training.session8.item_invetory.adapter.rest.dto.ItemDTO;
import com.training.session8.item_invetory.exception.ItemNotFoundException;
import com.training.session8.item_invetory.model.Item;
import com.training.session8.item_invetory.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ModelMapper modelMapper;
    private final ItemService itemService;

    public ItemController(ModelMapper modelMapper, ItemService itemService) {
        this.modelMapper = modelMapper;
        this.itemService = itemService;
    }

    @PostMapping
    public ItemDTO addingItem(@Valid @RequestBody ItemDTO itemDTO){
        Item item = mapItemFromDTO(itemDTO);
        Item addedItem = itemService.newItem(item);
        return mapToDTO(addedItem);

    }

    @GetMapping("/{id}")
    public ItemDTO gettingItem(@PathVariable("id") String id)
    {
        return itemService
                .getItem(id)
                .map(this::mapToDTO)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @GetMapping
    public Page<ItemDTO> allItem(
            @RequestParam(name = "Page Index",
                    defaultValue = "0",
                    required = false)
                    int pageIndex,
            @RequestParam(name = "Page Size",
                    defaultValue = "10",
                    required = false)
            int pageSize


    ){

        return itemService
                .loadItems(pageIndex, pageSize)
                .map(this::mapToDTO);
    }


    private Item mapItemFromDTO( ItemDTO itemDTO) {
        return modelMapper.map(itemDTO, Item.class);
    }

    private ItemDTO mapToDTO(Item item) {
        return modelMapper.map(item, ItemDTO.class);
    }
}
