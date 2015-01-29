package controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.CustomerBean;
import databean.FundBean;
import databean.Fund_Price_History_Bean;
import databean.PositionBean;
import databean.RecordBean;
import model.FundDAO;
import model.Fund_Price_History_DAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;

public class FundListViewAction extends Action{
	Fund_Price_History_DAO fundPriceHistoryDAO;
	FundDAO fundDAO;
	PositionDAO positionDAO;
	
	public FundListViewAction(Model model) {
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
	}
	
	public String getName() {
		return "fundListView.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
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
			int fundID = positions.get(i).getFund_id();
			
			FundBean fund = null;
			Fund_Price_History_Bean history = null;
			try {
				fund = fundDAO.read(fundID);
				history = fundPriceHistoryDAO.readLast(fundID);
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
			item.setValue(history.getPrice().multiply(positions.get(i).getShares()).setScale(2, BigDecimal.ROUND_HALF_UP));
			records.add(item);
		}
		
		request.setAttribute("records", records);
		
		return "fundListView.jsp";
	}
}
