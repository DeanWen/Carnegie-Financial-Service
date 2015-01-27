package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import form.DepositForm;

public class DepositAction extends Action{
	private FormBeanFactory<DepositForm> formBeanFactory = FormBeanFactory.getInstance(DepositForm.class);
	
	private CustomerDAO customerDAO;

	public DepositAction(Model model) {
		customerDAO = model.getCustomerDAO();
	}

	public String getName() { return "deposit.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	DepositForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
//	        // If not logged in, return to homepage
//			if (session.getAttribute("employee") != null) {
//				return "login.jsp";
//			}
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "deposit.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "deposit.jsp";
	        }
	        
	        // Look up the user and update cash
	        CustomerBean customer = null;
	        try {
		        customer = customerDAO.read(Integer.parseInt(form.getUserid()));
		        
		        if (customer == null) {
		            errors.add("User Name not found");
		            return "deposit.jsp";
		        }	        	
		        
		        // Update cash
		        int currentCash = customerDAO.read(Integer.parseInt(form.getUserid())).getCash().intValue();
		        int addAmount = Integer.parseInt(form.getDepositAmount());
		        int newCash = currentCash + addAmount;
		        // customerDAO.read(Integer.parseInt(form.getUserid())).setCash(new BigDecimal(newCash));
		        customer.setCash(new BigDecimal(newCash));
		        customerDAO.update(customer);
		        request.setAttribute("message", "Deposit Successfully!");
		        return "success.jsp";
		        
			} catch (MyDAOException e1) {
				e1.printStackTrace();
			}
	        
			return "deposit.do";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "deposit.jsp";
        }
    }
}
