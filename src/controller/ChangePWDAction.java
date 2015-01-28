package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import form.ChangePWDForm;
import model.CustomerDAO;
import model.Model;
import model.MyDAOException;

public class ChangePWDAction extends Action{
	private FormBeanFactory<ChangePWDForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangePWDForm.class);
	private CustomerDAO customerDAO;
	
	public ChangePWDAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "changePWD.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		
		ChangePWDForm form = null;
		try{
			form = formBeanFactory.create(request);
		} catch (FormBeanException e1) {
			e1.printStackTrace();
		}
		if(!form.isPresent()) {
			return "changePWD.jsp";
		}
		request.setAttribute("form", form);
		List<String> errors = new ArrayList<String>();
		errors.addAll(form.getValidationErrors());
		String oldPWD = form.getOldPWD();
		String cfmPWD = form.getCfmPWD();
		
		if(!customer.getPassword().equals(oldPWD)) {
			errors.add("Old password is not correct");
		}
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "changePWD.jsp";
		}
		
		customer.setPassword(cfmPWD);
		boolean check = false;
		try {
			customerDAO.update(customer);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("check", check);
			return "changePWD.jsp";
		}
		check = true;
		request.setAttribute("check", check);
		return "changePWD.jsp";
	}
}
