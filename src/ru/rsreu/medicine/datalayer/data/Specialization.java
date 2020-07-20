package ru.rsreu.medicine.datalayer.data;

/**
 * Doctor's specialization
 *
 */
public class Specialization {
	public String name;
	public int id;

	/**
	 * Creates empty instance
	 */
	public Specialization() {
	}

	/**
	 * Creates filled-properties instance
	 * 
	 * @param id   Specialization's id.
	 * @param name Specialization's name.
	 */
	public Specialization(int id, String name) {
		this.name = name;
		this.id = id;
	}

	/**
	 * Sets specialization's name.
	 * 
	 * @param name specialization's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets specialization's name.
	 * 
	 * @return specialization's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets specialization's id.
	 * 
	 * @param id specialization's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets specialization's id.
	 * 
	 * @return specialization's id.
	 */
	public int getId() {
		return id;
	}
}
