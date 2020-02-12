package com.training.session8.item_invetory.adapter.repository.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ImageLinkEntity {

    @Id
    @GeneratedValue
    private int id;

    private String link;
}
