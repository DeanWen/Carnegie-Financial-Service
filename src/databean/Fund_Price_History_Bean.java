package databean;

/*
 * Name: Dean Wen
 * Date: 11/26/2014
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Fund_Price_History_Bean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Date price_date;
	private BigDecimal price;
	private int fund_id;

	public Date getPrice_date() {
		return price_date;
	}

	public void setPrice_date(Date price_date) {
		this.price_date = price_date;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}

}
