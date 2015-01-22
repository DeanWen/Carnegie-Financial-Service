package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class BuyForm extends FormBean{
	String fundName;
	String buyAmount;
	String cfmAmount;
	String action;
	
	public void setFundName(String s) {
		fundName = s.trim();
	}
	public String getFundName() {
		return fundName;
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

		if(fundName == null || fundName.length() == 0) {
			errors.add("Please enter the fund name");
		}
		if (buyAmount == null || buyAmount.length() == 0) {
			errors.add("Please enter the amount of shares you want to buy");
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
		
		if(action == null || !action.equals("Done")) {
			errors.add("Invalid Button");
		}
		return errors;
	}
}
