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
	String price;
	
	public void setDate(String s) {
		date = s;
	}
	public String getDate() {
		return date;
	}
	
	public void setPrice(String s) {
		price = s.trim();
	}
	public String getPrice() {
		return price;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(date == null || date.length() == 0) {
			errors.add("Please input date");
		}
		if(!date.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})")) {
			errors.add("Date format is not correct");
		}
		
		if (price == null || price.length() == 0) {
			errors.add("Please enter the change on transition day:");
			return errors;
		}
		
		if (!price.matches("^[0-9]{1,12}([.][0-9]{1,2})?$")) {
			errors.add("Price number is not accepted");
			return errors;
		}
		
		return errors;
	}
}
