package ru.rsreu.medicine.datalayer;

public abstract class DAOFactory {
	public static DAOFactory getInstance(DBType dbType) {
		DAOFactory result = dbType.getDAOFactory();
		return result;
	}

	public abstract RecordDAO getRecordDAO();

	public abstract SpecializationDAO getSpecializationDAO();
	
	public abstract RegionDAO getRegionDAO();

	public abstract UserDAO getUserDAO();
}
