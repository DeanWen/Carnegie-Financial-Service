package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.PositionBean;
import databean.TransactionBean;
import form.BuyForm;
import form.SellForm;

public class BuyFundAction extends Action{
	static int id = 0;
	private FundDAO fundDAO;
	private TransactionDAO transactionDAO;
	private CustomerDAO customerDAO;
	private FormBeanFactory<BuyForm> buyFormFactory = FormBeanFactory
			.getInstance(BuyForm.class);
	
	public BuyFundAction(Model model) {
		fundDAO = model.getFundDAO();
		transactionDAO = model.getTransactionDAO();
		customerDAO = model.getCustomerDAO();
	}
	
	public String getName() {
		return "buyFund.do";
	}
	
	public String perform(HttpServletRequest request) {
		boolean check = false;
		HttpSession session = request.getSession();		
		
		CustomerBean oldCustomer = (CustomerBean) session.getAttribute("customer");
		int customer_id = oldCustomer.getCustomer_id();
		CustomerBean customerBean = null;
		try {
			customerBean = customerDAO.read(customer_id);
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BuyForm form = null;		
		try {
			form = buyFormFactory.create(request);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!form.isPresent()) {
			return "buyFund.jsp";
		}
		
		request.setAttribute("form", form);
		
		if(form.getId() != null) {
			id = form.getIdAsInt();
		}
		
		FundBean curFund = null;
		
		try {
			curFund = fundDAO.read(id);
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("curFund",curFund);
		request.setAttribute("customerBean", customerBean);
		
		List<String> errors = new ArrayList<String>();
		errors.addAll(form.getValidationErrors());

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "buyFund.jsp";
		}
		
		
		BigDecimal buyAmount = new BigDecimal(form.getBuyAmount());		
		BigDecimal cash = customerBean.getCash();
		
		if(buyAmount.compareTo(cash) == 1) {
			errors.add("You don't have enough money to buy");
			request.setAttribute("errors", errors);
			return "buyFund.jsp";
		}
		if(buyAmount.compareTo(BigDecimal.ZERO) == 0 || buyAmount.compareTo(BigDecimal.ZERO) == -1) {
			errors.add("You cannot buy negative shares");
			request.setAttribute("errors", errors);
			return "buyFund.jsp";
		}
		
		BigDecimal newCash = cash.subtract(buyAmount);
		customerBean.setCash(newCash);
		try {
			customerDAO.update(customerBean);
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			request.setAttribute("errors", errors);
			return "buyFund.jsp";
		}
		
		TransactionBean newTran = new TransactionBean();
		newTran.setAmount(buyAmount);
		newTran.setCustomer_id(customerBean.getCustomer_id());
		newTran.setFund_id(curFund.getFund_id());
		newTran.setTransaction_type("Buy");
		newTran.setStatus(false);
		
		try {
			transactionDAO.create(newTran);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add("Update database failed)");
			request.setAttribute("errors", errors);
			return "buyFund.jsp";
		}
		session.setAttribute("customerBean", customerBean);
		
		check = true;
		request.setAttribute("check", check);
		return "buyFund.jsp";
	}
}
