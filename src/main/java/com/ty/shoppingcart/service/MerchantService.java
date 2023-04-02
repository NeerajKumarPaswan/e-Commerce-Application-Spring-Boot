package com.ty.shoppingcart.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dao.MerchantDao;
import com.ty.shoppingcart.dto.Merchant;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.exception.InvalidCredentialsException;
import com.ty.shoppingcart.exception.NoIdFoundException;

@Service
public class MerchantService {
		
	@Autowired
	MerchantDao merchantDao;

	@Autowired
	MailSenderService mailSenderService;

	public ResponseStructure<Merchant> saveMerchant(Merchant merchant) {
		ResponseStructure<Merchant> responseStructure = new ResponseStructure<Merchant>();
		merchant.setToken(generateToken());
		Merchant merchant2 = merchantDao.saveMerchant(merchant);
		if (merchant2 != null) {
			responseStructure.setData(merchant2);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;

	}

	public ResponseStructure<Merchant> getMerchant(int id) {

		ResponseStructure<Merchant> responseStructure = new ResponseStructure<Merchant>();
		Merchant merchant = merchantDao.getMerchant(id);
		if (merchant != null) {
			responseStructure.setData(merchant);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Merchant>> getAllMerchants() {

		ResponseStructure<List<Merchant>> responseStructure = new ResponseStructure<List<Merchant>>();
		List<Merchant> merchants = merchantDao.getAllMerchants();
		if (merchants.size()>0) {
			responseStructure.setData(merchants);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}
	
	public boolean checkToken(Merchant merchant , String token) {
		if(merchant.getToken().equals(token)) {
			return true;
		}else {
			return false;
		}
	}
	
	private String generateToken() {
		StringBuilder token = new StringBuilder();
		return token.append(UUID.randomUUID().toString()).append(UUID.randomUUID().toString()).toString();
	}

	public ResponseStructure<Merchant> updateMerchant(int id, Merchant merchant) {
		ResponseStructure<Merchant> responseStructure = new ResponseStructure<Merchant>();
		merchant.setToken(null);
		Merchant merchant1 = merchantDao.updateMerchant(id, merchant);
		if (merchant1 != null) {
			responseStructure.setData(merchant1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteMerchant(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		boolean flag = merchantDao.deleteMerchant(id);
		if (flag) {
			responseStructure.setData("Data deleted");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
		} else {
			throw new NoIdFoundException("Merchant id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<Merchant> validateMerchant(String email, String password) {
		ResponseStructure<Merchant> responseStructure = new ResponseStructure<Merchant>();
		Merchant merchant = merchantDao.validateMerchant(email, password);
		if (merchant != null) {
			merchant.setStatus("Active");
			Merchant merchant1 = merchantDao.updateMerchant(merchant.getId(), merchant);
			responseStructure.setData(merchant1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new InvalidCredentialsException();
		}
		return responseStructure;
	}

}
