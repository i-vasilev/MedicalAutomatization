package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for logout 
 * @author Admin
 *
 */
public class LogoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().setAttribute("user", null);
		request.getRequestDispatcher("/login").forward(request, response);
	}

}
