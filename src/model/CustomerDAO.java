/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
package model;
import java.sql.*;
import java.util.*;

import databean.CustomerBean;

public class CustomerDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	private String db_username;
	private String db_password;

	public CustomerDAO(String jdbcDriver, String jdbcURL, String tableName, String db_username, String db_password)
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

	public void create(CustomerBean customer) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con
					.prepareStatement("INSERT INTO "
							+ tableName
							+ "(customer_id, username, password, firstname, lastname, addr_line1, addr_line2, city, state, zip, cash) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, customer.getCustomer_id());
			pstmt.setString(2, customer.getUsername());
			pstmt.setString(3, customer.getPassword());
			pstmt.setString(4, customer.getFirstname());
			pstmt.setString(5, customer.getLastname());
			pstmt.setString(6, customer.getAddr_line1());
			pstmt.setString(7, customer.getAddr_line2());
			pstmt.setString(8, customer.getCity());
			pstmt.setString(9, customer.getState());
			pstmt.setInt(10, customer.getZip());
			pstmt.setBigDecimal(11, customer.getCash());

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

	public void update(CustomerBean customer) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("UPDATE "
					+ tableName 
					+ " set username = ? "
					  + ", password = ? "
					  + ", firstname = ? "
					  + ", lastname = ? "
					  + ", addr_line1 = ? "
					  + ", addr_line2 = ? "
					  + ", city = ? "
					  + ", state = ? "
					  + ", zip = ? "
					  + ", cash = ? "
				    + "where customer_id = ?");
			pstmt.setString(1, customer.getUsername());
			pstmt.setString(2, customer.getPassword());
			pstmt.setString(3, customer.getFirstname());
			pstmt.setString(4, customer.getLastname());
			pstmt.setString(5, customer.getAddr_line1());
			pstmt.setString(6, customer.getAddr_line2());
			pstmt.setString(7, customer.getCity());
			pstmt.setString(8, customer.getState());
			pstmt.setInt(9, customer.getZip());
			pstmt.setBigDecimal(10, customer.getCash());
			pstmt.setInt(11, customer.getCustomer_id());
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

	public void delete(int customer_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("DELETE FROM "
					+ tableName + " WHERE customer_id = ?");
			pstmt.setInt(1, customer_id);
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

	public CustomerBean read(int customer_id) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE customer_id = ?");
			pstmt.setInt(1, customer_id);
			ResultSet rs = pstmt.executeQuery();

			CustomerBean customer;
			if (!rs.next()) {
				customer = null;
			} else {
				customer = new CustomerBean();
				customer.setCustomer_id(rs.getInt("customer_id"));
				customer.setUsername(rs.getString("username"));
				customer.setFirstname(rs.getString("firstname"));
				customer.setLastname(rs.getString("lastname"));
				customer.setPassword(rs.getString("password"));
				customer.setAddr_line1(rs.getString("addr_line1"));
				customer.setAddr_line2(rs.getString("addr_line2"));
				customer.setCity(rs.getString("city"));
				customer.setState(rs.getString("state"));
				customer.setState(rs.getString("state"));
				customer.setZip(rs.getInt("zip"));
				customer.setCash(rs.getBigDecimal("cash"));
			}

			rs.close();
			pstmt.close();
			releaseConnection(con);
			return customer;
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
