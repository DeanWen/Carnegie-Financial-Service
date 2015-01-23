package model;
/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
import java.sql.*;
import java.util.*;

import databean.PositionBean;

public class PositionDAO {
	
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	private String db_username;
	private String db_password;

	public PositionDAO(String jdbcDriver, String jdbcURL, String tableName, String db_username, String db_password)
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

	public void create(PositionBean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO "
							+ tableName
							+ "(Fund_fund_id, Customer_customer_id, shares) "
							+ "VALUES (?, ?, ?)");
			pstmt.setInt(1, item.getFund_id());
			pstmt.setInt(2, item.getCustomer_id());
			pstmt.setBigDecimal(3, item.getShares());

			int count = pstmt.executeUpdate();
			if (count != 1) {
				throw new SQLException("Insert updated " + count + " rows");
			}
			con.commit();
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

	public void update(PositionBean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement("UPDATE "
					+ tableName 
					+ " set shares = ? "
				    + "where Fund_fund_id = ? and Customer_customer_id = ? ");
			
			pstmt.setBigDecimal(1, item.getShares());
			pstmt.setInt(2, item.getFund_id());
			pstmt.setInt(3, item.getCustomer_id());
			
			int count = pstmt.executeUpdate();
			if (count != 1) {
				throw new SQLException("Insert updated " + count + " rows");
			}
			con.commit();
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

	public void delete(int fund_id, int customer_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM "
					+ tableName + " where Fund_fund_id = ? and Customer_customer_id = ? ");
			pstmt.setInt(1, fund_id);
			pstmt.setInt(2, customer_id);

			int count = pstmt.executeUpdate();
			if (count != 1) {
				throw new SQLException("Delete updated" + count + "rows");
			}
			con.commit();
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

	public PositionBean read(int fund_id, int customer_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE Fund_fund_id = ? AND Customer_customer_id = ?");
			pstmt.setInt(1, fund_id);
			pstmt.setInt(2, customer_id);
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			PositionBean item;
			if (!rs.next()) {
				item = null;
			} else {
				item = new PositionBean();
				item.setFund_id(rs.getInt("Fund_fund_id"));
				item.setCustomer_id(rs.getInt("Customer_customer_id"));
				item.setShares(rs.getBigDecimal("shares"));
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
	
	public ArrayList<PositionBean> getPositions(int customer_id) throws MyDAOException {
		Connection con = null;
		ArrayList<PositionBean> positions = new ArrayList<PositionBean>();
		try {
			con = getConnection();
			con.setAutoCommit(false);
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE Customer_customer_id = ? ORDER BY Fund_fund_id ASC");
			pstmt.setInt(1, customer_id);
			ResultSet rs = pstmt.executeQuery();
			con.commit();
			PositionBean item;
			while(rs.next()) {
				item = new PositionBean();
				item.setFund_id(rs.getInt("Fund_fund_id"));
				item.setCustomer_id(rs.getInt("Customer_customer_id"));
				item.setShares(rs.getBigDecimal("shares"));
				positions.add(item);
			}

			rs.close();
			pstmt.close();
			releaseConnection(con);
			return positions;
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
