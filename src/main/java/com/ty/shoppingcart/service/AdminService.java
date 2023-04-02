package com.ty.shoppingcart.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dao.AdminDao;
import com.ty.shoppingcart.dao.MerchantDao;
import com.ty.shoppingcart.dto.Admin;
import com.ty.shoppingcart.dto.Merchant;
import com.ty.shoppingcart.dto.ResponseStructure;
import com.ty.shoppingcart.exception.InvalidCredentialsException;
import com.ty.shoppingcart.exception.NoIdFoundException;

@Service
public class AdminService {
	@Autowired
	AdminDao adminDao;
	@Autowired 
	MerchantDao merchantDao;

	public ResponseStructure<Admin> saveAdmin(Admin admin) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin2 = adminDao.saveAdmin(admin);
		if (admin2 != null) {
			responseStructure.setData(admin2);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;

	}

	public ResponseStructure<Admin> getAdmin(int id) {

		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin = adminDao.getAdmin(id);
		if (admin != null) {
			responseStructure.setData(admin);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Success");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<Admin> updateAdmin(int id, Admin admin) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin1 = adminDao.updateAdmin(id, admin);
		if (admin1 != null) {
			responseStructure.setData(admin1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new NoIdFoundException();
		}
		return responseStructure;
	}

	public ResponseStructure<String> deleteAdmin(int id) {
		ResponseStructure<String> responseStructure = new ResponseStructure<String>();
		boolean flag = adminDao.deleteAdmin(id);
		if (flag) {
			responseStructure.setData("Data deleted");
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Deleted");
		} else {
			throw new NoIdFoundException("Admin id " + id + " Does not exist");
		}
		return responseStructure;
	}

	public ResponseStructure<Admin> validateAdmin(String email, String password) {
		ResponseStructure<Admin> responseStructure = new ResponseStructure<Admin>();
		Admin admin = adminDao.validateAdmin(email, password);
		if (admin != null) {
			responseStructure.setData(admin);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Updated");
		} else {
			throw new InvalidCredentialsException();
		}
		return responseStructure;
	}
	
	public ResponseStructure<List<Merchant>> getPendingMerchants(){
		ResponseStructure<List<Merchant>> responseStructure = new ResponseStructure<>();
		List<Merchant> merchants = merchantDao.getAllMerchants().stream().filter(p->p.getStatus().equals("pending")).collect(Collectors.toList());
		if(merchants.size()>0) {
			responseStructure.setData(merchants);
			responseStructure.setStatusCode(HttpStatus.FOUND.value());
			responseStructure.setMessage("success");
		}else {
			throw new NoIdFoundException("No pending merchants Found");
		}
		return responseStructure;
	}
	
	public ResponseStructure<Merchant> approveMerchant(int id) {
		ResponseStructure<Merchant> responseStructure = new ResponseStructure<Merchant>();
		Merchant merchant = merchantDao.getMerchant(id);
		merchant.setStatus("approved");
		Merchant merchant1 = merchantDao.updateMerchant(merchant.getId(), merchant);
		if (merchant1 != null) {
			responseStructure.setData(merchant1);
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("Approved");
		} else {
			throw new NoIdFoundException("no Merchant Found");
		}
		return responseStructure;
	}

}
