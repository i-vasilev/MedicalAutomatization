package ru.rsreu.medicine.datalayer;

import java.util.List;

import ru.rsreu.medicine.datalayer.data.Specialization;
import ru.rsreu.medicine.datalayer.data.User;

/**
 * DAO for specialization
 */
public interface SpecializationDAO {
	/**
	 * Gets all specializations
	 */
	List<Specialization> getSpecializations();

	/**
	 * Gets doctor's current specialization
	 */
	Specialization getCurrentSpecialization(User doctor);
}
