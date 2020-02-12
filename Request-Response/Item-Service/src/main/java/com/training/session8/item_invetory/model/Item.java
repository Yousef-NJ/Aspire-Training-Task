package com.training.session8.item_invetory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class Item {

    private String itemNo;

    private String name;

    private int manId;

    private int qty;

    private int price;

    private List<ImageLink> images;

    private String description;


}
