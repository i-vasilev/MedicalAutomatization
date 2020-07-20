package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.UserDAO;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for logining user
 * 
 * @author Admin
 *
 */
public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDAO userDAO = DAOFactory.getInstance(DBType.ORACLE).getUserDAO();
		User user = userDAO.logIn(req.getParameter("insurance"), req.getParameter("pass"));
		if (user != null) {
			req.getSession().setAttribute("user", user);
			resp.sendRedirect(req.getContextPath() + "/main");
		} else {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
	}

}
