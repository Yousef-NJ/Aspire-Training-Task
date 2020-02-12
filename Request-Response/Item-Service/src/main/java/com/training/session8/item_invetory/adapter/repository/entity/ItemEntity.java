package com.training.session8.item_invetory.adapter.repository.entity;

import com.training.session8.item_invetory.model.ImageLink;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ItemEntity {

    @Id
    private String itemNo;

    private String name;

    private int manId;

    private int qty;

    private int price;

    @OneToMany(cascade = CascadeType.ALL
            ,orphanRemoval = true

    )
    @JoinColumn(name = "item_no")
    private List<ImageLinkEntity> images;

    private String description;
}
