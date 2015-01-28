package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class RequestCheckForm extends FormBean {
	private String requestAmount;
	private String requestCfmAmount;

	public String getRequestAmount() {
		return requestAmount;
	}

	public void setRequestAmount(String amount) {
		requestAmount = amount.trim();
	}
	
	public String getRequestCfmAmount() {
		return requestCfmAmount;
	}

	public void setRequestCfmAmount(String amount) {
		requestCfmAmount = amount.trim();
	}
	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(requestAmount == null || requestAmount.length() == 0) {
			errors.add("Please enter the amount");
			return errors;
		}
		if(!requestAmount.matches("^[0-9]{1,12}([.][0-9]{1,2})?$")) {
			errors.add("Amount number is not accepted");
			return errors;
		}
		if (requestCfmAmount == null || requestCfmAmount.length() == 0) {
			errors.add("Please enter the confirm amount");
			return errors;
		}
		
		if (!requestAmount.equals(requestCfmAmount)) {
			errors.add("Please confrim your amount!");
		}
		
		return errors;
	}
}

