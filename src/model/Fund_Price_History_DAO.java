package model;
/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
import java.sql.*;
import java.sql.Date;
import java.util.*;

import databean.Fund_Price_History_Bean;

public class Fund_Price_History_DAO {
	
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	private String db_username;
	private String db_password;

	public Fund_Price_History_DAO(String jdbcDriver, String jdbcURL, String tableName, String db_username, String db_password)
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

	public void create(Fund_Price_History_Bean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO "
							+ tableName
							+ "(price_date, price, Fund_fund_id) "
							+ "VALUES (?, ?, ?)");
			pstmt.setDate(1, (Date) item.getPrice_date());
			pstmt.setFloat(2, Float.parseFloat(item.getPrice()));
			pstmt.setInt(3, item.getFund_id());

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

	public void update(Fund_Price_History_Bean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE"
					+ tableName 
					+ "set price = ? "
				    + "where Fund_fund_id = ? and price_date = ?");
			
			pstmt.setFloat(1, Float.parseFloat(item.getPrice()));
			pstmt.setInt(2, item.getFund_id());
			pstmt.setDate(3, (Date) item.getPrice_date());
			
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

	public void delete(int fund_id, Date price_date) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM "
					+ tableName + " WHERE Fund_fund_id = ? and price_date = ?");
			pstmt.setInt(1, fund_id);
			pstmt.setDate(2, price_date);
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

	public Fund_Price_History_Bean read(int fund_id, Date price_date) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE Fund_fund_id = ? and price_date = ?");
			pstmt.setInt(1, fund_id);
			pstmt.setDate(2, price_date);
			ResultSet rs = pstmt.executeQuery();

			Fund_Price_History_Bean item;
			if (!rs.next()) {
				item = null;
			} else {
				item = new Fund_Price_History_Bean();
				item.setFund_id(rs.getInt("Fund_fund_id"));
				item.setPrice_date(rs.getDate("price_date"));
				item.setPrice(rs.getFloat("price"));
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
	
	public Fund_Price_History_Bean readLast(int fund_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE price_date = (SELECT MAX(price_date) from " + tableName + " WHERE Fund_fund_id = ?)");
			pstmt.setInt(1, fund_id);
			ResultSet rs = pstmt.executeQuery();

			Fund_Price_History_Bean item;
			if (!rs.next()) {
				item = null;
			} else {
				item = new Fund_Price_History_Bean();
				item.setFund_id(rs.getInt("Fund_fund_id"));
				item.setPrice_date(rs.getDate("price_date"));
				item.setPrice(rs.getFloat("price"));
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
