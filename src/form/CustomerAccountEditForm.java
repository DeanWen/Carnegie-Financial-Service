package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerAccountEditForm extends FormBean{
	private String firstname;
	private String lastname;
	private String addr1;
	private String addr2;
	private String city;
	private String state;
	private String zip;
	private String action;
	
	public void setFirstname(String firstname) {
		this.firstname = firstname.trim();
	}
	public String getFirstname() {
		return firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname.trim();
	}
	public String getLastname() {
		return lastname;
	}
	
	public void setAddr1(String addr1) {
		this.addr1 = addr1.trim();
	}
	public String getAddr1() {
		return addr1;
	}
	
	public void setAddr2(String addr2) {
		this.addr2 = addr2.trim();
	}
	public String getAddr2() {
		return addr2;
	}
	
	public void setCity(String city) {
		this.city = city.trim();
	}
	public String getCity() {
		return city;
	}
	
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
	
	public void setZip(String zip) {
		this.zip = zip.trim();
	}
	public String getZip() {
		return zip;
	}
	
	public void setAction(String action) {
		this.action = action;
	}
	public String getAction() {
		return action;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if(firstname == null || firstname.length() == 0) {
			errors.add("First name is required");
		}
		if(lastname == null || lastname.length() == 0) {
			errors.add("Last name is required");
		}
		if(addr1 == null || addr1.length() == 0) {
			errors.add("Address is required");
		}
		if(city == null || city.length() == 0) {
			errors.add("City is required");
		}
		if(state == null || state.length() == 0) {
			errors.add("State is required");
		}
		if(zip == null || zip.length() == 0) {
			errors.add("Zip code is required");
		}
		if(!zip.matches("[0-9][0-9][0-9][0-9][0-9]")) {
			errors.add("Please enter the correct zip code");
		}
		System.out.println("Action:" + action);
		if(!action.equals("Done")) {
			errors.add("Invalid button");
		}

		return errors;
	}
}
