package com.cognizant.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import org.springframework.stereotype.Component;

import com.cognizant.dao.CustomerDAO;
import com.cognizant.entity.Customer;
import com.cognizant.helper.CustomerBuilder;
import com.cognizant.helper.Validator;

@Component
@Path(value="/customer")
public class CustomerService {
	
	private CustomerDAO customerDAO;
	
	private Validator validator;
	
	public Validator getValidator() {
		return validator;
	}
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
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
		
		customer.setPersonId(1L);
		customer.setFirstName("James");
		customer.setLastName("Bond");
		customer.setEmail("james@bond.de");
		customer.setPhoneNumber(223344551L);
	
		return customer;
	}
	
	@POST
	@Path(value="/create")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Customer createCustomer(JAXBElement<Customer> cus){
		Customer c = cus.getValue();
		Customer customer = new CustomerBuilder(c.getFirstName(), c.getLastName())
				.withEmail(c.getEmail())
				.withPhone(c.getPhoneNumber())
				.build();
		
		customerDAO.save(customer);
		
		//Long customerId = customerDAO.save(customer);
		
		return customerDAO.findCustomerById(3L);
		
	}
	
	@GET
	@Path(value="/list")
	@Produces(MediaType.APPLICATION_XML)
	public List<Customer> getAllCustomers(){	
		return customerDAO.fetchAllCustomers();
	}
	
}
