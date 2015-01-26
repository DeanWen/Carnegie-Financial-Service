package databean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HistoryBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private Date date;
	private String type;
	private String fundName;
	private BigDecimal shares;
	private BigDecimal price;
	private BigDecimal amount;
	
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
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
