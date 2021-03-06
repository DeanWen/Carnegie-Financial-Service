/**
/*
 *  Team 14 Infinity
 *  Task 7
 *  CMU - eBiz
 * Tian Zheng CMU
 * Jan 27, 2015
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import model.FundDAO;
import model.Model;
import model.MyDAOException;

import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;

import databean.FundBean;
import form.CreateFundForm;

public class CreateFundAction extends Action {
	private FormBeanFactory<CreateFundForm> formBeanFactory = FormBeanFactory
			.getInstance(CreateFundForm.class);

	private FundDAO fundDAO;
	public CreateFundAction(Model model) {
		model.getFundDAO();
		fundDAO = model.getFundDAO();
	}

	public String getName() {
		return "createFund.do";
	}

	public String perform(HttpServletRequest request) {

		List<String> errors = new ArrayList<String>();
		request.setAttribute("errors", errors);
		HttpSession session = request.getSession();
		if (session.getAttribute("employee") == null) {
			return "login.jsp";
		}
		
		try {	
			CreateFundForm form = formBeanFactory.create(request);
			request.setAttribute("form", form);

			// If no params were passed, return with no errors so that the form
			// will be
			// presented (we assume for the first time).
			
			if (!form.isPresent()) {
				return "createFund.jsp";
			}

			// Any validation errors?
			errors.addAll(form.getValidationErrors());
			if (errors.size() != 0) {
				return "createFund.jsp";
			}
			
			FundBean fund = new FundBean();
			if(fundDAO.readByName(form.getFundName()) != null) {
				errors.add("Fund Exists");
				return "createFund.jsp";
			} else {
				fund.setName(form.getFundName());
				fund.setSymbol(form.getFundTicker());
				fundDAO.create(fund);
				request.setAttribute("message", "Fund Created Successfully!");
				return "success.jsp";
			}
		} catch (FormBeanException | MyDAOException e) {
			errors.add(((Throwable) e).getMessage());
			return "createFund.jsp";
		}
	}
}
