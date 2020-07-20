package ru.rsreu.medicine.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.RecordDAO;
import ru.rsreu.medicine.datalayer.RegionDAO;
import ru.rsreu.medicine.datalayer.SpecializationDAO;
import ru.rsreu.medicine.datalayer.UserDAO;
import ru.rsreu.medicine.datalayer.data.Record;
import ru.rsreu.medicine.datalayer.data.Region;
import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.Specialization;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for finding user's
 * @author Admin
 *
 */
public class FindUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String insurance = req.getParameter("insurance");
		DAOFactory daoFactory = DAOFactory.getInstance(DBType.ORACLE);
		UserDAO userDao = daoFactory.getUserDAO();
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		User patient;
		if (user.getRole().equals(Roles.USER)) {
			patient = user;
			insurance = patient.getInsurance();
		} else {
			patient = userDao.getPatientByInsurance(insurance);
		}
		if (patient != null) {
			req.setAttribute("patient", patient);
			if (patient.getRole().equals(Roles.USER)) {
				RecordDAO recordDAO = daoFactory.getRecordDAO();
				List<Record> records = recordDAO.getRecordsByPatient(insurance);
				req.setAttribute("records", records);
			} else if (patient.getRole().equals(Roles.DOCTOR)) {
				SpecializationDAO specializationDAO = daoFactory.getSpecializationDAO();
				RegionDAO regionDAO = daoFactory.getRegionDAO();
				List<Specialization> specializations = specializationDAO.getSpecializations();
				List<Region> regions = regionDAO.getAllRegions();
				Region currentReg = regionDAO.getCurrentRegion(patient);
				Specialization currentSpecialization = specializationDAO.getCurrentSpecialization(patient);
				req.setAttribute("currentRegion", currentReg);
				req.setAttribute("currentSpecialization", currentSpecialization);
				req.setAttribute("regions", regions);
				req.setAttribute("specializations", specializations);
			}
			req.getRequestDispatcher("/WEB-INF/jsp/showPatient.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/findUser");
		}
	}
}
