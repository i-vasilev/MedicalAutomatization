package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command for showing patient reception's form
 * 
 * @author Admin
 *
 */
public class PatientReceptionFormCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("id", request.getParameter("id"));
		request.getRequestDispatcher("/WEB-INF/jsp/patientReception.jsp").forward(request, response);
	}

}
