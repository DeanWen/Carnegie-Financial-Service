package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class TransitionForm extends FormBean{
	String date;
	String change;
	
	public void setDate(String s) {
		date = s.trim();
	}
	public String getDate() {
		return date;
	}
	
	public void setChange(String s) {
		change = s.trim();
	}
	public String getChange() {
		return change;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(date == null || date.length() == 0) {
			errors.add("Please enter the date:");
			return errors;
		}
		if (change == null || change.length() == 0) {
			errors.add("Please enter the change on transition day:");
			return errors;
		}
		
		return errors;
	}
}
