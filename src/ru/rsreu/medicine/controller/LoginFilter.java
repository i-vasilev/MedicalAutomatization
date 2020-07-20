package ru.rsreu.medicine.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Login filter - forwards to login page, if user didn't login
 * @author Admin
 *
 */
public class LoginFilter implements Filter {
	private final static String EMPTY_STR = "";
	private final static String LOGIN = "user";
	private final static String COMMAND = "cmd";
	private final static String PATH_TO_LOGIN = "/WEB-INF/jsp/login.jsp";

	public LoginFilter() {
	}

	@Override
	public void destroy() {
	}

	/**
	 * Do login filter
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpServletResponse resp = (HttpServletResponse) arg1;
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		boolean isStaticResource = req.getRequestURI().endsWith("/images/")
				|| req.getRequestURI().endsWith("/style.css");
		String command = req.getParameter(COMMAND) == null ? "" : req.getParameter(COMMAND);
		HttpSession session = req.getSession(true);
		boolean isLoginCommand = req.getRequestURI().endsWith("/login");
		String sessionNick = session.getAttribute(LOGIN) == null ? "" : session.getAttribute(LOGIN).toString();
		if (sessionNick.equals(EMPTY_STR) && command.equals(EMPTY_STR) && !isStaticResource) {
			req.getRequestDispatcher(PATH_TO_LOGIN).forward(req, resp);
		} else {
			if (arg2 != null && !isLoginCommand) {
				arg2.doFilter(arg0, arg1);
			} else if (isLoginCommand) {
				resp.sendRedirect(req.getContextPath() + "/main");
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
