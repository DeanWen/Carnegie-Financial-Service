/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package form;

import java.util.ArrayList;
import java.util.List;

import org.mybeans.form.FormBean;

public class CreateFundForm extends FormBean{
	String fundName;
	String fundTicker;
	
	public void setFundName(String s) {
		fundName = s.trim();
	}
	public String getFundName() {
		return fundName;
	}
	
	public void setFundTicker(String s) {
		fundTicker = s.trim();
	}
	public String getFundTicker() {
		return fundTicker;
	}
	
	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if(fundName == null || fundName.length() == 0) {
			errors.add("Please enter the fund name.");
			return errors;
		}
		if (fundTicker == null || fundTicker.length() == 0) {
			errors.add("Please enter the fund ticker.");
			return errors;
		}
		
		if (!fundTicker.matches("^[A-Za-z0-9]{1,5}$")) {
			errors.add("Please enter a 1 to 5 letters or digits ticker.");
			return errors;
		}
		
		return errors;
	}
}
