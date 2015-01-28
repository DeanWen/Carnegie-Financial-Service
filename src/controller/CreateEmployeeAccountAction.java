/*
 * Author: Qiyue Ma (AndrewID: qma) 
 * Date: Nov. 30th, 2014
 * Copyright(C) 2014 All rights reserved.  
 */

package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import model.EmployeeDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import form.CreateEmployeeAccountForm;

public class CreateEmployeeAccountAction extends Action {
	private FormBeanFactory<CreateEmployeeAccountForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateEmployeeAccountForm.class);

	private EmployeeDAO employeeDAO;
	public CreateEmployeeAccountAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}

	public String getName() {
		return "createEmployeeAccount.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);

		try {	
			CreateEmployeeAccountForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			HttpSession session = request.getSession();
			if (session.getAttribute("employee") == null) {
				return "login.jsp";
			}
			if (!form.isPresent()) {
				return "createEmployeeAccount.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "createEmployeeAccount.jsp";
			}
			
			EmployeeBean employee = new EmployeeBean();
			if(employeeDAO.read(form.getUsername()) != null) {
				errors.add("User Exist");
				return "createEmployeeAccount.jsp";
			} else {
				employee.setUsername(form.getUsername());
				employee.setFirstname(form.getFirstname());
				employee.setLastname(form.getLastname());
				employee.setPassword(form.getPassword());
				employeeDAO.create(employee);
				request.setAttribute("message", "Create Employee Account Successfully!");
				return "success.jsp";
			}
		} catch (FormBeanException | MyDAOException e) {
			errors.add(((Throwable) e).getMessage());
			return "createEmployeeAccount.jsp";
		}
	}
}
