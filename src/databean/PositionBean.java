package databean;

/*
 * Name: Dean Wen
 * Date: 11/26/2014
 */

import java.io.Serializable;

public class PositionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private float shares;
	private int customer_id;
	private int fund_id;

	public float getShares() {
		return shares;
	}

	public void setShares(float shares) {
		this.shares = shares;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getFund_id() {
		return fund_id;
	}

	public void setFund_id(int fund_id) {
		this.fund_id = fund_id;
	}
}
