package databean;

import java.math.BigDecimal;

public class TransitionIdBean {
	private int fund_id;
	private BigDecimal price;
	
	public int getFund_id() {
		return fund_id;
	}
	public void setFund_id(int id) {
		fund_id = id;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
