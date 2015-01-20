package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CustomerAccountViewForm extends FormBean{
	private String action;
	
	public void setAction(String s) {
		this.action = s;
	}
	
	public String getAction() {
		return action;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();
		
		if(!action.equals("Edit")) {
			errors.add("Invalid button");
		}
		return errors;
	}
}
