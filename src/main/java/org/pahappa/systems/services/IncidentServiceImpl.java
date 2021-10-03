package org.pahappa.systems.services;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pahappa.systems.enums.Status;
import org.pahappa.systems.hibernateUtils.HibernateUtil;
import org.pahappa.systems.models.Incident;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Implements CRUD functionality for transacting with the DB on behalf of the View
 */

@Service
@Transactional
public class IncidentServiceImpl implements IncidentService {
	//For creating transactions to the DB
	private static Transaction transaction;
	//Reads Config file and opens connection to DB
	private static Session session = HibernateUtil.getSessionFactory().openSession();


	@Override
	public Incident saveIncident(Incident incident) throws Exception {
		try {
			//Set unset properties of the Incident
			incident.setStatus(Status.DRAFT);
			incident.setCreatedOn(new Date());

			transaction = session.beginTransaction();
			//Attempt to Save to DB
			session.saveOrUpdate(incident);

		} catch (HibernateException exception) {
			exception.printStackTrace();
		} finally {

			//If no errors, send the transaction to the Server
			transaction.commit();

			return getIncidentOfId(incident.getId());
		}
	}

	@Override
	public Incident updateIncident(Incident incident) throws Exception {
		try {
			transaction = session.beginTransaction();

			session.update(incident);
		} catch (HibernateException e){
			e.printStackTrace();
		} finally {
			transaction.commit();
		}
		return getIncidentOfId(incident.getId());
	}

	@Override
	public List<Incident> getAllIncidents() {

		List allIncidents = new ArrayList<Incident>();
		try {
			transaction = session.beginTransaction();

			//Get All Incidents
			allIncidents = session.createQuery("from Incident ").list();

		} catch (HibernateException exception) {
			exception.printStackTrace();
		} finally {

			//If no errors, send the transaction to the Server
			transaction.commit();
		}
		return allIncidents;

	}

	@Override
	public List<Incident> getRedflagIncidents() {
		List list = new ArrayList<Incident>();
		try {
			transaction = session.beginTransaction();

			list = session.createQuery("from Incident I where I.type = :incident_type").setParameter("incident_type", "RED_FLAG").list();

		} catch (HibernateException exception) {
			exception.printStackTrace();
		} finally {
			transaction.commit();
		}
		return  list;
	}

	@Override
	public List<Incident> getInterventionIncidents() {
		List list = new ArrayList<Incident>();
		try {
			transaction = session.beginTransaction();

			list = session.createQuery("from Incident I where I.type = :incident_type").setParameter("incident_type", "INTERVENTION").list();

		} catch (HibernateException exception) {
			exception.printStackTrace();
		} finally {
			transaction.commit();
		}
		return  list;
	}

	@Override
	public int countIncidents() {
		return getAllIncidents().size();
	}

	@Override
	public boolean incidentExists(Incident incident) {
		ArrayList<Incident> list = new ArrayList<Incident>();
		try {
			transaction = session.beginTransaction();

			list = (ArrayList<Incident>) session.createQuery("from Incident I where id = :incident_id").setParameter("incident_id", incident.getId()).list();

		} catch (HibernateException exception) {
			exception.printStackTrace();
		} finally {
			transaction.commit();
		}

		if (list.size() == 0){
			return false;
		}
		else
			return true;
	}

	@Override
	public Incident getIncidentOfId(int id) {
		ArrayList<Incident> list = new ArrayList<Incident>();
		try {
			transaction = session.beginTransaction();

			list = (ArrayList<Incident>) session.createQuery("from Incident I where id = :incident_id").setParameter("incident_id", id).list();

		} catch (HibernateException exception) {
			exception.printStackTrace();
		} finally {
			transaction.commit();

			return (Incident) list.get(0);
		}

	}

	@Override
	public void deleteIncident(Incident incident) {
		try {
			transaction = session.beginTransaction();

//            session.createQuery("DELETE from Incident I where I.id = :incident_id")
//                    .setParameter("incident_id", incident.getId()).list();
			session.delete(incident);
		} catch (HibernateException exception) {
			exception.printStackTrace();
		} finally {
			transaction.commit();
		}
	}
}
