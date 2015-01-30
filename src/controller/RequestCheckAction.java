package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.TransactionBean;
import form.RequestCheckForm;
import model.CustomerDAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;

public class RequestCheckAction extends Action {
	private FormBeanFactory<RequestCheckForm> formBeanFactory = FormBeanFactory
			.getInstance(RequestCheckForm.class);

	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;

	public RequestCheckAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}

	public String getName() {
		return "requestCheck.do";
	}

	public String perform(HttpServletRequest request) {
		boolean check = false;
		
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}
		List<String> errors = new ArrayList<String>();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customer");
					
		RequestCheckForm form = null;
		try {
			form = formBeanFactory.create(request);
			request.setAttribute("form", form);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		if (!form.isPresent()) {
			request.setAttribute("errors", errors);
			return "requestCheck.jsp";
		}
			
		errors.addAll(form.getValidationErrors());

		if (errors.size() != 0) {
			request.setAttribute("errors", errors);
			return "requestCheck.jsp";
		}

		BigDecimal amount = new BigDecimal(form.getRequestAmount());

		synchronized (customerDAO) {
			BigDecimal cash = customerBean.getCash();
			if (amount.compareTo(cash) == 1) {
				errors.add("No enough Money.");
				session.setAttribute("errors", errors);
				return "requestCheck.jsp";
			}
			customerBean.setCash(cash.subtract(amount));
			try {
				customerDAO.update(customerBean);
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errors", errors);
				return "requestCheck.jsp";
			}
		}

		
			TransactionBean transactionBean = new TransactionBean();
			transactionBean.setAmount(amount);
			transactionBean.setCustomer_id(customerBean.getCustomer_id());
			transactionBean.setTransaction_type("Withdraw");
			transactionBean.setStatus(0);

			
			try {
				transactionDAO.create(transactionBean);
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errors", errors);
				return "requestCheck.jsp";
			}
		
			check = true;
			request.setAttribute("check", check);
			return "requestCheck.jsp";
				
	}

}
