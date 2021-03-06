/*
/*
 *  Team 14 Infinity
 *  Task 7
 *  CMU - eBiz
 *
* Name: Dean Wen
 * Date: 1/16/2015
 */
package controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Model;
import databean.CustomerBean;
import databean.EmployeeBean;

/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() throws ServletException {
		Model model = new Model(getServletConfig());
		Action.add(new CreateEmployeeAccountAction(model));
		Action.add(new CreateCustomerAccountAction(model));
		Action.add(new LoginAction(model));
		Action.add(new FindCustomerAction(model));
		Action.add(new ViewCustomerAccountAction(model));
		Action.add(new EditCustomerAccountAction(model));
		Action.add(new CustomerAccountEditAction(model));
		Action.add(new CustomerAccountViewAction(model));
		Action.add(new FundListViewAction(model));
		Action.add(new TransactionHistoryViewAction(model));
		Action.add(new ChangePWDAction(model));
		Action.add(new SellFundAction(model));
		Action.add(new BuyFundAction(model));
		Action.add(new RequestCheckAction(model));
		Action.add(new DepositAction(model));
		Action.add(new CreateFundAction(model));
		Action.add(new TransitionAction(model));
		Action.add(new ChangeEmpPWDAction(model));
		Action.add(new ResearchFundAction(model));
		Action.add(new ViewChartAction(model));
		Action.add(new LogoutAction(model));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String nextPage = performTheAction(request);
		sendToNextPage(nextPage, request, response);
	}

	private String performTheAction(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String servletPath = request.getServletPath();
		String action = getActionName(servletPath);
		CustomerBean customer = (CustomerBean) session.getAttribute("customer");
		EmployeeBean employee = (EmployeeBean) session.getAttribute("employee");
		
		if (customer == null && employee == null) {
			// If the user hasn't logged in, direct him to the login page
			return Action.perform("login.do", request);
		}

		return Action.perform(action, request);
	}

	private void sendToNextPage(String nextPage, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextPage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND,
					request.getServletPath());
			return;
		}
		if (nextPage.endsWith(".do")) {
			response.sendRedirect(nextPage);
			return;
		}

		if (nextPage.endsWith(".jsp")) {
			RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
					+ nextPage);
			d.forward(request, response);
			return;
		}
		response.sendRedirect(nextPage);
	}

	private String getActionName(String path) {
		int slash = path.lastIndexOf('/');
		return path.substring(slash + 1);
	}
}
