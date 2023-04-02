package com.ty.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.shoppingcart.dto.Merchant;
import com.ty.shoppingcart.dto.Login;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.exception.NoIdFoundException;
import com.ty.shoppingcart.service.MerchantService;
import com.ty.shoppingcart.service.MailSenderService;

@RestController
public class MerchantController {
	
	@Autowired
	MerchantService merchantService;

	@Autowired
	MailSenderService mailSenderService;

	Merchant merchant2;

	@PostMapping("/merchants")
	public ResponseStructure<String> registerMerchant(@RequestBody Merchant merchant) {

		merchant2 = merchant;
		merchantService.saveMerchant(merchant);
		return mailSenderService.sendMail(merchant);
	}

	@GetMapping("/merchants/confirm")
	public ResponseStructure<Merchant> saveMerchant(@RequestBody Merchant merchant, @RequestParam String token) {
		boolean flag = merchantService.checkToken(merchant2, token);
		if (flag) {
			merchant2.setPassword(merchant.getPassword());
			return merchantService.updateMerchant(merchant2.getId(),merchant2);
		} else {
			throw new NoIdFoundException("Token does not match");
		}
	}

	@GetMapping("/merchants/{id}")
	public ResponseStructure<Merchant> getMerchant(@PathVariable int id) {
		return merchantService.getMerchant(id);
	}

	@PutMapping("/merchants/{id}")
	public ResponseStructure<Merchant> updateMerchant(@PathVariable int id, @RequestBody Merchant merchant) {
		return merchantService.updateMerchant(id, merchant);
	}

	@DeleteMapping("/merchants/{id}")
	public ResponseStructure<String> deleteMerchant(@PathVariable int id) {
		return merchantService.deleteMerchant(id);
	}

	@PostMapping("/merchants/login")
	public ResponseStructure<Merchant> validateMerchant(@RequestBody Login login) {
		return merchantService.validateMerchant(login.getEmail(), login.getPassword());
	}

}
