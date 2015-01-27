package databean;

import java.io.Serializable;
import java.math.BigDecimal;

public class RecordBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fundName;
	private String fundSymbol;
	private BigDecimal shares;
	private BigDecimal price;
	private BigDecimal value;
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
	
	public BigDecimal getShares() {
		return shares;
	}
	public void setShares(BigDecimal shares) {
		this.shares = shares;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public int getFundID() {
		return fundID;
	}
	public void setFundID(int id) {
		fundID = id;
	}
}
