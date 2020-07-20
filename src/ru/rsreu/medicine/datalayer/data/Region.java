package ru.rsreu.medicine.datalayer.data;

/**
 * Region's data class
 *
 */
public class Region {
	int id;
	String doctorName;

	/**
	 * Gets region's ID
	 * 
	 * @return region's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets region's id
	 * 
	 * @param id region's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Get's doctor's name
	 * 
	 * @return doctor's name
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * Set's doctor's name
	 * 
	 * @param doctorName doctor's name
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
}
