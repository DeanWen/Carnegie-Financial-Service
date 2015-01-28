/*
 * Author: Qiyue Ma (AndrewID: qma) 
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
import form.CreateCustomerAccountForm;

public class CreateCustomerAccountAction extends Action {
	private FormBeanFactory<CreateCustomerAccountForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateCustomerAccountForm.class);

	private CustomerDAO customerDAO;
	public CreateCustomerAccountAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "createCustomerAccount.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {	
			CreateCustomerAccountForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			if (!form.isPresent()) {
				return "createCustomerAccount.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "createCustomerAccount.jsp";
			}
			
			CustomerBean customer = new CustomerBean();
			if(customerDAO.read(Integer.parseInt(form.getCustomer_id())) != null) {
				errors.add("User Exist");
				return "createCustomerAccount.jsp";
			} else {
				customer.setCustomer_id(Integer.parseInt(form.getCustomer_id()));
				customer.setUsername(form.getUsername());
				customer.setFirstname(form.getFirstname());
				customer.setLastname(form.getLastname());
				customer.setPassword(form.getPassword());
				customer.setAddr_line1(form.getAddr_line1());
				customer.setAddr_line2(form.getAddr_line2());
				customer.setCity(form.getCity());
				customer.setState(form.getState());
				customer.setZip(Integer.parseInt(form.getZip()));
				customerDAO.create(customer);
				request.setAttribute("message", "Create Customer Account Successfully!");
				return "success.jsp";
			}
		} catch (FormBeanException | MyDAOException e) {
			errors.add(((Throwable) e).getMessage());
			return "createCustomerAccount.jsp";
		}
	}
}
