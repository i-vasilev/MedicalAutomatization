package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command pattern
 * 
 * @author Admin
 *
 */
public interface Command {
	/**
	 * Executes command
	 * 
	 * @param request  User's request
	 * @param response Responce for user
	 * @throws ServletException
	 * @throws IOException
	 */
	void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
