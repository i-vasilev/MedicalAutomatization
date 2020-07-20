package ru.rsreu.medicine.datalayer;

import java.sql.Date;
import java.util.List;

import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * DAO for user
 */
public interface UserDAO {
	/**
	 * gets User by login and password
	 */
	User logIn(String login, String password);
	/**
	 * Gets patient by insurance
	 */
	User getPatientByInsurance(String insurance);
	/**
	 * activates/deactivates user
	 */
	boolean activationUser(int id, boolean isActive);
	/**
	 * Adds user to db
	 */
	User addUser(String insurance, String name, Date dateBirth, String address, int idRegion, Roles role, String password);
	/**
	 * Updates specialization
	 */
	void updateSpecialization(int id, int specialization);

	/**
	 * Updates region
	 */
	void updateRegion(int id, int region);
	/**
	 * Gets doctors
	 */
	List<User> getDoctors();
}
