package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class SellForm extends FormBean{
	String fundName;
	String sellAmount;
	String cfmAmount;
	String action;
	
	public void setFundName(String s) {
		fundName = s.trim();
	}
	public String getFundName() {
		return fundName;
	}
	
	public void setSellAmount(String s) {
		sellAmount = s.trim();
	}
	public String getSellAmount() {
		return sellAmount;
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
			return errors;
		}
		if (sellAmount == null || sellAmount.length() == 0) {
			errors.add("Please enter the amount of shares you want to sell");
			return errors;
		}
		if (cfmAmount == null || cfmAmount.length() == 0) {
			errors.add("Please confirm shares you want to sell");
			return errors;
		}
		if (!cfmAmount.equals(sellAmount)) {
			errors.add("Shares amounts are not consistent");
			return errors;
		}
		
		if(action == null || !action.equals("Done")) {
			errors.add("Invalid Button");
		}
		return errors;
	}
}
