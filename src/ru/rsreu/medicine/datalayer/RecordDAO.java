package ru.rsreu.medicine.datalayer;

import java.util.Date;
import java.util.List;

import ru.rsreu.medicine.datalayer.data.Record;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * DAO for Records
 *
 */
public interface RecordDAO {
	/**
	 * Gets Records by date and doctor
	 */
	List<Record> getRecordsByDateAndDoctor(Date date, User user);

	/**
	 * Gets records by patient
	 */
	List<Record> getRecordsByPatient(String insurance);

	/**
	 * Updates complaints, conclusion, prescribing in record
	 */
	void updateRecord(int id, boolean isAppear, String complaints, String conclusion, String prescribing);

	/**
	 * Removes Patient From Record
	 */
	void removePatientFromRecord(int id);

	/**
	 * Gets doctor's records
	 */
	List<Record> getRecordsByDoctor(int idDoctor);

	/**
	 * Records patient to record
	 */
	void setPatientToRecord(int id, int idPatient);

	/**
	 * Adds record to doctor
	 */
	void addRecord(int idDoctor, Date date);
}
