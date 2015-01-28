/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.Model;
import model.MyDAOException;
import model.TransactionDAO;
import databean.CustomerBean;
import databean.TransactionBean;
import form.ViewCustomerAccountForm;

public class ViewCustomerAccountAction extends Action{
	private FormBeanFactory<ViewCustomerAccountForm> formBeanFactory = FormBeanFactory
			.getInstance(ViewCustomerAccountForm.class);
	TransactionDAO transactionDAO;
	public ViewCustomerAccountAction(Model model) {
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "viewCustomerAccount.do";
	}
	
	public String perform(HttpServletRequest request) {
		ViewCustomerAccountForm form = null;
		try{
			form = formBeanFactory.create(request);
		} catch (FormBeanException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("form", form);
		HttpSession session = request.getSession(true);
		TransactionBean transaction = null;
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		
		if (form.getAction().equals("Login")) {
			request.setAttribute("customer", customer);
			return "editCustomerAccount.do";
		}
//		if (session.getAttribute("customer") == null) {
//			return "login.jsp";
//		}
		//request.setAttribute("customer", customer);
		return "viewCustomerAccount.jsp";
	}
}
