package ru.rsreu.medicine.datalayer.oracledb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.rsreu.medicine.datalayer.RegionDAO;
import ru.rsreu.medicine.datalayer.data.Region;
import ru.rsreu.medicine.datalayer.data.User;
import ru.rsreu.medicine.utils.Resourcer;

/**
 * Oracle region dao
 */
public class OracleRegionDAO implements RegionDAO {

	private static final String GET_CURRENT_REGION = "getCurrentRegion";
	private static final String GET_ALL_REGIONS = "getAllRegions";
	private static final String GET_DOCTORS_REGIONS = "getDoctorsRegions";
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String ID_REGION = "id_region";
	private Connection connection;

	public OracleRegionDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * Gets doctors're regions
	 */
	@Override
	public List<Region> getDoctorsRegions() {
		PreparedStatement statement;
		List<Region> regions = new ArrayList<Region>();
		try {
			statement = connection.prepareStatement(Resourcer.getString(GET_DOCTORS_REGIONS));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Region region = new Region();
				region.setId(rs.getInt(ID));
				region.setDoctorName(rs.getString(NAME));
				regions.add(region);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regions;
	}

	/**
	 * Gets all regions
	 */
	@Override
	public List<Region> getAllRegions() {
		PreparedStatement statement;
		List<Region> regions = new ArrayList<Region>();
		try {
			statement = connection.prepareStatement(Resourcer.getString(GET_ALL_REGIONS));
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Region region = new Region();
				region.setId(rs.getInt(ID_REGION));
				regions.add(region);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return regions;
	}

	/**
	 * Gets doctor's current region
	 */
	@Override
	public Region getCurrentRegion(User doctor) {
		Region region = new Region();
		try {
			PreparedStatement statement = connection.prepareStatement(Resourcer.getString(GET_CURRENT_REGION));
			statement.setInt(1, doctor.getId());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				region.setId(rs.getInt(ID));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return region;
	}

}
