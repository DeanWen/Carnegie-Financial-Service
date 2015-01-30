package controller;

import java.math.BigDecimal;
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
	static int id = 0;
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
	
	@Override
	public String getName() {
		return "sellFund.do";
	}
	
	@Override
	public String perform(HttpServletRequest request) {
		boolean check = false;
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		List<String> errors = new ArrayList<String>();
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
		
		if(form.getId() != null) {
			id = form.getIdAsInt();
		}
		int fundID = id;
		System.out.println("ID: " +fundID);
				
		FundBean curFund = null;
		try {
			curFund = fundDAO.read(fundID);
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("curFund", curFund);
		PositionBean position = null;
		if(curFund != null) {
			try {
				position = positionDAO.read(curFund.getFund_id(), customer.getCustomer_id());
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errors", errors);
				return "sellFund.jsp";
			}
		
			request.setAttribute("position", position);
		}
		if(position == null) {
			return "sellFundError.jsp";
		}
		errors.addAll(form.getValidationErrors());
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		System.out.println(1);
		
		BigDecimal sellAmount = new BigDecimal(form.getCfmAmount());
		
		
		BigDecimal ownAmount = position.getShares();
		if(sellAmount.compareTo(ownAmount) == 1) {
			errors.add("You don't have enough shares to sell");
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		if(sellAmount.compareTo(BigDecimal.ZERO) == 0 || sellAmount.compareTo(BigDecimal.ZERO) == -1) {
			errors.add("You cannot sell " + sellAmount + " shares");
			request.setAttribute("errors", errors);
			return "sellFund.jsp";
		}
		
		BigDecimal newAmount = ownAmount.subtract(sellAmount);
		
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
		newTran.setTransaction_type("Sell");
		newTran.setStatus(0);
		
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
