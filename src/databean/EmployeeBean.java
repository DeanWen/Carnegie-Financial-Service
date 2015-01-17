package databean;
/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */

import java.io.*;

public class EmployeeBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String username;
	private String firstname;
	private String lastname;
	private String password;

	public String getFirstname() {
		return firstname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
}
