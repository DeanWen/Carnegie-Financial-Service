package controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databean.CustomerBean;
import databean.FundBean;
import databean.Fund_Price_History_Bean;
import databean.PositionBean;
import databean.ResearchBean;
import model.FundDAO;
import model.Fund_Price_History_DAO;
import model.Model;
import model.MyDAOException;
import model.PositionDAO;

public class ResearchFundAction extends Action {
	Fund_Price_History_DAO fundPriceHistoryDAO;
	FundDAO fundDAO;
	PositionDAO positionDAO;
	
	public ResearchFundAction(Model model) {
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		fundDAO = model.getFundDAO();
		positionDAO = model.getPositionDAO();
	}
	
	public String getName() {
		return "researchFund.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("customer") == null) {
			return "login.jsp";
		}		
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		
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
			PositionBean position = null;
			
			try {
				fund = fundDAO.read(fundID);
				history = fundPriceHistoryDAO.readLast(fundID);
				position = positionDAO.read(fundID, customer.getCustomer_id());
			} catch (MyDAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(fund == null || fund.getFund_id() == 0) {
				continue;
			}

			item.setFund_id(fund.getFund_id());
			item.setName(fund.getName());
			item.setSymbol(fund.getSymbol());
			if (history == null) {
				item.setPrice(null);
			}
			else {
				item.setPrice(history.getPrice());
			}
			if (position == null) {
				item.setShare(new BigDecimal(0).setScale(3));
			}else {
				item.setShare(position.getShares());
			}
			fundList.add(item);
		}
		
		request.setAttribute("fundList", fundList);
		
		return "researchFund.jsp";
	}
}
	


