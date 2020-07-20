package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for showing form finding user
 * @author Admin
 *
 */
public class FindUserFormCommand implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if (user.getRole().equals(Roles.USER)) {
			resp.sendRedirect(req.getContextPath() + "/showPatient");
		} else {
			req.getRequestDispatcher("WEB-INF/jsp/findUser.jsp").forward(req, resp);
		}
	}

}
