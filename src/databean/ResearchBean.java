package databean;

import java.math.BigDecimal;

public class ResearchBean {
	private int fund_id;
	private String name;
	private String symbol;
	private BigDecimal price;
	private BigDecimal shares;
		
	public int getFund_id() {
		return fund_id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public BigDecimal getShare() {
		return shares;
	}
	
	public void setFund_id(int id) {
		fund_id = id;
	}
	
	public void setName(String s) {
		name = s.trim();
	}
	
	public void setSymbol(String s) {
		symbol = s.trim();
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public void setShare(BigDecimal share) {
		shares = share;
	}
	

}
