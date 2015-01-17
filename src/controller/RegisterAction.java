/**
 * Homework 9 for 08600-Java J2EE Programming 
 * Author: Dian Wen (AndrewID: dwen) 
 * Date: Nov. 30th, 2014
 * Copyright(C) 2014 All rights reserved.  
 */

package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import model.EmployeeDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import form.RegisterForm;

public class RegisterAction extends Action {
	private FormBeanFactory<RegisterForm> formBeanFactory = FormBeanFactory
			.getInstance(RegisterForm.class);

	private EmployeeDAO userDAO;

	public RegisterAction(Model model) {
		userDAO = model.getEmployeeDAO();
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

			// Create the user bean
			EmployeeBean user = new EmployeeBean();
			if (userDAO.read(form.getUsername()) != null) {
				errors.add("User Exist");
				return "register.jsp";
			} else {
				user.setUsername(form.getUsername());
				user.setFirstname(form.getFirstname());
				user.setLastname(form.getLastname());
				user.setPassword(form.getPassword());
				userDAO.create(user);

// 				Attach (this copy of) the user bean to the session
//				HttpSession session = request.getSession(false);
//				session.setAttribute("user", user);
				request.setAttribute("message", "Register Successfully!");
				return "success.jsp";
			}
		} catch (FormBeanException | MyDAOException e) {
			errors.add(((Throwable) e).getMessage());
			return "register.jsp";
		}
	}
}
