package com.ty.shoppingcart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.shoppingcart.dto.Admin;
import com.ty.shoppingcart.dto.Login;
import com.ty.shoppingcart.dto.Merchant;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.service.AdminService;
import com.ty.shoppingcart.service.MailSenderService;

@RestController
public class AdminController {

	@Autowired
	AdminService adminService;

	@Autowired
	MailSenderService mailSenderService;

	Admin admin2;

	@PostMapping("/admins")
	public ResponseStructure<Admin> saveAdmin(@RequestBody Admin admin) {

		return adminService.saveAdmin(admin);
	}

	@GetMapping("/admins/{id}")
	public ResponseStructure<Admin> getAdmin(@PathVariable int id) {
		return adminService.getAdmin(id);
	}

	@PutMapping("/admins/{id}")
	public ResponseStructure<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
		return adminService.updateAdmin(id, admin);
	}

	@DeleteMapping("/admins/{id}")
	public ResponseStructure<String> deleteAdmin(@PathVariable int id) {
		return adminService.deleteAdmin(id);
	}

	@PostMapping("/admins/login")
	public ResponseStructure<Admin> validateAdmin(@RequestBody Login login) {
		return adminService.validateAdmin(login.getEmail(), login.getPassword());
	}
	
	@GetMapping("/admins/merchants/view")
	public ResponseStructure<List<Merchant>> getPendingMerchants(){
		return adminService.getPendingMerchants();
	}
	
	@PutMapping("/admins/merchants/approve/{id}")
	public ResponseStructure<Merchant> approveMerchant(@PathVariable int id){
		return adminService.approveMerchant(id);
	}

}
