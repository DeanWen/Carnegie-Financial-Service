package model;
/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
import java.sql.*;
import java.util.*;

import databean.FundBean;

public class FundDAO {
	
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	private String db_username;
	private String db_password;

	public FundDAO(String jdbcDriver, String jdbcURL, String tableName, String db_username, String db_password)
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

	public void create(FundBean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO "
							+ tableName
							+ "(fund_id, name, symbol) "
							+ "VALUES (?, ?, ?)");
			pstmt.setInt(1, item.getFund_id());
			pstmt.setString(2, item.getName());
			pstmt.setString(3, item.getSymbol());

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

	public void update(FundBean item) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE"
					+ tableName 
					+ "set name = ? "
					+ ", symbol = ? "
				    + "where fund_id = ? ");
		
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getSymbol());
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

			}
			throw new MyDAOException(e);
		}
	}

	public void delete(int fund_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM "
					+ tableName + " WHERE fund_id = ? ");
			pstmt.setInt(1, fund_id);

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

	public FundBean read(int fund_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE fund_id = ? ");
			pstmt.setInt(1, fund_id);
			ResultSet rs = pstmt.executeQuery();

			FundBean item;
			if (!rs.next()) {
				item = null;
			} else {
				item = new FundBean();
				item.setFund_id(rs.getInt("fund_id"));
				item.setName(rs.getString("name"));
				item.setSymbol(rs.getString("symbol"));
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
	
	public FundBean readByName(String fundName) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE name = ? ");
			pstmt.setString(1, fundName);
			ResultSet rs = pstmt.executeQuery();

			FundBean item;
			if (!rs.next()) {
				item = null;
			} else {
				item = new FundBean();
				item.setFund_id(rs.getInt("fund_id"));
				item.setName(rs.getString("name"));
				item.setSymbol(rs.getString("symbol"));
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
