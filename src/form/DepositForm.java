/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class DepositForm extends FormBean{
	String userid;
	String depositAmount;
	
	public void setUserid(String s) {
		userid = s.trim();
	}
	public String getUserid() {
		return userid;
	}
	
	public void setDepositAmount(String s) {
		depositAmount = s.trim();
	}
	public String getDepositAmount() {
		return depositAmount;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(userid == null || userid.length() == 0) {
			errors.add("Please enter the user id:");
			return errors;
		}
		if (depositAmount == null || depositAmount.length() == 0) {
			errors.add("Please enter the deposit amount:");
			return errors;
		}
		if (!depositAmount.matches("^[0-9]{1,12}([.][0-9]{1,2})?$")) {
			errors.add("Deposit amount number is not accepted");
			return errors;
		}
		
		return errors;
	}
}
