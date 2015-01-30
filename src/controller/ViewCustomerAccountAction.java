/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import model.FundDAO;
import model.Fund_Price_History_DAO;
import model.Model;
import model.MyDAOException;
import model.TransactionDAO;
import databean.CustomerBean;
import databean.FundBean;
import databean.Fund_Price_History_Bean;
import databean.HistoryBean;
import databean.TransactionBean;
import form.ViewCustomerAccountForm;

public class ViewCustomerAccountAction extends Action{
	private FormBeanFactory<ViewCustomerAccountForm> formBeanFactory = FormBeanFactory
			.getInstance(ViewCustomerAccountForm.class);
	
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private Fund_Price_History_DAO fundPriceHistoryDAO;
	
	public ViewCustomerAccountAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() {
		return "viewCustomerAccount.do";
	}
	
	public String perform(HttpServletRequest request) {
		ViewCustomerAccountForm form = null;
		HttpSession session = request.getSession(true);
		if (session.getAttribute("employee") == null) {
			return "login.jsp";
		}
		try{
			form = formBeanFactory.create(request);
		} catch (FormBeanException e1) {
			e1.printStackTrace();
		}
		request.setAttribute("form", form);
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");		

		System.out.println(session);

		
		int customerID = customer.getCustomer_id();
		System.out.println("Customer ID" + customerID);
		ArrayList<TransactionBean> transactions = new ArrayList<TransactionBean>();
		try {
			transactions = transactionDAO.getCompleteTransactions(customerID);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("transactions", transactions);
		
		ArrayList<TransactionBean> pendings = new ArrayList<TransactionBean>();
		try {
			pendings = transactionDAO.getPendingTransactions(customerID);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<HistoryBean> histories = new ArrayList<HistoryBean>();
		ArrayList<HistoryBean> pendingHistory = new ArrayList<HistoryBean>();
		System.out.println("size: " + transactions.size());
		
		for(int i = 0; i < pendings.size(); i++) {
			HistoryBean cur = new HistoryBean();
			Fund_Price_History_Bean fph = null;
			int fundID = pendings.get(i).getFund_id();
			System.out.println("fund id: " + fundID);
			try {
				fph = fundPriceHistoryDAO.read(fundID, pendings.get(i).getExecute_date());
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int tempFundID = pendings.get(i).getFund_id();
			FundBean tempFund = null;
			if(tempFundID > 0) {
				try {
					tempFund = fundDAO.read(pendings.get(i).getFund_id());
				} catch (MyDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(tempFund != null) {
				cur.setFundName(tempFund.getName());
			}
			
			cur.setDate(pendings.get(i).getExecute_date());
			cur.setAmount(pendings.get(i).getAmount());
			cur.setShares(pendings.get(i).getShares());
			cur.setStatus(pendings.get(i).getStatus());
			
			cur.setType(pendings.get(i).getTransaction_type());
			
			if(fph != null) {
				cur.setPrice(fph.getPrice());
				System.out.println("Price: " + fph.getPrice());
			}
			histories.add(cur);
		}
		
		for(int i = 0; i < transactions.size(); i++) {
			HistoryBean cur = new HistoryBean();
			Fund_Price_History_Bean fph = null;
			int fundID = transactions.get(i).getFund_id();
			System.out.println("fund id: " + fundID);
			try {
				fph = fundPriceHistoryDAO.read(fundID, (Date) transactions.get(i).getExecute_date());
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			int tempFundID = transactions.get(i).getFund_id();
			FundBean tempFund = null;
			if(tempFundID > 0) {
				try {
					tempFund = fundDAO.read(transactions.get(i).getFund_id());
				} catch (MyDAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(tempFund != null) {
				cur.setFundName(tempFund.getName());
			}
			
			cur.setDate(transactions.get(i).getExecute_date());
			cur.setAmount(transactions.get(i).getAmount());
			cur.setShares(transactions.get(i).getShares());
			cur.setStatus(transactions.get(i).getStatus());
			
			cur.setType(transactions.get(i).getTransaction_type());
			
			if(fph != null) {
				cur.setPrice(fph.getPrice());
				System.out.println("Price: " + fph.getPrice());
			}
			
			histories.add(cur);
		}
		request.setAttribute("histories", histories);
		request.setAttribute("pendingHistory", pendingHistory);
		
//		if (form.getAction().equals("Edit")) {
//			session.setAttribute("customer", customer);
//			return "editCustomerAccount.do";
//		}

		return "viewCustomerAccount.jsp";
	}
}
