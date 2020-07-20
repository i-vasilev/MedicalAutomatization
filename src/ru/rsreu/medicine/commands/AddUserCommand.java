package ru.rsreu.medicine.commands;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.UserDAO;
import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;
import ru.rsreu.medicine.utils.PasswordGenerator;

/**
 * Command for adding user
 * @author Admin
 *
 */
public class AddUserCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		StringBuilder messages = new StringBuilder();
		String insurance = request.getParameter("insurance");
		String name = request.getParameter("name");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		String dateString = request.getParameter("dateBirth");
		Date dateBirth = new Date();
		if (!dateString.equals("")) {
			try {
				dateBirth = df.parse(dateString);
			} catch (ParseException e) {
				messages.append("Дата рождения не верная!<br />");
			}
		} else {
			messages.append("Дата рождения не может быть пустой!<br />");
		}
		String address = request.getParameter("address");
		int idRegion = Integer.parseInt(request.getParameter("region"));
		String role = request.getParameter("role");

		if (insurance.equals("")) {
			messages.append("Номер полиса не может быть пустым!<br />");
		}
		if (name.equals("")) {
			messages.append("Имя не может быть пустым!<br />");
		}
		if (address.equals("")) {
			messages.append("Адрес места жительства не может быть пустым!<br />");
		}

		if (messages.toString().equals("")) {
			PasswordGenerator passGenerator = new PasswordGenerator.PasswordGeneratorBuilder().useDigits(true)
					.useLower(true).useUpper(true).build();
			String password = passGenerator.generate(15);

			UserDAO userDAO = DAOFactory.getInstance(DBType.ORACLE).getUserDAO();
			User checksUser = userDAO.getPatientByInsurance(insurance);
			if (checksUser == null) {
				checksUser = userDAO.addUser(insurance, name, new java.sql.Date(dateBirth.getTime()), address, idRegion,
						Roles.valueOf(role), password);
				if (checksUser != null) {
					request.setAttribute("newUser", checksUser);
					request.setAttribute("password", password);
					request.getRequestDispatcher("/WEB-INF/jsp/profileParameters.jsp").forward(request, response);
				} else {
					messages.append("Возникли какие-то ошибки. Обратитесь, пожалуйста, к программисту.<br />");
				}
			} else {
				messages.append("Пользователь с таким номером полиса уже существует!<br />");
			}
		}
		if (!messages.toString().equals("")) {
			HttpSession session = request.getSession();
			session.setAttribute("messages", messages.toString());
			response.sendRedirect(request.getContextPath() + "/addUser");
		}
	}

}
