package ru.rsreu.medicine.datalayer;

import java.util.List;

import ru.rsreu.medicine.datalayer.data.Region;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * DAO for regions
 */
public interface RegionDAO {
	/**
	 * Gets doctors're regions
	 */
	public List<Region> getDoctorsRegions();
	/**
	 * Gets all regions
	 */
	public List<Region> getAllRegions();
	/**
	 * Gets doctor's current region
	 */
	public Region getCurrentRegion(User doctor);
}
