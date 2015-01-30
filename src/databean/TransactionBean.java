/*
 *  Team 14 Infinity
 *  Task 7
 *  CMU - eBiz
 */
package databean;

/*
 * Name: Dean Wen
 * Date: 11/26/2014
 */

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;

public class TransactionBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private int transaction_id;
	private int customer_id;
	private int fund_id;
	private Date execute_date;
	private BigDecimal shares;
	private String transaction_type;
	private BigDecimal amount;
	private int status;
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
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

	public Date getExecute_date() {
		return execute_date;
	}

	public void setExecute_date(Date execute_date) {
		this.execute_date = execute_date;
	}



	public String getTransaction_type() {
		return transaction_type;
	}

	public void setTransaction_type(String transaction_type) {
		this.transaction_type = transaction_type;
	}

	public BigDecimal getShares() {
		return shares;
	}

	public void setShares(BigDecimal shares) {
		this.shares = shares;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


}
