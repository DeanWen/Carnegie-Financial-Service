/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransitionForm extends FormBean{
	String date;
	
	public void setDate(String s) {
		date = s.trim();
	}
	public String getDate() {
		return date;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(date == null || date.length() == 0) {
			errors.add("Please enter the date:");
			return errors;
		}
		
		return errors;
	}
}
