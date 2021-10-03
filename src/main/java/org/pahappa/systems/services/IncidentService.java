

package org.pahappa.systems.services;

import org.pahappa.systems.exceptions.SavingFailedException;
import org.pahappa.systems.exceptions.ValidationFailedException;
import org.pahappa.systems.models.Incident;

import java.util.List;

public interface IncidentService {
	
	/**
	 * Saves an {@link Incident}. An incident must have an id, title, type, status and comment.
	 * In case any of (id, title, type, createdOn, comment and status) contains a null value, a {@link ValidationFailedException} must be thrown
	 * The title, and comment must not be empty.
	 * 
	 * In case a Incident fails to save after all validation criteria is met. Throw {@link SavingFailedException}
	 * 
	 * @param activity
	 * @throws Exception
	 * @return saved {@link Incident}
	 */
	Incident saveIncident(Incident incident) throws Exception;

	/**
	 * Updates an {@link Incident}. An incident must have an id, title, type, status and comment.
	 * In case any of (id, title, type, createdOn, comment and status) contains a null value, a {@link ValidationFailedException} must be thrown
	 * The title, and comment must not be empty.
	 * 
	 * In case a Incident fails to save after all validation criteria is met. Throw {@link SavingFailedException}
	 * 
	 * @param activity
	 * @throws Exception
	 * @return updated {@link Incident}
	 */
	Incident updateIncident(Incident incident) throws Exception;
	
	/**
	 * Gets all available {@link Incident}
	 * 
	 * @return a list of {@link Incident}
	 */
	List<Incident> getAllIncidents();
	
	/**
	 * Gets all available {@link Incident} of type RedFlag
	 * 
	 * @return a list of RedFlag {@link Incident}
	 */
	List<Incident> getRedflagIncidents();
	
	/**
	 * Gets all available {@link Incident} of type Intervention
	 * 
	 * @return a list of Intervention {@link Incident}
	 */
	List<Incident> getInterventionIncidents();

	/**
	 * Counts all available {@link Incident}
	 * 
	 * @return a count {@link Incident}
	 */
	int countIncidents();

	/**
	 * Check if an {@link Incident} exist
	 * 
	 * @return
	 */
	boolean incidentExists(Incident incident);
	
	/**
	 * Gets an {@link Incident} of a given id
	 * 
	 * @param id
	 * @return updated {@link Incident}
	 */
	Incident getIncidentOfId(int id);

	/**
	 * Deletes an {@link Incident}
	 * 
	 * @param incident
	 */
	void deleteIncident(Incident incident);
}
