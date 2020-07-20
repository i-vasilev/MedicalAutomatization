package ru.rsreu.medicine.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.UserDAO;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for saving user
 * 
 * @author Admin
 *
 */
public class SaveUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String specializationString = request.getParameter("specialization");
		String regionString = request.getParameter("region");
		String idDoctorString = request.getParameter("doctor_id");
		String isActivatedString = request.getParameter("deactivated");
		UserDAO userDAO = DAOFactory.getInstance(DBType.ORACLE).getUserDAO();
		if (idDoctorString != null) {
			int idDoctor = Integer.parseInt(idDoctorString);
			if (specializationString != null) {
				int idSpecialization = Integer.parseInt(specializationString);
				userDAO.updateSpecialization(idDoctor, idSpecialization);
			}
			if (regionString != null) {
				int idRegion = Integer.parseInt(regionString);
				userDAO.updateRegion(idDoctor, idRegion);
			}
			boolean isActivated = isActivatedString == null;
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				if (user.getId() != idDoctor) {
					DAOFactory.getInstance(DBType.ORACLE).getUserDAO().activationUser(idDoctor, isActivated);
				}
			}
		}
		FindUserCommand command = new FindUserCommand();
		command.execute(request, response);

	}

}
