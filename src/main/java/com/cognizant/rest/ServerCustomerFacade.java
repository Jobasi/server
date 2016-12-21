package com.cognizant.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;

import org.springframework.stereotype.Component;

import com.cognizant.dao.CustomerDAO;
import com.cognizant.entity.Customer;
import com.cognizant.helper.CustomerBuilder;
import com.cognizant.helper.CustomerList;
import com.cognizant.helper.Validator;

@Component
@Path(value="/customer")
public class ServerCustomerFacade {
	
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
	public Response sayHello(){
		return Response.status(200).build();	     
		
		
	}
	@GET
	@Path(value="/find/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public Response findCustomer(@PathParam(value = "id") Long id){
		return customerDAO.findCustomerById(id);	     
	}
	
	@POST
	@Path(value="/create")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response createCustomer(JAXBElement<Customer> cus){
		Customer c = cus.getValue();
		Customer customer = new Customer();
		customer = new CustomerBuilder(c.getFirstName(), c.getLastName())
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
	public Response getAllCustomers(){	
		return customerDAO.fetchAllCustomers();
	}
	
}
