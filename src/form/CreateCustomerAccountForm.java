
/*
 *  Team 14 Infinity
 *  Task 7
 *  CMU - eBiz
 * Homework 9 for 08600-Java J2EE Programming 
 * Author: Dian Wen (AndrewID: dwen) 
 * Date: Nov. 30th, 2014
 * Copyright(C) 2014 All rights reserved.  
 */
package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateCustomerAccountForm extends FormBean {
	private String customer_id;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String addr_line1;
	private String addr_line2;
	private String city;
	private String state;
	private String zip;
	private String action;

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddr_line1() {
		return addr_line1;
	}

	public void setAddr_line1(String addr_line1) {
		this.addr_line1 = addr_line1;
	}

	public String getAddr_line2() {
		return addr_line2;
	}

	public void setAddr_line2(String addr_line2) {
		this.addr_line2 = addr_line2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAction() {
		return action;
	}


	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		if (customer_id == null || customer_id.length() == 0) {
			errors.add("customer id is required");
		}
		if (customer_id.matches("^0$")) {
			errors.add("customer id can not be zero");
		}
		if (!customer_id.matches("[0-9]+")) {
			errors.add("customer id can only contain digits");
		}
		if (username == null || username.length() == 0) {
			errors.add("userName is required");
		}
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		if (!password.matches("^[a-zA-Z][a-zA-Z0-9_]{5,17}$")) {
			errors.add("Password must be between 6 and 18 digits\nIt can only contain letters, digits, and underscode\nIt must start with letters");
		}
		if (firstname == null || firstname.length() == 0) {
			errors.add("firstName is required");
		}
		if (lastname == null || lastname.length() == 0) {
			errors.add("lastName is required");
		}
		if (addr_line1 == null || addr_line1.length() == 0){
			errors.add("address is required");
		}
		if(city == null||city.length() == 0){
			errors.add("city is required");
		}
		if(city.matches(".*[0-9]+.*")) {
			errors.add("city name is invalid");
		}
		if(state == null || state.length() == 0){
			errors.add("State is required");
		}
		if(!state.matches("[A-Z]{2}")) {
			errors.add("State must be two uppercase letters");
		}
		if (zip == null || zip.length() == 0) {
			errors.add("Zip is required");
		}
		if (zip != null && zip.length() != 0 && !zip.matches("[0-9]{5}")) {
			errors.add("Zip code is not accpeted");
		}
		
		if (action == null) {
			errors.add("Button is required");
		}
		if (errors.size() > 0) {
			return errors;
		}
		if (!action.equals("Done")) {
			errors.add("Invalid button");
		}

		return errors;
	}
}
