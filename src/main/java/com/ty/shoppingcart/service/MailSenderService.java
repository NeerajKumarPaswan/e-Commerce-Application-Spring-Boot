package com.ty.shoppingcart.service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ty.shoppingcart.dto.Customer;
import com.ty.shoppingcart.dto.Merchant;
import com.ty.shoppingcart.dto.ResponseStructure;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class MailSenderService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	private Configuration configuration;
	
	 public String getEmailContent(Customer customer) throws IOException, TemplateException {
	        StringWriter stringWriter = new StringWriter();
	        Map<String, Object> model = new HashMap<>();
	        model.put("customer", customer);
	        configuration.getTemplate("email-template.ftl").process(model, stringWriter);
	        return stringWriter.getBuffer().toString();
	    }
	 
	 public String getEmailContent(Merchant merchant) throws IOException, TemplateException {
	        StringWriter stringWriter = new StringWriter();
	        Map<String, Object> model = new HashMap<>();
	        model.put("merchant", merchant);
	        configuration.getTemplate("merchant-template.ftl").process(model, stringWriter);
	        return stringWriter.getBuffer().toString();
	    }

	public ResponseStructure<String> sendMail(Customer customer) {
		ResponseStructure<String> res = new ResponseStructure<>();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		try {
			helper.setSubject("Customer Verification for shopping cart account");
			helper.setFrom("udayvegesena@gmail.com");
			helper.setTo(customer.getEmail());
			String html = getEmailContent(customer);
			helper.setText(html, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			javaMailSender.send(mimeMessage);
			res.setData("please check your mail");
			res.setMessage("Registration Successfull");
			res.setStatusCode(HttpStatus.CREATED.value());
			return res;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return res;
		}

	}
	
	public ResponseStructure<String> sendMail(Merchant merchant) {
		ResponseStructure<String> res = new ResponseStructure<>();
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		try {
			helper.setSubject("Merchant Verification for shopping cart account");
			helper.setFrom("udayvegesena@gmail.com");
			helper.setTo(merchant.getEmail());
			String html = getEmailContent(merchant);
			helper.setText(html, true);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			javaMailSender.send(mimeMessage);
			res.setData("please check your mail");
			res.setMessage("Registration Successfull");
			res.setStatusCode(HttpStatus.CREATED.value());
			return res;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return res;
		}

	}
}
