/**
 * In this class, I test the Hibernate ORM by saving and retrieving from the DB
 * @author Blaki
 */

package org.pahappa.systems.views;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pahappa.systems.enums.Type;
import org.pahappa.systems.hibernateUtils.HibernateUtil;
import org.pahappa.systems.models.Incident;
import org.pahappa.systems.services.IncidentServiceImpl;
import org.pahappa.systems.services.IncidentServiceImplConsole;

public class iReporterDB {
    //For creating transactions to the DB
    private static Transaction transaction;
    //Reads Config file and opens connection to DB
    private static Session session = HibernateUtil.getSessionFactory().openSession();


    public static void main(String[] args) throws Exception {
        IncidentServiceImplConsole incidentService = new IncidentServiceImplConsole();

        Incident incident = new Incident();

        incident.setTitle("Emyooja");
        incident.setType(Type.RED_FLAG);
        incident.setComment("Emyooja Funds were lost on the way");

//        //incidentService.saveIncident(incident);
//
//        try {
//            transaction = session.beginTransaction();
//
//            //Attempt to Save to DB
//            session.save(incidentService.saveIncident(incident));
//
//        } catch (HibernateException exception){
//            exception.printStackTrace();
//        } finally {
//
//            //If no errors, send the transaction to the Server
//            transaction.commit();
//
//            System.out.println("Incident Saved!");
//        }


        IncidentServiceImpl x = new IncidentServiceImpl();

        Incident saved = x.saveIncident(incident);

        System.out.println(x.getAllIncidents());

        System.out.println("++++++++=======jjjjjjj" +x.getIncidentOfId(incident.getId()));

        System.out.println("++++++++=======" + x.incidentExists(saved));

        System.out.println(x.countIncidents());

//        x.deleteIncident(saved);

        System.out.println(x.countIncidents());

        saved.setType(Type.INTERVENTION);

        System.out.println(x.updateIncident(saved));



//        try {
//            transObj = sessionObj.beginTransaction();
//            sessionObj.save(employee);
//        } catch (HibernateException exceptionObj) {
//            exceptionObj.printStackTrace();
//        } finally {
//            transObj.commit();
//        }
    }
}
