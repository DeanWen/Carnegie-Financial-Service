/**
/*
 *  Team 14 Infinity
 *  Task 7
 *  CMU - eBiz
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
import form.DepositForm;

public class DepositAction extends Action{
	private FormBeanFactory<DepositForm> formBeanFactory = FormBeanFactory.getInstance(DepositForm.class);
	
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;

	public DepositAction(Model model) {
		customerDAO = model.getCustomerDAO();
		transactionDAO = model.getTransactionDAO();
	}

	public String getName() { return "deposit.do"; }
    
    public String perform(HttpServletRequest request) {
    	HttpSession session = request.getSession();
		if (session.getAttribute("employee") == null) {
			return "login.jsp";
		}        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	DepositForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
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
	        BigDecimal newCash = new BigDecimal(0);
	        try {
		        customer = customerDAO.read(Integer.parseInt(form.getUserid()));
		        
		        if (customer == null) {
		            errors.add("User Name not found");
		            return "deposit.jsp";
		        }	        	
		        
		        // Update cash
		        BigDecimal currentCash = customerDAO.read(Integer.parseInt(form.getUserid())).getCash();
		        BigDecimal addAmount = new BigDecimal(form.getDepositAmount());
		        if (currentCash.add(addAmount).compareTo(new BigDecimal("9999999999")) == 1) {
		        	errors.add("Please enter a smaller amount");
					request.setAttribute("errors", errors);
					return "deposit.jsp";	
		        } else {
		        	newCash = currentCash.add(addAmount);
		        }
		        	
		        customer.setCash(newCash);
		        customerDAO.update(customer);
		        		        
				TransactionBean transactionBean = new TransactionBean();
				transactionBean.setAmount(newCash);
				transactionBean.setCustomer_id(Integer.parseInt(form.getUserid()));
				transactionBean.setFund_id(0);
				transactionBean.setTransaction_type("Deposit");
				transactionBean.setStatus(0);
		        
				try {
					transactionDAO.create(transactionBean);
				} catch (MyDAOException e) {
					e.printStackTrace();
					errors.add("Add transaction failed");
					request.setAttribute("errors", errors);
					return "deposit.jsp";
				}
		        
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
