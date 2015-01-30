/**
 * Xiaodong zHOU CMU
 * Jan 27, 2015
 */
package controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Date;
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
	private final BigDecimal MAX = new BigDecimal(9999999999.99);

	public TransitionAction(Model model) {
		fundPriceHistoryDAO = model.getFundPriceHistoryDAO();
		transactionDAO = model.getTransactionDAO();
		fundDAO = model.getFundDAO();
		customerDAO = model.getCustomerDAO();
		positionDAO = model.getPositionDAO();
	}

	@Override
	public String getName() { return "transition.do"; }
	

//----------------------Transition Function------Dian Wen--1/28/2015-----------------------------------------
	public void process (HashMap<Integer, BigDecimal> currentPrices, java.sql.Date executeDate) {
		try {
			ArrayList<TransactionBean> allPending = transactionDAO.getAllPending();
			
			for (TransactionBean tran : allPending) {

				CustomerBean customer = customerDAO.read(tran.getCustomer_id());
				PositionBean cusPosition = positionDAO.read(tran.getFund_id(), tran.getCustomer_id());
				
				//current total must > withdraw amount
				//current total + deposit must < big decimal limit
				//shares * prices < big decimal limit
				//deposit < big decimal limit
				
				if (tran.getTransaction_type().equalsIgnoreCase("Withdraw")) {
					//update total
					customer.setTotal(customer.getTotal().subtract(tran.getAmount()));
				}
				else if (tran.getTransaction_type().equalsIgnoreCase("Deposit")) {
					//update total
					customer.setTotal(customer.getTotal().add(tran.getAmount()));
				}
				else if (tran.getTransaction_type().equalsIgnoreCase("Sell")) {
					//1. minus shares
					//2. calculate income
					//3. add to total
					
					//current holding shares must > sell shares
					if (cusPosition.getShares().subtract(tran.getShares()).compareTo(BigDecimal.ZERO) == -1) {
						System.out.println("total money exceed limit, transaction failed");
						tran.setStatus(-1);
					}else {
						cusPosition.setShares(cusPosition.getShares().subtract(tran.getShares()));						
						BigDecimal afterSell = currentPrices.get(tran.getFund_id()).multiply(tran.getShares());						
						if (MAX.compareTo(afterSell) == 1 && 
								MAX.compareTo(customer.getTotal().add(afterSell)) == 1) {
							customer.setTotal(customer.getTotal().add(afterSell));
							positionDAO.update(cusPosition);
						}else {
							System.out.println("total money exceed limit, transaction failed");
							tran.setStatus(-1);
						}
					}
				}
				else if (tran.getTransaction_type().equalsIgnoreCase("Buy")) {
					//1. calculate how much shares can buy
					//2. add shares
					//3. minus money from total
					BigDecimal tmp = tran.getAmount().divide(currentPrices.get(tran.getFund_id()));
					if (cusPosition == null) {
						cusPosition = new PositionBean();
						cusPosition.setCustomer_id(tran.getCustomer_id());
						cusPosition.setFund_id(tran.getFund_id());
						cusPosition.setShares(tmp);
						customer.setTotal(customer.getTotal().subtract(tran.getAmount()));
						positionDAO.create(cusPosition);
					}else {
						BigDecimal newShares = tmp.add(cusPosition.getShares());
						if (MAX.compareTo(newShares) == 1 && 
								customer.getTotal().subtract(tran.getAmount()).compareTo(BigDecimal.ZERO) == 1) {
							cusPosition.setShares(newShares);
							customer.setTotal(customer.getTotal().subtract(tran.getAmount()));
							positionDAO.update(cusPosition);
						}else {
							System.out.println("total shares exceed limit, transaction failed");
							tran.setStatus(-1);
						}
					}
				}
				
				//update available cash to total
				customer.setCash(customer.getTotal());
				
				//update transaction status and execute date to complete
				if (tran.getStatus() == 0) {
					tran.setStatus(1);
				}
				tran.setExecute_date(executeDate);
				
				//update database;
				customerDAO.update(customer);
				transactionDAO.update(tran);		
			}
		} catch (MyDAOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
	}
	
    public boolean checkInputValidator (String[] prices) {
   	 for (String item : prices) {
				try {
					BigDecimal price = new BigDecimal(item);
					System.out.println(price);
				} catch(Exception e) {
					return false;
				}
   	 }
   	 return true;
    }
    
//------------------------------------------------------------------------------------------------------

     
    @Override
	public String perform(HttpServletRequest request) {
    	
    	Boolean check = false;
    	request.setAttribute("check", check);
        HttpSession session = request.getSession();
        List<String> errors = new ArrayList<String>();
        TransitionForm form = null;
        if (session.getAttribute("employee") == null) {
			return "login.jsp";
		}
        try {
	    	form = formBeanFactory.create(request);
	        request.setAttribute("form",form);
	        //If not logged in, return to homepage
			
			

	        
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
			
			Date preDate = null;
			try {
				preDate = fundPriceHistoryDAO.readLastDate();
				request.setAttribute("preDate", preDate); 
			} catch (MyDAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			request.setAttribute("fundList", fundList); 
			String[] prices = request.getParameterValues("price");
			
			if(prices == null) {
				return "transition.jsp";
			}
			
			
			String d = form.getDate();
			Date date = java.sql.Date.valueOf(d);

			if(date.equals(preDate) || preDate.after(date)) {
				errors.add("Date entered this time must be after that you entered last time");
				request.setAttribute("errors", errors);
				return "transition.jsp";
			}
			
			if(!checkInputValidator(prices)) {
				errors.add("Some price numbers are invalid");
				request.setAttribute("errors", errors);
				return "transition.jsp";
			}
			
			HashMap<Integer, BigDecimal> currPrice = new HashMap<Integer, BigDecimal>();
			
				for(int i = 0; i < fundList.size(); i++) {
					int id = fundList.get(i).getFund_id();
					BigDecimal price = null;
					try {
						price = new BigDecimal(prices[i]);
					} catch(Exception e) {
						errors.add("Some price numbers are invalid");
						request.setAttribute("errors", errors);
						return "transition.jsp";
					}
					
					System.out.println(funds.get(i).getName());
					
					Fund_Price_History_Bean fphb = new Fund_Price_History_Bean();
					fphb.setFund_id(id);
					fphb.setPrice(price);
					fphb.setPrice_date(date);
					
					
					
					try {
						try {
							fundPriceHistoryDAO.create(fphb);
							currPrice.put(id, price);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (MyDAOException e) {
						// TODO Auto-generated catch block
						errors.add("Update failed");
					}
				}
		
				process(currPrice, date);

				
			if(!form.isPresent()) {
	        	return "transition.jsp";
	        }
			errors = form.getValidationErrors();
	        if (errors.size() > 0) {
	        	request.setAttribute("errors", errors);
	        	return "transition.jsp";
	        }
	        check = true;
	        request.setAttribute("check", check);
	        if(check) {
	        	return "transition.do";
	        }else {
	        	return "transition.jsp";
	        }
        } catch (FormBeanException e) {
        	errors.add(e.getMessage());
        	return "transition.jsp";
        }
    }
}
