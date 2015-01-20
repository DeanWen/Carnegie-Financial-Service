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
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import form.LoginForm;


public class LoginAction extends Action{
	private FormBeanFactory<LoginForm> formBeanFactory = FormBeanFactory
			.getInstance(LoginForm.class);

	private CustomerDAO customerDAO;

	public LoginAction(Model model) {
		customerDAO = model.getCustomerDAO();
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
		CustomerBean customer = null;
		try {
			customer = customerDAO.read(Integer.parseInt(form.getUserid()));
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

		if (form.getAction().equals("Login")) {
			session.setAttribute("customer", customer);
			return "customerAccountView.do";
		}
		return "login.jsp";
	}
}