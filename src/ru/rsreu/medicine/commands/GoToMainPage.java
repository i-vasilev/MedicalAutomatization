package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for going to main menu
 * 
 * @author Admin
 *
 */
public class GoToMainPage implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Command command = null;
		if (user.getRole().equals(Roles.USER) || user.getRole().equals(Roles.RECEPTIONLIST)
				|| user.getRole().equals(Roles.ADMIN)) {
			command = new FindUserFormCommand();
		}
		if (user.getRole().equals(Roles.DOCTOR)) {
			command = new DoctorReceptionFormCommand();
		}
		if (command != null) {
			command.execute(request, response);
		} else {
			response.sendError(505);
		}

	}

}
