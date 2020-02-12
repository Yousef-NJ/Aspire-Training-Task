package com.training.session11.cartfullreqresp.adapter.repository.rds.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class ItemEntity {

    @Id
    private String itemNo;

    private int price;

    private int qty;

}
