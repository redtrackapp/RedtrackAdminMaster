package com.dbs.redtrackadmin.integration.dao;

import java.util.List;

import com.dbs.redtrackadmin.integration.model.Incident;


public interface IncidentDAO {

	public List<Incident> getAllIncidents();

	public Incident getIncidentById(String incidentId);

	public void saveOrUpdateIncident(Incident incident);


}
