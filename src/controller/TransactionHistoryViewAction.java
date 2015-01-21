package controller;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import databean.HistoryBean;
import databean.PositionBean;
import databean.RecordBean;
import databean.TransactionBean;
import form.IdForm;
public class TransactionHistoryViewAction extends Action{
	private TransactionDAO transactionDAO;
	private PositionDAO positionDAO;
	private FundDAO fundDAO;
	private Fund_Price_History_DAO fundPriceHistoryDAO;
	private FormBeanFactory<IdForm> idFormFactory = FormBeanFactory
			.getInstance(IdForm.class);
	
	public TransactionHistoryViewAction(Model model) {
		transactionDAO = model.getTransactionDAO();
		positionDAO = model.getPositionDAO();
		fundDAO = model.getFundDAO();
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() {
		return "transactionHistoryView.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		IdForm form = null;
		try {
			form = idFormFactory.create(request);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int fundID = form.getIdAsInt();
		int customerID = customer.getCustomer_id();
		FundBean curFund = null;
		try {
			curFund = fundDAO.read(fundID);
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		request.setAttribute("curFund", curFund);
		ArrayList<TransactionBean> transactions = new ArrayList<TransactionBean>();
		try {
			transactions = transactionDAO.getTransactions(customerID, fundID);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("transactions", transactions);
		
		ArrayList<RecordBean> records = new ArrayList<RecordBean>();
		ArrayList<PositionBean> positions = new ArrayList<PositionBean>();
		try {
			positions = positionDAO.getPositions(customer.getCustomer_id());
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 0; i < positions.size(); i++) {
			RecordBean item = new RecordBean();
			int fundid = positions.get(i).getFund_id();
			
			FundBean fund = null;
			Fund_Price_History_Bean history = null;
			try {
				fund = fundDAO.read(fundid);
				history = fundPriceHistoryDAO.readLast(fundid);
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(fund == null || history == null) {
				continue;
			}
			item.setFundID(fund.getFund_id());
			item.setShares(positions.get(i).getShares());
			item.setFundName(fund.getName());
			item.setFundSymbol(fund.getSymbol());
			item.setPrice(history.getPrice());
			item.setValue(history.getPrice().multiply(positions.get(i).getShares()));
			records.add(item);
		}
		
		request.setAttribute("records", records);
		
		ArrayList<HistoryBean> histories = new ArrayList<HistoryBean>();
		for(int i = 0; i < transactions.size(); i++) {
			HistoryBean cur = new HistoryBean();
			Fund_Price_History_Bean fph = null;
			try {
				fph = fundPriceHistoryDAO.read(fundID, (Date) transactions.get(i).getExecute_date());
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cur.setFundName(curFund.getName());
			cur.setDate(transactions.get(i).getExecute_date());
			cur.setAmount(transactions.get(i).getAmount());
			cur.setShares(transactions.get(i).getShares());
			cur.setType(transactions.get(i).getTransaction_type());
			if(fph != null) {
				cur.setPrice(fph.getPrice());
			}
			histories.add(cur);
		}
		
		request.setAttribute("histories", histories);
		return "transactionHistoryView.jsp";
	}
}
