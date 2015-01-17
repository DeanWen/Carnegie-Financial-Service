/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
package model;
import javax.servlet.*;

public class Model {
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");
			String db_username = config.getInitParameter("db_username");
			String db_password = config.getInitParameter("db_password"); 
			employeeDAO = new EmployeeDAO(jdbcDriver, jdbcURL, "Employee",db_username, db_password);
			customerDAO = new CustomerDAO(jdbcDriver, jdbcURL, "Customer",db_username, db_password);
		} catch (MyDAOException e) {
			throw new ServletException(e);
		}
	}

	public EmployeeDAO getEmployeeDAO() {
		return employeeDAO;
	}

	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}
}
