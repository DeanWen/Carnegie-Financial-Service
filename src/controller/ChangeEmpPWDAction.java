package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.EmployeeBean;
import form.ChangeEmpPWDForm;
import model.EmployeeDAO;
import model.Model;
import model.MyDAOException;

public class ChangeEmpPWDAction extends Action{
	private FormBeanFactory<ChangeEmpPWDForm> formBeanFactory = FormBeanFactory
			.getInstance(ChangeEmpPWDForm.class);
	private EmployeeDAO employeeDAO;
	
	public ChangeEmpPWDAction(Model model) {
		employeeDAO = model.getEmployeeDAO();
	}
	
	public String getName() {
		return "changeEmpPWD.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
		
		ChangeEmpPWDForm form = null;
		try{
			form = formBeanFactory.create(request);
		} catch (FormBeanException e1) {
			e1.printStackTrace();
		}
		if(!form.isPresent()) {
			return "changeEmpPWD.jsp";
		}
		request.setAttribute("form", form);
		List<String> errors = new ArrayList<String>();
		errors.addAll(form.getValidationErrors());
		String oldPWD = form.getOldPWD();
		String cfmPWD = form.getCfmPWD();
		
		if(!employee.getPassword().equals(oldPWD)) {
			errors.add("Old password is not correct");
		}
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "changeEmpPWD.jsp";
		}
		
		employee.setPassword(cfmPWD);
		boolean check = false;
		try {
			employeeDAO.update(employee);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("check", check);
			return "changeEmpPWD.jsp";
		}
		check = true;
		request.setAttribute("check", check);
		return "changeEmpPWD.jsp";
	}
}
