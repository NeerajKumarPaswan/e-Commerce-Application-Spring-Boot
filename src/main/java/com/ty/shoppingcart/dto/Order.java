package com.ty.shoppingcart.dto;


import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "myOrder")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String status = "confirm";
	private LocalDateTime localDateTime;
	private double total;
	@ManyToMany
	private List<Item> items;
	@ManyToOne
	@JsonIgnore
	@JoinColumn
	private Customer customer;
	@ManyToOne
	@JoinColumn
	private Address address;

}
