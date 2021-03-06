/*
 *  Team 14 Infinity
 *  Task 7
 *  CMU - eBiz
 */
package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class ChangePWDForm extends FormBean{
	private String oldPWD;
	private String newPWD;
	private String cfmPWD;
	private String action;
	
	public void setOldPWD(String s) {
		oldPWD = s.trim();
	}
	public String getOldPWD() {
		return oldPWD;
	}
	
	public void setNewPWD(String s) {
		newPWD = s.trim();
	}
	public String getNewPWD() {
		return newPWD;
	}
	
	public void setCfmPWD(String s) {
		cfmPWD = s.trim();
	}
	public String getCfmPWD() {
		return cfmPWD;
	}
	
	public void setAction(String s) {
		action = s.trim();
	}
	public String getAction() {
		return action;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (oldPWD == null || oldPWD.length() == 0) {
			errors.add("Please enter your old password");
		}
		if (newPWD == null || newPWD.length() == 0) {
			errors.add("New password is required");
		}
		if (!newPWD.matches("^[a-zA-Z][a-zA-Z0-9_]{5,17}$")) {
			errors.add("Password must be between 6 and 18 digits\nIt can only contain letters, digits, and underscode\nIt must start with letters");
		}
		if (cfmPWD == null || cfmPWD.length() == 0) {
			errors.add("Please confirm your new password");
		} else if (!cfmPWD.equals(newPWD)) {
			errors.add("Please confirm your new password");
		}
		if (action == null || !action.equals("Done")) {
			errors.add("Invalid button");
		}

		return errors;
	}
}
