package com.ty.shoppingcart.dto;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String house_name;
	private String flat_number;
	private String street;
	private String city;
	private String state;
	private String country;
	private String pincode;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@OneToMany(mappedBy = "address")
	@JsonIgnore
	private List<Order> orders;

}
