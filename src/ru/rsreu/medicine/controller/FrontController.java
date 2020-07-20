package ru.rsreu.medicine.controller;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.medicine.commands.AddRecordsFormCommand;
import ru.rsreu.medicine.commands.AddUserCommand;
import ru.rsreu.medicine.commands.AddUserFormCommand;
import ru.rsreu.medicine.commands.Command;
import ru.rsreu.medicine.commands.DoctorReceptionFormCommand;
import ru.rsreu.medicine.commands.FindUserCommand;
import ru.rsreu.medicine.commands.FindUserFormCommand;
import ru.rsreu.medicine.commands.GoToMainPage;
import ru.rsreu.medicine.commands.LoginCommand;
import ru.rsreu.medicine.commands.LoginFormCommand;
import ru.rsreu.medicine.commands.LogoutCommand;
import ru.rsreu.medicine.commands.PatientReceptionCommand;
import ru.rsreu.medicine.commands.PatientReceptionFormCommand;
import ru.rsreu.medicine.commands.RecordToDoctorCommand;
import ru.rsreu.medicine.commands.RemoveRecordCommand;
import ru.rsreu.medicine.commands.SaveUserCommand;

/**
 * Main servlet
 * @author Admin
 *
 */
@WebServlet(name = "FrontController")
public class FrontController extends HttpServlet {

	private ServletConfig config;

	/**
	 * Initiate servlet.
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	/**
	 * Gets servlet configuration
	 */
	@Override
	public ServletConfig getServletConfig() {
		return config;
	}

	/**
	 * Get request handler
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Command command = getCommand(req.getRequestURI().replace(req.getContextPath(), ""));
		if (command != null) {
			command.execute(req, resp);
		}
	}

	/**
	 * Post request handler
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmdName = req.getParameter("cmd");
		Command command = getCommand(cmdName);
		if (command != null) {
			command.execute(req, resp);
		}
	}

	/**
	 * Gets command by it's name
	 * @param commandName string
	 * @return command on execution
	 */
	private Command getCommand(String commandName) {
		Command command = null;
		if (commandName.equals("/")) {
			command = new GoToMainPage();
		}
		if (commandName.equals("Login")) {
			command = new LoginCommand();
		}
		if (commandName.contentEquals("reception")) {
			command = new PatientReceptionCommand();
		}
		if (commandName.equals("saveUser")) {
			command = new SaveUserCommand();
		}
		if (commandName.equals("/login")) {
			command = new LoginFormCommand();
		}
		if (commandName.equals("findUser")) {
			command = new FindUserCommand();
		}
		if (commandName.equals("/findUser")) {
			command = new FindUserFormCommand();
		}
		if (commandName.equals("/showPatient")) {
			command = new FindUserCommand();
		}
		if (commandName.equals("/addUser")) {
			command = new AddUserFormCommand();
		}
		if (commandName.equals("addUser")) {
			command = new AddUserCommand();
		}
		if (commandName.equals("removeRecord")) {
			command = new RemoveRecordCommand();
		}
		if (commandName.equals("/doctorReception")) {
			command = new DoctorReceptionFormCommand();
		}
		if (commandName.equals("/patientReception")) {
			command = new PatientReceptionFormCommand();
		}
		if (commandName.equals("/addRecords") || commandName.equals("addRecords")) {
			command = new AddRecordsFormCommand();
		}
		if (commandName.equals("/recordToDoctor") || commandName.equals("record")) {
			command = new RecordToDoctorCommand();
		}
		if (commandName.equals("/logout")) {
			command = new LogoutCommand();
		}
		if (commandName.equals("/main")) {
			command = new GoToMainPage();
		}
		return command;
	}
}
