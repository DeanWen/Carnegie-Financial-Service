package form;

import java.util.ArrayList;
import java.util.List;

public class TransitionIdForm {
	private String id;
	private String price;
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	public String getPrice() {
		return price;
	}
	public int getIdAsInt() {
		// Be sure to first call getValidationErrors() to ensure
		// that NullPointer exception or NumberFormatException will not be
		// thrown!
		return Integer.parseInt(id);
	}

	

	public List<String> getValidationErrors() {
		List<String> errors = new ArrayList<String>();

		if (id == null || id.length() == 0) {
			errors.add("Id is required");
			return errors;
		}

		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			errors.add("Id is not an integer");
		}
		
		if(!price.matches("^[0-9]{1,12}([.][0-9]{1,2})?$")) {
			errors.add("Price number is not accepted");
		}

		return errors;
	}
}
