/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class FindCustomerForm extends FormBean{
	String userid;
	
	public void setUserid(String s) {
		userid = s.trim();
	}
	public String getUserid() {
		return userid;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(userid == null || userid.length() == 0) {
			errors.add("Please enter the user id:");
			return errors;
		}
		
		return errors;
	}
}
