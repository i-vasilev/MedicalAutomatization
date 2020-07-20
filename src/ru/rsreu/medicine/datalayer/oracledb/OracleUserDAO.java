package ru.rsreu.medicine.datalayer.oracledb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.medicine.datalayer.UserDAO;
import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;
import ru.rsreu.medicine.utils.Resourcer;

/**
 * User's db class
 *
 */
public class OracleUserDAO implements UserDAO {
	
	private static final String GET_DOCTORS = "getDoctors";
	private static final String UPDATE_REGION = "updateRegion";
	private static final String UPDATE_SPECIALIZATION = "updateSpecialization";
	private static final String ADD_USER = "addUser";
	private static final String ACTIVATION_USER = "activationUser";
	private static final String GET_PATIENT_BY_INSURANCE = "getPatientByInsurance";
	private static final String LOGIN = "Login";
	
	private static final String SPECIALIZATION = "specialization";
	private static final String INSURANCE = "insurance";
	private static final String ID_USER = "id_user";
	private static final String ROLE = "role";
	private static final String DATE_BIRTH = "date_birth";
	private static final String IS_REMOVED = "is_removed";
	private static final String ADDRESS = "address";
	private static final String NAME = "name";
	private Connection connection;

	public OracleUserDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * gets User by login and password
	 */
	@Override
	public User logIn(String login, String password) {
		User user = null;
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(LOGIN));
			statement.setString(1, login);
			statement.setString(2, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString(NAME), rs.getString(ADDRESS), rs.getInt(IS_REMOVED) == 1,
						rs.getDate(DATE_BIRTH), Roles.valueOf(rs.getString(ROLE)), rs.getInt(ID_USER),
						rs.getString(INSURANCE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Gets patient by insurance
	 */
	@Override
	public User getPatientByInsurance(String insurance) {
		User user = null;
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(GET_PATIENT_BY_INSURANCE));

			statement.setString(1, insurance);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				user = new User(rs.getString(NAME), rs.getString(ADDRESS), rs.getInt(IS_REMOVED) == 1,
						rs.getDate(DATE_BIRTH), Roles.valueOf(rs.getString(ROLE)), rs.getInt(ID_USER),
						rs.getString(INSURANCE));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * activates/deactivates user
	 */
	@Override
	public boolean activationUser(int id, boolean isActive) {
		boolean result = true;
		try {
			connection.setAutoCommit(true);
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(ACTIVATION_USER));
			int isRemoved = isActive ? 0 : 1;
			statement.setInt(1, isRemoved);
			statement.setInt(2, id);
			System.out.println(statement.executeUpdate());
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			result = false;
		}
		return result;
	}

	/**
	 * Adds user to db
	 */
	@Override
	public User addUser(String insurance, String name, Date dateBirth, String address, int idRegion, Roles role,
			String password) {
		User result = null;
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(ADD_USER));
			statement.setString(1, insurance);
			statement.setString(2, name);
			statement.setDate(3, dateBirth);
			statement.setString(4, address);
			statement.setInt(5, idRegion);
			statement.setString(6, role.toString());
			statement.setString(7, password);
			statement.execute();
			result = getPatientByInsurance(insurance);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Updates specialization
	 */
	@Override
	public void updateSpecialization(int id, int specialization) {
		try {
			CallableStatement statement = connection.prepareCall(Resourcer.getString(UPDATE_SPECIALIZATION));
			statement.setInt(1, id);
			statement.setInt(4, id);
			statement.setInt(2, specialization);
			statement.setInt(3, specialization);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Updates region
	 */
	@Override
	public void updateRegion(int id, int region) {
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(UPDATE_REGION));
			statement.setInt(1, id);
			statement.setInt(2, region);
			statement.setInt(3, region);
			statement.setInt(4, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets doctors
	 */
	@Override
	public List<User> getDoctors() {
		List<User> doctors = new ArrayList<User>();
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(GET_DOCTORS));
			statement.setString(1, Roles.DOCTOR.toString());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User user = new User(rs.getString(NAME), rs.getString(ADDRESS), rs.getInt(IS_REMOVED) == 1,
						rs.getDate(DATE_BIRTH), Roles.valueOf(rs.getString(ROLE)), rs.getInt(ID_USER),
						rs.getString(INSURANCE));
				user.setSpecialization(rs.getString(SPECIALIZATION));
				doctors.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctors;
	}

}
