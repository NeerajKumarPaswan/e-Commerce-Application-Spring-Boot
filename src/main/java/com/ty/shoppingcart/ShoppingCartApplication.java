package com.ty.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartApplication.class, args);
	}
	
	List<VendorExtension> vendorExtensions = new ArrayList<VendorExtension>();
	Contact contact = new Contact("uday", "https://testyantra.com", "udaybhaker1414@gmail.com");
	
	ApiInfo apiInfo = new ApiInfo("SHOPPING-CART-APPLICATION",
			"API's to perform all actions related to BOOK product in App",
			"Snapshoot-0.0.1",
			"https://testyantraglobal.com/",
			contact,
			"www.ty.com",
			"jhgfdsdfg",
			vendorExtensions);
	
	@Bean
	public Docket myDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ty"))
				.build()
				.apiInfo(apiInfo);
	}
}
