/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.TransactionBean;
import form.EditCustomerAccountForm;

public class EditCustomerAccountAction extends Action{
	private FormBeanFactory<EditCustomerAccountForm> formBeanFactory = 
	FormBeanFactory.getInstance(EditCustomerAccountForm.class);
	
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	
	public EditCustomerAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "editCustomerAccount.do";
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
		
		EditCustomerAccountForm form = null;
		try{
		form = formBeanFactory.create(request);
		} catch (FormBeanException e1) {
		e1.printStackTrace();
		}
		
		if(!form.isPresent()) {
			return "editCustomerAccount.jsp";
		}
		request.setAttribute("form", form);
		List<String> errors = new ArrayList<String>();
		errors.addAll(form.getValidationErrors());
		if(errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "editCustomerAccount.jsp";
		}
	
		
		customer.setFirstname(form.getFirstname());
		customer.setLastname(form.getLastname());
		customer.setAddr_line1(form.getAddr1());
		customer.setAddr_line2(form.getAddr2());
		customer.setCity(form.getCity());
		customer.setState(form.getState());
		customer.setZip(Integer.parseInt(form.getZip()));
		System.out.println("State:" + form.getState());
		if(form.getAction().equals("Done")) {
			System.out.println("Done");
			try {
				customerDAO.update(customer);
			} catch (MyDAOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
				errors.add("Update database failed");
				request.setAttribute("errors", errors);
				return "editCustomerAccount.jsp";
			}			
			request.setAttribute("customer", customer);
			session.removeAttribute("customer");
			return "viewCustomerAccount.jsp";
		}
		return "editCustomerAccount.jsp";
	}
}
