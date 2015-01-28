/**
 * Homework 9 for 08600-Java J2EE Programming 
 * Author: Dian Wen (AndrewID: dwen) 
 * Date: Nov. 30th, 2014
 * Copyright(C) 2014 All rights reserved.  
 */
package form;

import java.util.ArrayList;
import java.util.List;
import org.mybeans.form.FormBean;

public class CreateEmployeeAccountForm extends FormBean {
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String action;

	public void setPassword(String password) {
		this.password = password;
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

	public void setAction(String action) {
		this.action = action;
	}

	public String getPassword() {
		return password;
	}

	public String getAction() {
		return action;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (username == null || username.length() == 0) {
			errors.add("Username is required");
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
