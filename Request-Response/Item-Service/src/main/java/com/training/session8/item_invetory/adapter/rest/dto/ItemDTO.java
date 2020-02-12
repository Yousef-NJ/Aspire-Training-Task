package com.training.session8.item_invetory.adapter.rest.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ItemDTO {

    @NotEmpty
    private String itemNo;

    @NotEmpty
    private String name;

    private String description;

    @Min(0)
    private int qty;

    @Min(0)
    private int price;

    @Min(1)
    private int manId;


    private List<ImageLinkDTO> images;
}
