/*
 * Name: Dean Wen
 * Date: 1/18/2015
 */
package model;
import java.sql.*;
import java.sql.Date;
import java.util.*;

import databean.TransactionBean;

public class TransactionDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	private String db_username;
	private String db_password;

	public TransactionDAO(String jdbcDriver, String jdbcURL, String tableName, String db_username, String db_password)
			throws MyDAOException {
		this.jdbcDriver = jdbcDriver;
		this.jdbcURL = jdbcURL;
		this.tableName = tableName;
		this.db_username = db_username;
		this.db_password = db_password;
	}

	private synchronized Connection getConnection() throws MyDAOException {
		if (connectionPool.size() > 0) {
			return connectionPool.remove(connectionPool.size() - 1);
		}

		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			throw new MyDAOException(e);
		}

		try {
			return DriverManager.getConnection(jdbcURL, db_username, db_password);
		} catch (SQLException e) {
			throw new MyDAOException(e);
		}
	}

	private synchronized void releaseConnection(Connection con) {
		connectionPool.add(con);
	}

	public void create(TransactionBean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO "
							+ tableName
							+ "(transaction_id, customer_id, fund_id, execute_date, shares, transaction_type, amount) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, item.getTransaction_id());
			pstmt.setInt(2, item.getCustomer_id());
			pstmt.setInt(3, item.getFund_id());
			pstmt.setDate(4, (Date) item.getExecute_date());
			pstmt.setFloat(5, item.getShares());
			pstmt.setString(6, item.getTransaction_type());
			pstmt.setFloat(7, item.getAmount());

			int count = pstmt.executeUpdate();
			if (count != 1) {
				throw new SQLException("Insert updated " + count + " rows");
			}

			pstmt.close();
			releaseConnection(con);
		} catch (SQLException e) {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e2) {
				/* ignore */
			}
			throw new MyDAOException(e);
		}
	}

	public void update(TransactionBean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE"
					+ tableName 
					+ "set customer_id = ? "
					  + ", fund_id = ? "
					  + ", execute_date = ? "
					  + ", shares = ? "
					  + ", transaction_type = ? "
					  + ", amount = ? "
				    + "where transaction_id = ?");
			pstmt.setInt(1, item.getCustomer_id());
			pstmt.setInt(2, item.getFund_id());
			pstmt.setDate(3, (Date) item.getExecute_date());
			pstmt.setFloat(4, item.getShares());
			pstmt.setString(5, item.getTransaction_type());
			pstmt.setFloat(6, item.getAmount());
			pstmt.setInt(7, item.getTransaction_id());
			
			int count = pstmt.executeUpdate();
			if (count != 1) {
				throw new SQLException("Insert updated " + count + " rows");
			}

			pstmt.close();
			releaseConnection(con);
		} catch (SQLException e) {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e2) {

			}
			throw new MyDAOException(e);
		}
	}

	public void delete(int transaction_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM "
					+ tableName + " WHERE transaction_id = ?");
			pstmt.setInt(1, transaction_id);
			int count = pstmt.executeUpdate();
			if (count != 1) {
				throw new SQLException("Delete updated" + count + "rows");
			}
			pstmt.close();
			releaseConnection(con);
		} catch (SQLException e) {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e2) {

			}
			throw new MyDAOException(e);
		}
	}

	public TransactionBean read(int transaction_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE transaction_id = ?");
			pstmt.setInt(1, transaction_id);
			ResultSet rs = pstmt.executeQuery();

			TransactionBean item;
			if (!rs.next()) {
				item = null;
			} else {
				item = new TransactionBean();
				item.setTransaction_id(rs.getInt("transaction_id"));
				item.setCustomer_id(rs.getInt("customer_id"));
				item.setFund_id(rs.getInt("fund_id"));
				item.setShares(rs.getFloat("shares"));
				item.setExecute_date(rs.getDate("execute_date"));
				item.setTransaction_type(rs.getString("transaction_type"));
				item.setAmount(rs.getFloat("amount"));
			}

			rs.close();
			pstmt.close();
			releaseConnection(con);
			return item;
		} catch (SQLException e) {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e2) {
				/* ignore */
			}
			throw new MyDAOException(e);
		}
	}
}
