/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
package model;

import java.sql.*;
import java.util.*;

import databean.EmployeeBean;

public class EmployeeDAO {
	private List<Connection> connectionPool = new ArrayList<Connection>();

	private String jdbcDriver;
	private String jdbcURL;
	private String tableName;
	private String db_username;
	private String db_password;

	public EmployeeDAO(String jdbcDriver, String jdbcURL, String tableName, String db_username, String db_password)
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

	public void create(EmployeeBean user) throws MyDAOException {
		Connection con = null;

		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement("INSERT INTO "
					+ tableName + " (username, firstname, "
					+ "lastname, password) VALUES (?,?,?,?)");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getFirstname());
			pstmt.setString(3, user.getLastname());
			pstmt.setString(4, user.getPassword());

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

	public EmployeeBean read(String username) throws MyDAOException {
		Connection con = null;
		try {
			con = getConnection();

			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM "
					+ tableName + " WHERE username = ?");
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();

			EmployeeBean user;
			if (!rs.next()) {
				user = null;
			} else {
				user = new EmployeeBean();
				user.setUsername(rs.getString("username"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
			}

			rs.close();
			pstmt.close();
			releaseConnection(con);
			return user;
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

	public ArrayList<EmployeeBean> getUsers() throws MyDAOException {
		ArrayList<EmployeeBean> users = new ArrayList<EmployeeBean>();
		Connection con = null;
		String sql = "SELECT * FROM " + tableName;
		try {
			con = getConnection();

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			EmployeeBean user;
			while (rs.next()) {
				user = new EmployeeBean();
				user.setUsername(rs.getString("username"));
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setPassword(rs.getString("password"));
				users.add(user);
			}

			rs.close();
			stmt.close();
			releaseConnection(con);
			return users;
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

	public void update(EmployeeBean user) throws MyDAOException {
		Connection con = null;
		String sql = "UPDATE " + tableName
				+ " set password = ? where username = ?";
		try {
			con = getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUsername());
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
}
