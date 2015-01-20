package databean;

import java.io.Serializable;

public class RecordBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fundName;
	private String fundSymbol;
	private String shares;
	private String price;
	private String value;
	private int fundID;
	
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	
	public String getFundSymbol() {
		return fundSymbol;
	}
	public void setFundSymbol(String fundSymbol) {
		this.fundSymbol = fundSymbol;
	}
	
	public String getShares() {
		return shares;
	}
	public void setShares(float shares) {
		this.shares = String.format("%.3f", shares);
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = String.format("%.2f", price);
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = String.format("%.2f", value);
	}
	
	public int getFundID() {
		return fundID;
	}
	public void setFundID(int id) {
		fundID = id;
	}
}
