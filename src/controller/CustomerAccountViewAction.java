package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.MyDAOException;
import model.TransactionDAO;

import databean.CustomerBean;
import databean.TransactionBean;

public class CustomerAccountViewAction extends Action{
	
	TransactionDAO transactionDAO;
	public CustomerAccountViewAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "customerAccountView.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		TransactionBean transaction = null;
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		try {
			transaction = transactionDAO.last(customer.getCustomer_id());
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("lastTransaction", transaction);

		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}
		//request.setAttribute("customer", customer);
		return "customerAccountView.jsp";
	}
}
