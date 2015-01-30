package controller;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class TransactionHistoryViewAction extends Action{
	private TransactionDAO transactionDAO;
	private FundDAO fundDAO;
	private Fund_Price_History_DAO fundPriceHistoryDAO;
	
	
	public TransactionHistoryViewAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	@Override
	public String getName() {
		return "transactionHistoryView.do";
	}
	
	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		
		
		int customerID = customer.getCustomer_id();
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
				fph = fundPriceHistoryDAO.read(fundID, transactions.get(i).getExecute_date());
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

		return "transactionHistoryView.jsp";
	}
}
