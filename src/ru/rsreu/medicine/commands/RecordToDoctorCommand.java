package ru.rsreu.medicine.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.RecordDAO;
import ru.rsreu.medicine.datalayer.UserDAO;
import ru.rsreu.medicine.datalayer.data.Record;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for recording to doctor
 * @author Admin
 *
 */
public class RecordToDoctorCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		String doctorInsuranceString = (String) request.getParameter("doctor");
		String idUser = (String) request.getParameter("idUser");
		
		String timeString = request.getParameter("time");
		UserDAO userDAO = factory.getUserDAO();
		if (doctorInsuranceString == null) {
			List<User> doctors = userDAO.getDoctors();
			request.setAttribute("doctors", doctors);
			request.setAttribute("idUser", idUser);
		} else {
			User doctor = userDAO.getPatientByInsurance(doctorInsuranceString);
			RecordDAO recordDao = factory.getRecordDAO();
			if (timeString == null) {
				List<Record> records = recordDao.getRecordsByDoctor(doctor.getId());
				request.setAttribute("records", records);
				request.setAttribute("selectedDoctor", doctor);
				request.setAttribute("idUser", idUser);
			} else {
				int time = Integer.parseInt(timeString);
				int idCurrentUser = Integer.parseInt(idUser);
				recordDao.setPatientToRecord(time, idCurrentUser);
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/recordToDoctor.jsp").forward(request, response);

	}

}
