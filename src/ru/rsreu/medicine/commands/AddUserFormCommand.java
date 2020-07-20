package ru.rsreu.medicine.commands;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.DBType;
import ru.rsreu.medicine.datalayer.RegionDAO;
import ru.rsreu.medicine.datalayer.data.Region;
import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * Command for showing form adding user
 * @author Admin
 *
 */
public class AddUserFormCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (user.getRole() != Roles.ADMIN || user.getRole() != Roles.RECEPTIONLIST) {
				String messages = (String) (session.getAttribute("messages") != null ? session.getAttribute("messages") : "");
				session.setAttribute("messages", null);
				RegionDAO regDao = DAOFactory.getInstance(DBType.ORACLE).getRegionDAO();
				List<Region> regions = regDao.getDoctorsRegions();
				request.setAttribute("regions", regions);
				if (user.getRole() == Roles.ADMIN) {
					request.setAttribute("roles", Roles.values());
				} else {
					request.setAttribute("roles", Collections.singletonList(Roles.USER));
				}
				request.setAttribute("messages", messages);
				request.getRequestDispatcher("WEB-INF/jsp/addUser.jsp").forward(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		}
	}

}
