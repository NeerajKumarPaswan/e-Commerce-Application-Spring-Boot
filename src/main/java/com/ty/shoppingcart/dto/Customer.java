package com.ty.shoppingcart.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;
	private String password;
	private String token;
	private String gender;
	private int age;
	private String status = "inactive";
	@OneToMany(mappedBy = "customer")
	private List<Address> addresses;
	@OneToOne
	@JoinColumn
	private Cart cart;
	@OneToOne(cascade = CascadeType.PERSIST , mappedBy = "customer")
	private WishList wishList;
	@OneToMany(mappedBy = "customer")
	private List<Order> orders;
	
}
