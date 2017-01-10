package com.cognizant.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.core.Response;

import com.cognizant.entity.Customer;
import com.cognizant.exceptions.CustomerNotFoundException;
import com.cognizant.helper.BaseClass;
import com.cognizant.helper.CustomerList;
import com.cognizant.helper.Validator;

public class CustomerDAO {

	private static final String PERSISTENCE_UNIT_NAME = "customer";
    private EntityManagerFactory factory;
    private EntityManager entityManager;
    
	 public CustomerDAO() {
	 }
	 

	    private Validator validator;

	    public void setValidator(Validator validator) {
	        this.validator = validator;
	        }
	
	    public Response save(Customer customer){
	    	try {
	    		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				this.entityManager = this.factory.createEntityManager();
		    	entityManager.getTransaction().begin();
		    	entityManager.persist(customer);
		    	entityManager.getTransaction().commit();
		    	entityManager.close();
		    	return Response.status(201).entity(customer).build();
				
			} catch (Exception e) {
				return Response.status(500).entity(e).build();
			}
	    	
	    }
	    @SuppressWarnings("unchecked")
	    public Response fetchAllCustomers(){
	    	try {
		    	this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				this.entityManager = this.factory.createEntityManager();
		    	entityManager.getTransaction().begin();
		        Query query = entityManager.createQuery("SELECT c FROM Customer c");
		        List<Customer> customer = (List<Customer>) query.getResultList(); 
		        if (customer.isEmpty()){
		        	return Response.status(404).build();
		        }
		        entityManager.close();
		        CustomerList customerList = new CustomerList();
		        customerList.setCustomer(customer);
		       
		        return Response.status(200).entity(customerList).build();	  
			} catch (Exception e) {
				return Response.status(500).entity(e).build();			
				
			}
      
	     
	    }

	    public Response findCustomerById(long id){
	    	try {
	    		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				this.entityManager = this.factory.createEntityManager();
		    	entityManager.getTransaction().begin();
		        Customer customer = entityManager.find(Customer.class, id);
		        if (customer == null){
		        	return Response.status(404).build();
		        }
		        entityManager.close();
		        return Response.status(200).entity(customer).build();
			} catch (RuntimeException e) {
				return Response.status(500).entity(e).build();
			}
	    	

	    }

	    public Response updateCustomer(Customer customer){
	    	try {
	    		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				this.entityManager = this.factory.createEntityManager();
				Customer customer2 = entityManager.find(Customer.class, customer.getPersonId());
				entityManager.getTransaction().begin();
		    	
		    	customer2.setEmail(customer.getEmail());
		    	customer2.setFirstName(customer.getFirstName());
		    	customer2.setLastName(customer.getLastName());
		    	customer2.setPhoneNumber(customer.getPhoneNumber());
		    	
		    	entityManager.getTransaction().commit();
		    	
		    	return Response.status(201).entity(customer2).build();
			} catch (Exception e) {
				return Response.status(400).entity(e).build();
			}
	    	
	
	    }

	    public Response deleteCustomer(Long id){
	    	try {
	    		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				this.entityManager = this.factory.createEntityManager();
				Customer customer = entityManager.find(Customer.class, id);
				entityManager.getTransaction().begin();
		    	
				entityManager.remove(customer);
		    	
		    	entityManager.getTransaction().commit();
		    	return Response.status(200).entity(customer).build();
				
			} catch (Exception e) {
				return Response.status(500).entity(e).build();
			}
	    	
	    	
	    	
	    }

	    public Response findByEmail(String email){
	    	try {
	    		this.factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
				this.entityManager = this.factory.createEntityManager();
		    	entityManager.getTransaction().begin();
		    	Query query = entityManager.createQuery("SELECT c FROM Customer WHERE c.email = :email");
		    	Customer customer = (Customer)query.getSingleResult();
		    	System.out.println(customer.toString());
		    	entityManager.close();
		    	if (customer == null){
		        	return Response.status(404).build();
		        }
		    	return Response.status(200).entity(customer).build();
			} catch (Exception e) {
				return Response.status(500).entity(e).build();
			}
	    	
	    }
}

