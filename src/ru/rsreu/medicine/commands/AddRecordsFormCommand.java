package ru.rsreu.medicine.commands;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.RecordDAO;
import ru.rsreu.medicine.datalayer.UserDAO;
import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for adding records
 *
 */
public class AddRecordsFormCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		DAOFactory factory = DAOFactory.getInstance(DBType.ORACLE);
		if (user.getRole().equals(Roles.RECEPTIONLIST)) {
			if (request.getParameter("cmd") == null) {
				UserDAO userDAO = factory.getUserDAO();
				List<User> doctors = userDAO.getDoctors();
				request.setAttribute("doctors", doctors);
				request.getRequestDispatcher("/WEB-INF/jsp/addRecordsToDoctor.jsp").forward(request, response);
			} else {
				String idDoctorString = request.getParameter("doctor");
				int idDoctor = Integer.parseInt(idDoctorString);
				String dateString = request.getParameter("date");
				String timeStartString = request.getParameter("timeStart");
				String timeEndString = request.getParameter("timeEnd");
				String intervalString = request.getParameter("interval");
				if (intervalString != null && timeEndString != null && timeStartString != null && dateString != null) {
					try {
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
						SimpleDateFormat tf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
						int interval = Integer.parseInt(intervalString);

						Calendar timeStartCal = stringToCalendar(timeStartString, tf);
						Calendar timeEndCal = stringToCalendar(timeEndString, tf);

						timeStartCal = addMinutesAndHoursToCalendar(stringToCalendar(dateString, df), timeStartCal);
						timeEndCal = addMinutesAndHoursToCalendar(stringToCalendar(dateString, df), timeEndCal);
						RecordDAO recordDAO = factory.getRecordDAO();

						while (timeStartCal.before(timeEndCal)) {
							recordDAO.addRecord(idDoctor, timeStartCal.getTime());
							timeStartCal.add(Calendar.MINUTE, interval);
						}
					} catch (ParseException e) {
						e.printStackTrace();
						response.sendRedirect(request.getContextPath());
					}

				}
			}
		} else {
			response.sendRedirect(request.getContextPath());

		}
	}

	private Calendar stringToCalendar(String date, SimpleDateFormat df) throws ParseException {
		Date timeStart = df.parse(date);
		Calendar timeStartCal = new GregorianCalendar();
		timeStartCal.setTime(timeStart);
		return timeStartCal;
	}

	private Calendar addMinutesAndHoursToCalendar(Calendar date, Calendar time) {
		date.add(Calendar.MINUTE, time.get(Calendar.MINUTE));
		date.add(Calendar.HOUR_OF_DAY, time.get(Calendar.HOUR_OF_DAY));
		return date;
	}
}
