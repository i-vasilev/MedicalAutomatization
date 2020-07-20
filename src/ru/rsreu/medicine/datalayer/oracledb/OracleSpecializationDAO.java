package ru.rsreu.medicine.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.medicine.datalayer.SpecializationDAO;
import ru.rsreu.medicine.datalayer.data.Specialization;
import ru.rsreu.medicine.datalayer.data.User;
import ru.rsreu.medicine.utils.Resourcer;
/**
 * Specialization's db class
 *
 */
public class OracleSpecializationDAO implements SpecializationDAO {

	private static final String GET_CURRENT_SPECIALIZATION = "getCurrentSpecialization";
	private static final String GET_SPECIALIZATIONS = "getSpecializations";
	private static final String SPECIALIZATION = "specialization";
	private static final String ID_SPECIALIZATION = "id_specialization";
	private Connection connection;

	public OracleSpecializationDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Gets all specializations
	 */
	@Override
	public List<Specialization> getSpecializations() {
		List<Specialization> specializations = new ArrayList<Specialization>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(Resourcer.getString(GET_SPECIALIZATIONS));
			while (rs.next()) {
				specializations.add(new Specialization(rs.getInt(ID_SPECIALIZATION), rs.getString(SPECIALIZATION)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specializations;
	}

	/**
	 * Gets doctor's current specialization
	 */
	@Override
	public Specialization getCurrentSpecialization(User doctor) {
		Specialization specialization = new Specialization();
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(GET_CURRENT_SPECIALIZATION));
			statement.setInt(1, doctor.getId());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				specialization.setId(rs.getInt(SPECIALIZATION));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return specialization;
	}

}
