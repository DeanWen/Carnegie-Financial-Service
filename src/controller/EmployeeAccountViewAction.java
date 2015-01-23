package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;
import model.MyDAOException;
import model.TransactionDAO;
import databean.CustomerBean;
import databean.EmployeeBean;
import databean.TransactionBean;

public class EmployeeAccountViewAction extends Action{
	
	TransactionDAO transactionDAO;
	public EmployeeAccountViewAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "employeeAccountView.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}
		//request.setAttribute("customer", customer);
		return "employeeAccountView.jsp";
	}
}
