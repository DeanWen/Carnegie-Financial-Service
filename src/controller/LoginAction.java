/*
 * Carnegie Finance Service 
 * Author: Xiaodong Zhou (AndrewID: xiaodonz) 
 * Date: Jan. 18th, 2015
 * Copyright(C) 2015 All rights reserved.  
 */
package controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.EmployeeDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.EmployeeBean;
import form.LoginForm;


public class LoginAction extends Action{
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);

	private CustomerDAO customerDAO;
	private EmployeeDAO employeeDAO;
	
	public LoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
		employeeDAO = model.getEmployeeDAO();
	}
	
	public String getName() {
		return "login.do";
	}
	
	public String perform(HttpServletRequest request) {
		LoginForm form = null;
		try{
			form = formBeanFactory.create(request);
		} catch (FormBeanException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("form", form);
		
		HttpSession session = request.getSession(true);

		if (session.getAttribute("user") != null) {
			return "homepage.jsp";
		}
		
		if(!form.isPresent()) {
			return "login.jsp";
		}
		
		List<String> errors = new ArrayList<String>();
		errors.addAll(form.getValidationErrors());

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "login.jsp";
		}
		

		if (form.getAction().equals("Login")) {
			CustomerBean customer = null;
			try {
				try {
					customer = customerDAO.read(Integer.parseInt(form.getUserid()));
				} catch (NumberFormatException e) {
					errors.add("Please enter digits in order to login as customer");
					request.setAttribute("errors", errors);
					return "login.jsp";
				}
				if (customer == null) {
					errors = new ArrayList<String>();
					errors.add("This account does not exists");
					request.setAttribute("errors", errors);
					return "login.jsp";
				} else if(!customer.getPassword().equals(form.getPassword())){
					errors.add("Password is wrong");
					request.setAttribute("errors", errors);
					return "login.jsp";
				}
			} catch (MyDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			session.setAttribute("customer", customer);
			return "customerAccountView.do";
		}
		if (form.getAction().equals("Login as Employee")) {
			EmployeeBean employee = null;
			try {
				employee = employeeDAO.read(form.getUserid());
				if (employee == null) {
					errors = new ArrayList<String>();
					errors.add("This account does not exist");
					request.setAttribute("errors", errors);
					return "login.jsp";
				} else if(!employee.getPassword().equals(form.getPassword())){
					errors.add("Password is wrong");
					request.setAttribute("errors", errors);
					return "login.jsp";
				}
			} catch (MyDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			session.setAttribute("employee", employee);
			return "transition.do";
		}
		return "login.jsp";
	}
}
