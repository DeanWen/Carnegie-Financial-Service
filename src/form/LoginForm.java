/*
 *  Team 14 Infinity
 *  Task 7
 *  CMU - eBiz
 */
package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class LoginForm extends FormBean {
	private String userid;
	private String password;
	private String action;
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	public String getUserid() {
		return userid;
	}
	
	public String getPassword() {
		return password;
	}

	public String getAction() {
		return action;
	}

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (userid == null || userid.length() == 0) {
			errors.add("ID is required");
		}
		if (password == null || password.length() == 0) {
			errors.add("Password is required");
		}
		if (action == null) {
			errors.add("Button is required");
		}
		if (errors.size() > 0) {
			return errors;
		}
		if (!(action.equals("Login")||action.equals("Login as Employee"))) {
			errors.add("Invalid button");
		}

		return errors;
	}
}
