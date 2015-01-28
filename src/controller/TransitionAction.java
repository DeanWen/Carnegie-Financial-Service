/**
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.FundDAO;
import model.Fund_Price_History_DAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.CustomerBean;
import databean.FundBean;
import databean.Fund_Price_History_Bean;
import databean.PositionBean;
import databean.ResearchBean;
import form.TransitionForm;

public class TransitionAction extends Action{
	private FormBeanFactory<TransitionForm> formBeanFactory = FormBeanFactory.getInstance(TransitionForm.class);
	Fund_Price_History_DAO fundPriceHistoryDAO;
	private FundDAO fundDAO;

	public TransitionAction(Model model) {
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() { return "transition.do"; }
    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        List<String> errors = new ArrayList<String>();
        try {
	    	TransitionForm form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        
//	        // If not logged in, return to homepage
//			if (session.getAttribute("employee") != null) {
//				return "login.jsp";
//			}
	        
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
