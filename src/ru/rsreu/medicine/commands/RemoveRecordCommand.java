package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.RecordDAO;

/**
 * Command for removing record 
 * @author Admin
 *
 */
public class RemoveRecordCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idRecord = Integer.parseInt(request.getParameter("id"));
		RecordDAO recordDAO = DAOFactory.getInstance(DBType.ORACLE).getRecordDAO();
		recordDAO.removePatientFromRecord(idRecord);
		FindUserCommand command = new FindUserCommand();
		command.execute(request, response);
	}

}
