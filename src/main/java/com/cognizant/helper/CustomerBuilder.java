package com.cognizant.helper;

import com.cognizant.entity.Customer;

public class CustomerBuilder {
	private final String firstName;
    private final String lastName;
    private String email;
    private long phoneNumber;
    private Long personId;


    public CustomerBuilder(String firstName, String lastName) {
    	try{
    		if((firstName == null) && (lastName == null) ){
        		throw new Exception();
        	}
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	this.firstName = firstName;
        this.lastName = lastName;
        
    }

    public CustomerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public CustomerBuilder withPhone(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    public CustomerBuilder withPersonId(Long personId) {
		this.personId = personId;
		return this;
	}

    public Customer build() {
        return new Customer(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

}
