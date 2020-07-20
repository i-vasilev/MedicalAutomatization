package ru.rsreu.medicine.datalayer.oracledb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

import ru.rsreu.medicine.datalayer.DAOFactory;
import ru.rsreu.medicine.datalayer.RecordDAO;
import ru.rsreu.medicine.datalayer.RegionDAO;
import ru.rsreu.medicine.datalayer.SpecializationDAO;
import ru.rsreu.medicine.datalayer.UserDAO;

/**
 * Oracle DB Factory class
 */
public class OracleDBDAOFactory extends DAOFactory {
	private static volatile OracleDBDAOFactory instance;
	private Connection connection;

	private OracleDBDAOFactory() {
	}

	/**
	 * Gets {@link ru.rsreu.medicine.datalayer.DAOFactory}'s instance
	 * 
	 * @return {@link ru.rsreu.medicine.datalayer.DAOFactory}'s instance
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static OracleDBDAOFactory getInstance() throws ClassNotFoundException, SQLException {
		OracleDBDAOFactory factory = instance;
		if (instance == null) {
			synchronized (OracleDBDAOFactory.class) {
				instance = factory = new OracleDBDAOFactory();
				factory.connected();
			}
		}
		return factory;
	}

	private void connected() throws ClassNotFoundException, SQLException {
		Locale.setDefault(Locale.ENGLISH);
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@Admin:1521:XE";
		String user = "system";
		String password = "1";
		connection = DriverManager.getConnection(url, user, password);
		System.out.println("Connected to oracle DB!");
	}

	/**
	 * Gets RecordDAO
	 */
	@Override
	public RecordDAO getRecordDAO() {
		return new OracleRecordDAO(connection);
	}

	/**
	 * Gets UserDAO
	 */
	@Override
	public UserDAO getUserDAO() {
		return new OracleUserDAO(connection);
	}

	/**
	 * Gets RegionDAO
	 */
	@Override
	public RegionDAO getRegionDAO() {
		return new OracleRegionDAO(connection);
	}

	/**
	 * Gets SpecializationDAO
	 */
	@Override
	public SpecializationDAO getSpecializationDAO() {
		return new OracleSpecializationDAO(connection);
	}

}
