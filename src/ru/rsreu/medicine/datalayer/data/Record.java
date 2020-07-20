package ru.rsreu.medicine.datalayer.data;

import java.util.Date;

/**
 * Data class Record
 *
 */
public class Record {
	private int id;
	private User patient;
	private User doctor;
	private String complaints;
	private String conclusion;
	private String prescribing;
	private boolean isAppear;
	private Date time;

	/**
	 * Create's empty instance
	 */
	public Record() {
	}

	/**
	 * Creates instance with fill properties
	 * 
	 * @param id         Record's id
	 * @param patient    Patient's data
	 * @param doctor     Doctor's data
	 * @param complaints Patient's complaints
	 * @param conclusion Doctor's conclusion
	 * @param Doctor's   prescribing
	 * @param isAppear   Is patient appeared
	 * @param time       Record's date and time
	 */
	public Record(int id, User patient, User doctor, String complaints, String conclusion, String prescribing,
			boolean isAppear, Date time) {
		super();
		this.id = id;
		this.patient = patient;
		this.doctor = doctor;
		this.complaints = complaints;
		this.conclusion = conclusion;
		this.prescribing = prescribing;
		this.isAppear = isAppear;
		this.time = time;
	}

	/**
	 * Gets record's id
	 * 
	 * @return record's id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets record's id
	 * 
	 * @param id record's id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets patient's data
	 * 
	 * @return patient's data
	 */
	public User getPatient() {
		return patient;
	}

	/**
	 * Sets patient's data
	 * 
	 * @param patient patient's data
	 */
	public void setPatient(User patient) {
		this.patient = patient;
	}

	/**
	 * Gets doctor data
	 * 
	 * @return doctor's data
	 */
	public User getDoctor() {
		return doctor;
	}

	/**
	 * Sets doctor's data
	 * 
	 * @param doctor doctor's data
	 */
	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	/**
	 * Gets patient's complaints
	 * 
	 * @return patient's complaints
	 */
	public String getComplaints() {
		return complaints;
	}

	/**
	 * Sets patient's complaints
	 * 
	 * @param complaints patient's complaints
	 */
	public void setComplaints(String complaints) {
		this.complaints = complaints;
	}

	/**
	 * Gets doctor's conclusion
	 * 
	 * @return doctor's conclusion
	 */
	public String getConclusion() {
		return conclusion;
	}

	/**
	 * Sets doctor's conclusion
	 * 
	 * @param conclusion doctor's conclusion
	 */
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	/**
	 * Gets doctor's prescribing
	 * 
	 * @return doctor's prescribing
	 */
	public String getPrescribing() {
		return prescribing;
	}

	/**
	 * Sets doctor's prescribing
	 * 
	 * @param prescribing doctor's prescribing
	 */
	public void setPrescribing(String prescribing) {
		this.prescribing = prescribing;
	}

	/**
	 * Gets is patient appeared
	 * 
	 * @return is patient appeared
	 */
	public boolean isAppear() {
		return isAppear;
	}

	/**
	 * Sets is patient appeared
	 * 
	 * @param isAppear is patient appeared
	 */
	public void setAppear(boolean isAppear) {
		this.isAppear = isAppear;
	}

	/**
	 * Gets record's time
	 * 
	 * @return record's time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Sets record's time
	 * 
	 * @param time record's time
	 */
	public void setTime(Date time) {
		this.time = time;
	}
}
