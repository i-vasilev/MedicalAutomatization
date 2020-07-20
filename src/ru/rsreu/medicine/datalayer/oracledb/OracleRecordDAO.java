package ru.rsreu.medicine.datalayer.oracledb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.rsreu.medicine.datalayer.RecordDAO;
import ru.rsreu.medicine.datalayer.data.Record;
import ru.rsreu.medicine.datalayer.data.Roles;
import ru.rsreu.medicine.datalayer.data.User;
import ru.rsreu.medicine.utils.Resourcer;

/**
 * Record's db class
 *
 */
public class OracleRecordDAO implements RecordDAO {

	private static final String ADD_RECORD = "addRecord";
	private static final String GET_RECORDS_BY_DOCTOR = "getRecordsByDoctor";
	private static final String REMOVE_PATIENT_FROM_RECORD = "removePatientFromRecord";
	private static final String SET_PATIENT_TO_RECORD = "setPatientToRecord";
	private static final String UPDATE_RECORD = "updateRecord";
	private static final String GET_RECORDS_BY_DATE_AND_DOCTOR = "getRecordsByDateAndDoctor";
	private static final String GET_RECORDS_BY_PATIENT = "getRecordsByPatient";
	private static final String ID_USER = "id_user";
	private static final String NAME = "name";
	private static final String INSURANCE = "insurance";
	private static final String ROLE = "role";
	private static final String IS_REMOVED = "is_removed";
	private static final String DATE_BIRTH = "date_birth";
	private static final String ADDRESS = "address";
	private static final String TIME = "time";
	private static final String IS_APPEAR = "is_appear";
	private static final String CONCLUSION = "conclusion";
	private static final String PRESCRIBING = "prescribing";
	private static final String ID_RECORD = "id_record";
	private static final String COMPLAINTS = "complaints";
	private static final String SPECIALIZATION = "specialization";
	private static final String DOCTOR_NAME = "doctor_name";
	private static final String ID_DOCTOR = "id_doctor";

	private Connection connection;

	public OracleRecordDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Gets records by patient
	 */
	@Override
	public List<Record> getRecordsByPatient(String insurance) {
		List<Record> records = new ArrayList<Record>();
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(GET_RECORDS_BY_PATIENT));
			statement.setString(1, insurance);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Record record = new Record();
				User doctor = new User(rs.getInt(ID_DOCTOR), rs.getString(DOCTOR_NAME), rs.getString(SPECIALIZATION));
				record.setDoctor(doctor);
				record.setComplaints(rs.getString(COMPLAINTS));
				record.setId(rs.getInt(ID_RECORD));
				record.setPrescribing(rs.getString(PRESCRIBING));
				record.setConclusion(rs.getString(CONCLUSION));
				record.setAppear(rs.getInt(IS_APPEAR) > 0);
				record.setTime(rs.getDate(TIME));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	/**
	 * Gets Records by date and doctor
	 */
	@Override
	public List<Record> getRecordsByDateAndDoctor(Date date, User user) {
		List<Record> records = new ArrayList<Record>();
		CallableStatement statement;
		try {
			statement = connection.prepareCall(Resourcer.getString(GET_RECORDS_BY_DATE_AND_DOCTOR));
			statement.setInt(1, user.getId());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Record record = new Record();
				record.setId(resultSet.getInt(ID_RECORD));
				record.setTime(resultSet.getDate(TIME));
				record.setPatient(new User(resultSet.getString(NAME), resultSet.getString(ADDRESS),
						resultSet.getInt(IS_REMOVED) == 0, resultSet.getDate(DATE_BIRTH),
						Roles.valueOf(resultSet.getString(ROLE)), resultSet.getInt(ID_USER),
						resultSet.getString(INSURANCE)));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	/**
	 * Updates complaints, conclusion, prescribing in record
	 */
	@Override
	public void updateRecord(int id, boolean isAppear, String complaints, String conclusion, String prescribing) {
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(UPDATE_RECORD));
			int isAppearInt = isAppear ? 1 : 0;
			statement.setString(1, complaints);
			statement.setString(2, conclusion);
			statement.setString(3, prescribing);
			statement.setInt(4, isAppearInt);
			statement.setInt(5, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Records patient to record
	 */
	@Override
	public void setPatientToRecord(int id, int idPatient) {
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(SET_PATIENT_TO_RECORD));
			statement.setInt(1, idPatient);
			statement.setInt(2, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes Patient From Record
	 */
	@Override
	public void removePatientFromRecord(int id) {
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(REMOVE_PATIENT_FROM_RECORD));
			statement.setInt(1, id);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets doctor's records
	 */
	@Override
	public List<Record> getRecordsByDoctor(int idDoctor) {
		List<Record> records = new ArrayList<Record>();
		CallableStatement statement;
		try {
			statement = connection.prepareCall(Resourcer.getString(GET_RECORDS_BY_DOCTOR));
			statement.setInt(1, idDoctor);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Record record = new Record();
				record.setId(resultSet.getInt(ID_RECORD));
				record.setTime(resultSet.getDate(TIME));
				records.add(record);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return records;
	}

	/**
	 * Adds record to doctor
	 */
	@Override
	public void addRecord(int idDoctor, Date date) {
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(ADD_RECORD));
			statement.setInt(1, idDoctor);
			statement.setDate(2, new java.sql.Date(date.getTime()));
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
