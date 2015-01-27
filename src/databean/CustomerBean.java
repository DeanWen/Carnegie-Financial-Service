package databean;

/*
 * Name: Dean Wen
 * Date: 11/26/2014
 */

import java.io.Serializable;
import java.math.BigDecimal;

public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int customer_id;
	private String username;
	private String firstname;
	private String lastname;
	private String password;
	private String addr_line1;
	private String addr_line2;
	private String city;
	private String state;
	private int zip;
	private BigDecimal cash;

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
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

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public BigDecimal getCash() {
		return cash;
	}

	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}

}
