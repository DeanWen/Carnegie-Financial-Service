package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import form.TransitionForm;

public class TransitionAction extends Action{
	private FormBeanFactory<TransitionForm> formBeanFactory = FormBeanFactory.getInstance(TransitionForm.class);
	
	private FundDAO fundDAO;

	public TransitionAction(Model model) {
		fundDAO = model.getFundDAO();
	}

	public String getName() { return "transition.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
	    	TransitionForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
//	        // If not logged in, return to homepage
//			if (session.getAttribute("employee") != null) {
//				return "login.jsp";
//			}
	        
	        // If no params were passed, return with no errors so that the form will be
	        // presented (we assume for the first time).
	        if (!form.isPresent()) {
	            return "transition.jsp";
	        }

	        // Any validation errors?
	        errors.addAll(form.getValidationErrors());
	        if (errors.size() != 0) {
	            return "transition.jsp";
	        }
	        
//	        // Look up the user and update cash
//	        CustomerBean customer = null;
//	        try {
//		        customer = fundDAO.read(Integer.parseInt(form.getUserid()));
//		        
//		        if (customer == null) {
//		            errors.add("User Name not found");
//		            return "transition.jsp";
//		        }	        	
//		        
//		        // Update cash
//		        int currentCash = customerDAO.read(Integer.parseInt(form.getUserid())).getCash().intValue();
//		        int addAmount = Integer.parseInt(form.getDepositAmount());
//		        int newCash = currentCash + addAmount;
//		        // customerDAO.read(Integer.parseInt(form.getUserid())).setCash(new BigDecimal(newCash));
//		        customer.setCash(new BigDecimal(newCash));
//		        customerDAO.update(customer);
//		        request.setAttribute("message", "Transition Day Added successfully!");
//		        return "success.jsp";
//		        
//			} catch (MyDAOException e1) {
//				e1.printStackTrace();
//			}
	        
			return "transition.do";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "transition.jsp";
        }
    }
}
