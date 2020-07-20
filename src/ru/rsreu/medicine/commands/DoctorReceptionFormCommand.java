package ru.rsreu.medicine.commands;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.data.Record;
import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for showing form doctor's reception
 * @author Admin
 *
 */
public class DoctorReceptionFormCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user.getRole().equals(Roles.DOCTOR)) {
			List<Record> records = DAOFactory.getInstance(DBType.ORACLE).getRecordDAO()
					.getRecordsByDateAndDoctor(new Date(), user);
			request.setAttribute("records", records);
			request.getRequestDispatcher("/WEB-INF/jsp/doctorReception.jsp").forward(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}

	}

}
