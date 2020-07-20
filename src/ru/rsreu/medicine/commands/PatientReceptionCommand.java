package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;

/**
 * Command for patient reception
 * 
 * @author Admin
 *
 */
public class PatientReceptionCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String complaints = (String) request.getParameter("complaints");
		String conclusion = (String) request.getParameter("conclusion");
		String prescribing = (String) request.getParameter("prescribing");
		int idRecord = Integer.parseInt(request.getParameter("id"));
		if (complaints != null && conclusion != null && prescribing != null) {
			DAOFactory.getInstance(DBType.ORACLE).getRecordDAO().updateRecord(idRecord, true, complaints, conclusion,
					prescribing);
		}
		response.sendRedirect(request.getContextPath() + "/doctorReception");

	}

}
