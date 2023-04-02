package com.ty.shoppingcart.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Merchant;
import com.ty.shoppingcart.repository.MerchantRespository;

@Repository
public class MerchantDao {
	
	@Autowired
	MerchantRespository merchantRepository;
	
	

	
	public Merchant saveMerchant(Merchant merchant) {

		return merchantRepository.save(merchant);
	}

	
	public Merchant getMerchant(int id) {

		Optional<Merchant> optional = merchantRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public List<Merchant> getAllMerchants(){
		return merchantRepository.findAll();
	}

	
	public Merchant updateMerchant(int id, Merchant merchant) {
		Optional<Merchant> optional = merchantRepository.findById(id);
		if (optional.isPresent()) {
			return merchantRepository.save(merchant);
		} else {
			return null;
		}
	}

	
	public boolean deleteMerchant(int id) {
		Optional<Merchant> optional = merchantRepository.findById(id);
		if (optional.isPresent()) {
			merchantRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

	
	public Merchant validateMerchant(String email, String password) {
		
		return merchantRepository.validateMerchant(email, password);
	}
	
}
