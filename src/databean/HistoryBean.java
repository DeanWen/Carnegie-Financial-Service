package databean;

import java.io.Serializable;
import java.util.Date;

public class HistoryBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date date;
	private String type;
	private String fundName;
	private String shares;
	private String price;
	private String amount;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date d) {
		this.date = d;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
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
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = String.format("%.2f", amount);
	}
}
