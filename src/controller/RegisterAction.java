/*
 * Homework 9 for 08600-Java J2EE Programming 
 * Author: Dian Wen (AndrewID: dwen) 
 * Date: Nov. 30th, 2014
 * Copyright(C) 2014 All rights reserved.  
 */

package controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import form.RegisterForm;

public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory
			.getInstance(RegisterForm.class);

	private CustomerDAO customerDAO;
	public RegisterAction(Model model) {
		model.getEmployeeDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "register.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {	
			RegisterForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "register.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "register.jsp";
			}
			
			CustomerBean customer = new CustomerBean();
			if(customerDAO.read(Integer.parseInt(form.getUserid())) != null) {
				errors.add("User Exist");
				return "register.jsp";
			} else {
				customer.setCustomer_id(Integer.parseInt(form.getUserid()));
				customer.setFirstname(form.getFirstname());
				customer.setLastname(form.getLastname());
				customer.setPassword(form.getPassword());
				customerDAO.create(customer);
				request.setAttribute("message", "Register Successfully!");
				return "success.jsp";
			}
		} catch (FormBeanException | MyDAOException e) {
			errors.add(((Throwable) e).getMessage());
			return "register.jsp";
		}
	}
}
