package com.training.adapter.repo.rdsrepo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart_aggregate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartAggregateEntity {
	private String userId;
	@Id
	private String cartNo;
	private String state;
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
	@JoinColumn(name = "cart_no")
	private List<CartItemEntity> items;

}
