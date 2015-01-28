/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package controller;

import java.math.BigDecimal;
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
import form.FindCustomerForm;

public class FindCustomerAction extends Action{
	private FormBeanFactory<FindCustomerForm> formBeanFactory = FormBeanFactory.getInstance(FindCustomerForm.class);
	
	private CustomerDAO customerDAO;

	public FindCustomerAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "findCustomer.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	FindCustomerForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
//	        // If not logged in, return to homepage
//			if (session.getAttribute("employee") != null) {
//				return "login.jsp";
//			}
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "findCustomer.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "findCustomer.jsp";
	        }
	        
	        // Look up the user and update cash
	        CustomerBean customer = null;
	        try {
		        customer = customerDAO.read(Integer.parseInt(form.getUserid()));
		        
		        if (customer == null) {
		            errors.add("User Name not found");
		            return "findCustomer.jsp";
		        }	     
		        
		        session.setAttribute("customer", customer);
		        return "viewCustomerAccount.jsp";
		        
			} catch (MyDAOException e1) {
				e1.printStackTrace();
			}
	        
			return "findCustomer.do";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "findCustomer.jsp";
        }
    }
}
