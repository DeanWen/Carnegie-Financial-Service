/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.CustomerDAO;
import model.FundDAO;
import model.Fund_Price_History_DAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;
import model.TransactionDAO;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.Fund_Price_History_Bean;
import databean.PositionBean;
import databean.ResearchBean;
import databean.TransactionBean;
import form.TransitionForm;

public class TransitionAction extends Action{
	private FormBeanFactory<TransitionForm> formBeanFactory = FormBeanFactory.getInstance(TransitionForm.class);
	private Fund_Price_History_DAO fundPriceHistoryDAO;
	private FundDAO fundDAO;
	private CustomerDAO customerDAO;
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;

	public TransitionAction(Model model) {
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
	}

	public String getName() { return "transition.do"; }
	

//----------------------Transition Function------Dian Wen--1/28/2015-----------------------------------------
	public void process (HashMap<Integer, BigDecimal> currentPrices, Date executeDate) {
		try {
			ArrayList<TransactionBean> allPending = transactionDAO.getAllPending();
			
			for (TransactionBean tran : allPending) {

				CustomerBean customer = customerDAO.read(tran.getCustomer_id());
				PositionBean cusPosition = positionDAO.read(tran.getFund_id(), tran.getCustomer_id());
				
				//Here assume all number are correct like
				//current total must > withdraw amount
				//current total + deposit must < big decimal limit
				//current holding shares must > sell amount
				//shares * prices < big decimal limit
				//deposit < big decimal limit
				
				if (tran.getTransaction_type().equalsIgnoreCase("Withdraw")) {
					//update total
					customer.setTotal(customer.getTotal().subtract(tran.getAmount()));
				}
				else if (tran.getTransaction_type().equalsIgnoreCase("Deposit")) {
					//update total
					customer.setTotal(customer.getCash().add(tran.getAmount()));
				}
				else if (tran.getTransaction_type().equalsIgnoreCase("Sell")) {
					//1. minus shares
					//2. calculate income
					//3. update total
					cusPosition.setShares(cusPosition.getShares().subtract(tran.getAmount()));
					BigDecimal afterSell = currentPrices.get(tran.getFund_id()).multiply(cusPosition.getShares());
					customer.setTotal(customer.getTotal().add(afterSell));
				}
				else if (tran.getTransaction_type().equalsIgnoreCase("Buy")) {
					//1. add shares
					//2. calculate cost
					//3. update total
					cusPosition.setShares(cusPosition.getShares().add(tran.getAmount()));
					BigDecimal afterBuy = currentPrices.get(tran.getFund_id()).multiply(cusPosition.getShares());
					customer.setTotal(customer.getTotal().subtract(afterBuy));
				}
				
				//update available cash to total
				customer.setCash(customer.getTotal());
				
				//update transaction status and execute date to complete
				tran.setStatus(true);
				tran.setExecute_date(executeDate);
				
				//update database;
				customerDAO.update(customer);
				positionDAO.update(cusPosition);
				transactionDAO.update(tran);		
			}
		} catch (MyDAOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}
//------------------------------------------------------------------------------------------------------
     
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> errors = new ArrayList<String>();
        try {
	    	TransitionForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
//	        // If not logged in, return to homepage
	        if (session.getAttribute("employee") == null) {
				return "login.jsp";
			}
	        
//	        // If no params were passed, return with no errors so that the form will be
//	        // presented (we assume for the first time).
//	        if (!form.isPresent()) {
//	            return "transition.jsp";
//	        }
//
//	        // Any validation errors?
//	        errors.addAll(form.getValidationErrors());
//	        if (errors.size() != 0) {
//	            return "transition.jsp";
//	        }
	        
	        
			ArrayList<ResearchBean> fundList = new ArrayList<ResearchBean>();
			 
			ArrayList<FundBean> funds = new ArrayList<FundBean>();
			try {
				funds = fundDAO.readAll();
			} catch (MyDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			for(int i = 0; i < funds.size(); i++) {
				ResearchBean item = new ResearchBean();
				int fundID = funds.get(i).getFund_id();
				
				FundBean fund = null;
				Fund_Price_History_Bean history = null;
				
				try {
					fund = fundDAO.read(fundID);
					history = fundPriceHistoryDAO.readLast(fundID);
				} catch (MyDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(fund == null || fundID == 0) {
					continue;
				}

				item.setFund_id(fund.getFund_id());
				item.setName(fund.getName());
				item.setSymbol(fund.getSymbol());
				if (history != null) {
					item.setPrice(history.getPrice());	
				} else {
					item.setPrice(null);
				}
				
				item.setShare(new BigDecimal(0));
				fundList.add(item);
			}
			
			request.setAttribute("fundList", fundList);
			if(!form.isPresent()) {
	        	return "transition.jsp";
	        }
			errors = form.getValidationErrors();
	        if (errors.size() > 0) {
	        	request.setAttribute("errors", errors);
	        	return "transition.jsp";
	        }
			return "transition.jsp";
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "transition.jsp";
        }
    }
}
