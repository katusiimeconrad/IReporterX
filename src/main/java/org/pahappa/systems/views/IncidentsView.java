package org.pahappa.systems.views;

import org.pahappa.systems.enums.Type;
import org.pahappa.systems.models.Incident;
import org.pahappa.systems.services.IncidentService;
import org.pahappa.systems.services.IncidentServiceImpl;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean( name = "incidentView")
@SessionScoped
public class IncidentsView {
    private IncidentService incidentService;
    private Incident incident;
    private List<Incident> incidents;

    @PostConstruct
    public void init() {
        this.incidentService = new IncidentServiceImpl();
        this.incident = new Incident();
        this.incidents = incidentService.getAllIncidents();
    }

    /**
     * Method saves an Incident to the DB
     * @param incident
     * @throws Exception
     */
    public void save(Incident incident) throws Exception {
        incidentService.saveIncident(incident);

    }

    /**
     * Returns the count of all incidents
     * @return
     */
    public int countIncidents(){
        return this.incidents.size();
    }

    public List<Incident> getAlIncidents(){
        this.incident = new Incident();
        this.incidents = incidentService.getAllIncidents();
        return this.incidents;
    }

    public List<Incident> getRedFlagIncidents(){
        //[redflagIncidents] will contain all incidents in [incidents] where type is REDFLAG
        List<Incident> redflagIncidents = new ArrayList<Incident>();
        for (Incident incident:incidents) {
            if( incident.getType() == Type.RED_FLAG ){
                redflagIncidents.add(incident);
            }
        }
        //The List will be empty if no incidents were marked as REDFLAG
        this.incidents = redflagIncidents;
        return this.incidents;
    }

    public List<Incident> getInterventionIncidents() {
        List<Incident> interventionIncidents =new ArrayList<Incident>();
        //looping through the incidents list to get intervention incidents
        for (Incident incident:incidents) {
            if (incident.getType()== Type.INTERVENTION) {
                interventionIncidents.add(incident);
            }
        }

        this.incidents = interventionIncidents;
        return this.incidents;
    }

    public Incident updateIncident(Incident incident) throws Exception {
        return incidentService.updateIncident(incident);
    }
    public Incident findById(int id){
         this.incident = incidentService.getIncidentOfId(id);
         return this.incident;
    }

    public void deleteIncident(Incident incident) {

        incidentService.deleteIncident(incident);

        this.incidents = incidentService.getAllIncidents();

    }


    //Getters & Setters
    public Incident getIncident() {
        return incident;
    }

    public void setIncident(Incident incident) {
        this.incident = incident;
    }

    /**
     * Get all incidents from the DB
     * @return
     */
    public List<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(List<Incident> incidents) {
        this.incidents = incidents;
    }

    public Type getRedFlag(){
        return Type.RED_FLAG;
    }
    public Type getIntervention(){
        return Type.INTERVENTION;
    }

}
