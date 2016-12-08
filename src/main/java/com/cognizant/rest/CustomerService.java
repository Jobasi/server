package com.cognizant.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.cognizant.entity.Customer;
import com.cognizant.helper.Validator;

@Component
@Path(value="/customer")
public class CustomerService {
	
	private Validator validator;
	
	public Validator getValidator() {
		return validator;
	}
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@GET
	@Path(value="/hello")
	@Produces(MediaType.APPLICATION_JSON)
	public String sayHello(){
		return "Hello from the other side!!!";
		
		
	}
	@GET
	@Path(value="/find")
	@Produces(MediaType.APPLICATION_XML)
	public com.cognizant.entity.Customer findCustomer(){
		Customer customer  = new Customer();
		
		customer.setId(1L);
		customer.setFirstName("James");
		customer.setLastName("Bond");
		customer.setEmail("james@bond.de");
		customer.setPhoneNumber(223344551L);
	
		return customer;
	}
	
}
