package ru.rsreu.medicine.datalayer.data;

import java.util.Date;

/**
 * User's data class
 *
 */
public class User {
	private int id;
	private String name;
	private String address;
	private boolean isRemoved;
	private Date dateBirth;
	private Roles role;
	private String insurance;
	private String specialization;

	/**
	 * 
	 * Creates instance with fill properties
	 * 
	 * @param name      User's name
	 * @param address   User's address
	 * @param isRemoved Is deactivated user
	 * @param dateBirth User's date birth
	 * @param role      User's role
	 * @param id        User's id
	 * @param insurance User's insurance.
	 */
	public User(String name, String address, boolean isRemoved, Date dateBirth, Roles role, int id, String insurance) {
		this.name = name;
		this.address = address;
		this.isRemoved = isRemoved;
		this.dateBirth = dateBirth;
		this.role = role;
		this.id = id;
		this.insurance = insurance;
	}

	/**
	 * Creates instance
	 * 
	 * @param id             User's id
	 * @param name           User's name
	 * @param specialization User's specialization
	 */
	public User(int id, String name, String specialization) {
		this.name = name;
		this.id = id;
		this.specialization = specialization;
	}

	/**
	 * Gets user's insurance
	 * 
	 * @return user's insurance
	 */
	public String getInsurance() {
		return insurance;
	}

	/**
	 * Gets user's name
	 * 
	 * @return user's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets user's address
	 * 
	 * @return user's address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Gets is user deactivated
	 * 
	 * @return is user deactivated
	 */

	public boolean isRemoved() {
		return isRemoved;
	}

	/**
	 * Gets user's dateBirth
	 * 
	 * @return user's dateBirth
	 */
	public Date getDateBirth() {
		return dateBirth;
	}

	/**
	 * Gets user's role
	 * 
	 * @return user's role
	 */

	public Roles getRole() {
		return role;
	}

	/**
	 * Gets user's id
	 * 
	 * @return user's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets user's specialization
	 * 
	 * @param specialization user's specialization
	 */
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	/**
	 * Gets user's name
	 * 
	 * @return user's name
	 */

	public String getSpecialization() {
		return specialization;
	}
}
