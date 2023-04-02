package com.ty.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ty.shoppingcart.dto.Merchant;

public interface MerchantRespository extends JpaRepository<Merchant, Integer> {

	@Query("SELECT m FROM Merchant m WHERE m.email=:myemail AND m.password=:mypassword")
	Merchant validateMerchant(@Param("myemail") String email, @Param("mypassword") String password);

}
