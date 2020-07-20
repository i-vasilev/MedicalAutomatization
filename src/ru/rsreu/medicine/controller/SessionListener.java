package ru.rsreu.medicine.controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 * Session listener
 * 
 * @author Admin
 *
 */
public class SessionListener implements javax.servlet.http.HttpSessionListener {
	private final static String LOGIN = "user";

	public SessionListener() {
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	/**
	 * Executes, when session destroys
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		if (session.getAttribute(LOGIN) != null) {
			try {
				//String nick = session.getAttribute(LOGIN) == null ? "" : session.getAttribute(LOGIN).toString();
				session.setAttribute(LOGIN, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
