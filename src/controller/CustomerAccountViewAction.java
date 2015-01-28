package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;
import databean.CustomerBean;
import databean.TransactionBean;

public class CustomerAccountViewAction extends Action{
	CustomerDAO customerDAO;
	TransactionDAO transactionDAO;
	public CustomerAccountViewAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "customerAccountView.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		TransactionBean transaction = null;
		CustomerBean oldCustomer = (CustomerBean) session.getAttribute("customer");
		int customer_id = oldCustomer.getCustomer_id();
		CustomerBean customer = null;
		try {
			customer = customerDAO.read(customer_id);
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			transaction = transactionDAO.last(customer.getCustomer_id());
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("lastTransaction", transaction);
		session.setAttribute("customer", customer);
		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}
		//request.setAttribute("customer", customer);
		return "customerAccountView.jsp";
	}
}
