package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import form.SellForm;

public class SellFundAction extends Action{
	FundDAO fundDAO;
	PositionDAO positionDAO;
	TransactionDAO transactionDAO;
	private FormBeanFactory<SellForm> sellFormFactory = FormBeanFactory
			.getInstance(SellForm.class);
	
	public SellFundAction(Model model) {
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
		transactionDAO = model.getTransactionDAO();
	}
	
	public String getName() {
		return "sellFund.do";
	}
	
	public String perform(HttpServletRequest request) {
		boolean check = false;
		HttpSession session = request.getSession();
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");

		
		
		SellForm form = null;
		try {
			form = sellFormFactory.create(request);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!form.isPresent()) {
			return "sellFund.jsp";
		}
		request.setAttribute("form", form);
		
		List<String> errors = new ArrayList<String>();
		errors.addAll(form.getValidationErrors());

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		
		FundBean curFund = null;
		try {
			curFund = fundDAO.readByName(form.getFundName());
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(curFund == null) {
			errors.add("You don't have this fund");
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		
		float sellAmount = Float.parseFloat(form.getCfmAmount());
		
		PositionBean position;
		try {
			position = positionDAO.read(curFund.getFund_id(), customer.getCustomer_id());
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		Float ownAmount = Float.parseFloat(position.getShares());
		if(sellAmount > ownAmount) {
			errors.add("You don't have enough shares to sell");
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		
		Float newAmount = ownAmount - sellAmount;
		
		position.setShares(newAmount);
		try {
			positionDAO.update(position);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add("Update database failed");
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		TransactionBean newTran = new TransactionBean();
		newTran.setShares(sellAmount);
		newTran.setCustomer_id(customer.getCustomer_id());
		newTran.setFund_id(curFund.getFund_id());
		newTran.setTransaction_type("Pending");
		try {
			transactionDAO.create(newTran);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errors.add("Update database failed)");
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}	
		
		check = true;
		request.setAttribute("check", check);
		return "sellFund.jsp";
	}
}
