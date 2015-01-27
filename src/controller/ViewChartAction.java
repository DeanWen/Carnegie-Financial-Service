package controller;

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
import form.IdForm;

public class ViewChartAction extends Action {

	private FormBeanFactory<IdForm> idFormFactory = FormBeanFactory
			.getInstance(IdForm.class);
	private FundDAO fundDAO;
	private Fund_Price_History_DAO historyDAO;
	
	public ViewChartAction(Model model) {
		fundDAO = model.getFundDAO();
		historyDAO = model.getFundPriceHistoryDAO();
	}
	
	public String getName() {
		return "fundChart.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		IdForm form = null;
		try {
			form = idFormFactory.create(request);
		} catch (FormBeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int fundID = form.getIdAsInt();
		
		ArrayList<Fund_Price_History_Bean> beans = new ArrayList<Fund_Price_History_Bean>();
		try {
			beans = historyDAO.read(fundID);
		} catch (MyDAOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FundBean fund = null;
		try {
			fund = fundDAO.read(fundID);
		} catch (MyDAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fundName = fund.getName();

		request.setAttribute("fundID", fundID);
		request.setAttribute("fundName", fundName);
		request.setAttribute("beans",beans);
		
		return "fundChart.jsp";
	}
}
