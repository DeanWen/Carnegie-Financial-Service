package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Model;

public class LogoutAction extends Action {
	public LogoutAction(Model model) {
	}
	
	public String getName() {
		return "logout.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("customer", null);
		session.setAttribute("employee", null);
		session.invalidate();
		return "login.do";
	}
}
