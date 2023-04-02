package com.ty.shoppingcart.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.shoppingcart.dto.Admin;
import com.ty.shoppingcart.repository.AdminRepository;



@Repository
public class AdminDao {

	@Autowired
	AdminRepository adminRepository;
	
	

	
	public Admin saveAdmin(Admin admin) {

		return adminRepository.save(admin);
	}

	
	public Admin getAdmin(int id) {

		Optional<Admin> optional = adminRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	
	public Admin updateAdmin(int id, Admin admin) {
		Optional<Admin> optional = adminRepository.findById(id);
		if (optional.isPresent()) {
			return adminRepository.save(admin);
		} else {
			return null;
		}
	}

	
	public boolean deleteAdmin(int id) {
		Optional<Admin> optional = adminRepository.findById(id);
		if (optional.isPresent()) {
			adminRepository.delete(optional.get());
			return true;
		} else {
			return false;
		}
	}

	
	public Admin validateAdmin(String email, String password) {
		
		return adminRepository.validateAdmin(email, password);
	}
	
	

}
