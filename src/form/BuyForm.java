package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean{
	String id;
	String buyAmount;
	String cfmAmount;
	String action;
	
	public String getId() {
		return id;
	}
	
	public int getIdAsInt() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be
		// thrown!
		return Integer.parseInt(id);
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setBuyAmount(String s) {
		buyAmount = s.trim();
	}
	public String getBuyAmount() {
		return buyAmount;
	}
	
	public void setCfmAmount(String s) {
		cfmAmount = s.trim();
	}
	public String getCfmAmount() {
		return cfmAmount;
	}
	
	public void setAction(String s) {
		action = s.trim();
	}
	public String getAction() {
		return action;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (buyAmount == null || buyAmount.length() == 0) {
			errors.add("Please enter the amount of shares you want to buy");
			return errors;
		}
		
		if (buyAmount != null && buyAmount.length() != 0) {
			float tmp = Float.parseFloat(buyAmount);
			if (tmp < 0.01) {
				errors.add("Buy amount should be larger than $0.01");
			}
		}
		
		if (cfmAmount == null || cfmAmount.length() == 0) {
			errors.add("Please confirm shares you want to buy");
			return errors;
		}
		if (!cfmAmount.equals(buyAmount)) {
			errors.add("Shares amounts are not consistent");
		}
		
		if(action == null || !action.equals("Buy")) {
			errors.add("Invalid Button");
		}
		return errors;
	}
}
