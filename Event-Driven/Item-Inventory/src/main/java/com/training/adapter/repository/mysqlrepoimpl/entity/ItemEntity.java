package com.training.adapter.repository.mysqlrepoimpl.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class ItemEntity {
	@Id
	private String itemNo;
	private String name;
	private String description;
	private int manId;
	private int quantity;
	private int price;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name = "item_image")
	private List<ImageLinkEntity> images;

}
