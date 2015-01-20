/*
 * Name: Dean Wen
 * Date: 1/16/2015
 */
package model;
import javax.servlet.*;

public class Model {
	private EmployeeDAO employeeDAO;
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private Fund_Price_History_DAO fundPriceHistoryDAO;
	private FundDAO fundDAO;
	private PositionDAO positionDAO;

	public Model(ServletConfig config) throws ServletException {
		try {
			String jdbcDriver = config.getInitParameter("jdbcDriverName");
			String jdbcURL = config.getInitParameter("jdbcURL");
			String db_username = config.getInitParameter("db_username");
			String db_password = config.getInitParameter("db_password"); 
			employeeDAO = new EmployeeDAO(jdbcDriver, jdbcURL, "Employee",db_username, db_password);
			customerDAO = new CustomerDAO(jdbcDriver, jdbcURL, "Customer",db_username, db_password);
			transactionDAO = new TransactionDAO(jdbcDriver, jdbcURL, "Transaction",db_username, db_password);
			fundPriceHistoryDAO = new Fund_Price_History_DAO(jdbcDriver, jdbcURL, "Fund_Price_History",db_username, db_password);
			fundDAO = new FundDAO(jdbcDriver, jdbcURL, "Fund",db_username, db_password);
			positionDAO = new PositionDAO(jdbcDriver, jdbcURL, "Position",db_username, db_password);
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
	
	public TransactionDAO getTransactionDAO() {
		return transactionDAO;
	}
	
	public Fund_Price_History_DAO getFundPriceHistoryDAO() {
		return fundPriceHistoryDAO;
	}
	
	public FundDAO getFundDAO() {
		return fundDAO;
	}
	
	public PositionDAO getPositionDAO() {
		return positionDAO;
	}
}
