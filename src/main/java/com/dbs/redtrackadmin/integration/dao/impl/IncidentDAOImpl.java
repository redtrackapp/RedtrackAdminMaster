package com.dbs.redtrackadmin.integration.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.dbs.redtrackadmin.integration.dao.IncidentDAO;
import com.dbs.redtrackadmin.integration.model.Incident;



public class IncidentDAOImpl implements IncidentDAO {

	private JdbcTemplate jdbcTemplate;
	
	
	 // JdbcTemplate setter
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }
	/*public ContactDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
*/
	

	@Override
	public void saveOrUpdateIncident(Incident incident) {
		System.out.println("incident id is *******saveOrUpdateIncident************** "+incident.getIncidentId());
		Incident in=getIncidentById(incident.getIncidentId());
		if (in!=null) {
			// update
			System.out.println("in DAO saveOrUpdateIncident*********************saveOrUpdateIncident**********************  if start ");
			String sql = "UPDATE incident SET INCIDENT_TITLE=?, INCIDENT_DESC=?, INCIDENT_RESOLUTION=?,INCIDENT_THREAT_SEVERITY=?, INCIDENT_CATEGORY=?, USEROTP_ID=?,INCIDENT_CONF_NUM=?, INCIDENT_PARTICIPANT_CODE=?, USERDEVICE_TOKENSTR=?,DATE_CREATED=?, DATE_UPDATED=?, UPDATED_BY=?, "
						+ "STATUS=?,INCIDENT_STATUS=? WHERE INCIDENT_ID=?";
			jdbcTemplate.update(sql, incident.getIncidenttitle(), incident.getIncidentdesc(),
					incident.getIncidentresolution(), incident.getIncidentthreatseverity(), incident.getIncidentcategory(),incident.getUserotpid(), incident.getIncidentconfnum(), incident.getIncidentparticipantcode(),incident.getUserdevictokenstr(), incident.getDateCreated(), incident.getDateUpdated(), incident.getUpdatedBy(),incident.getStatus(),incident.getIncidentStatus(), incident.getIncidentId());
			System.out.println("in DAO saveOrUpdateIncident*********************saveOrUpdateIncident********************** if end ");
		} else {
			// insert
			System.out.println("in DAO saveOrUpdateIncident*********************saveOrUpdateIncident**********************  else start ");
			System.out.println(" incident data ***********"+incident.getIncidentId()+incident.getIncidenttitle()+incident.getIncidentdesc()+
					incident.getIncidentresolution()+incident.getIncidentthreatseverity()+incident.getIncidentcategory()+incident.getUserotpid()+incident.getIncidentconfnum()+ incident.getIncidentparticipantcode()+incident.getUserdevictokenstr()+incident.getDateCreated()+ incident.getDateUpdated()+incident.getUpdatedBy()+incident.getStatus()+incident.getIncidentStatus());
			
			String sql = "INSERT INTO incident (INCIDENT_ID,INCIDENT_TITLE, INCIDENT_DESC, INCIDENT_RESOLUTION, INCIDENT_THREAT_SEVERITY,INCIDENT_CATEGORY,USEROTP_ID,INCIDENT_CONF_NUM,INCIDENT_PARTICIPANT_CODE,USERDEVICE_TOKENSTR,DATE_CREATED,DATE_UPDATED,UPDATED_BY,STATUS,INCIDENT_STATUS)"
						+ " VALUES (?,?,?, ?, ?, ?,?, ?, ?, ?,?, ?, ?, ?,?)";
			jdbcTemplate.update(sql, incident.getIncidentId(),incident.getIncidenttitle(), incident.getIncidentdesc(),
					incident.getIncidentresolution(), incident.getIncidentthreatseverity(), incident.getIncidentcategory(),incident.getUserotpid(), incident.getIncidentconfnum(), incident.getIncidentparticipantcode(),incident.getUserdevictokenstr(), incident.getDateCreated(), incident.getDateUpdated(), incident.getUpdatedBy(),incident.getStatus(),incident.getIncidentStatus());
			System.out.println("in DAO saveOrUpdateIncident*********************saveOrUpdateIncident********************** else end ");
			
		}
		
	}

	
	
	
	@Override
	public List<Incident> getAllIncidents() {
		String sql = "SELECT * FROM incident";
		List<Incident> listofIncidents = jdbcTemplate.query(sql, new RowMapper<Incident>() {

			@Override
			public Incident mapRow(ResultSet rs, int rowNum) throws SQLException {
				Incident aIncident = new Incident();
	
				aIncident.setIncidentId(rs.getString("INCIDENT_ID"));
				aIncident.setIncidenttitle(rs.getString("INCIDENT_TITLE"));
				aIncident.setIncidentdesc(rs.getString("INCIDENT_DESC"));
				aIncident.setIncidentresolution(rs.getString("INCIDENT_RESOLUTION"));
				aIncident.setIncidentthreatseverity(rs.getString("INCIDENT_THREAT_SEVERITY"));
			aIncident.setIncidentcategory(rs.getString("INCIDENT_CATEGORY"));
			aIncident.setUserotpid(rs.getLong("USEROTP_ID"));
			aIncident.setIncidentconfnum(rs.getString("INCIDENT_CONF_NUM"));
			aIncident.setIncidentparticipantcode(rs.getString("INCIDENT_PARTICIPANT_CODE"));
			aIncident.setUserdevictokenstr(rs.getString("USERDEVICE_TOKENSTR"));
		//	System.out.println("i n DAO**************************___________*********************Start");
		aIncident.setDateCreated(rs.getString("DATE_CREATED"));
		aIncident.setDateUpdated(rs.getString("DATE_UPDATED"));
		// System.out.println("i n DAO**************************___________******************End ***");
			aIncident.setUpdatedBy(rs.getString("UPDATED_BY"));
			aIncident.setStatus(rs.getString("STATUS"));
			aIncident.setIncidentStatus(rs.getString("INCIDENT_STATUS"));
				
			return aIncident;
			}
			
		});
	
		return listofIncidents;
	}
	
	
	


		

	/*@Override
	public Incident getIncidentById(String incidentId) {
		Incident incident=null;
		try {
		String sql = "SELECT * FROM incident WHERE INCIDENT_ID="+incidentId;
		incident=jdbcTemplate.query(sql, new ResultSetExtractor<Incident>() {

			@Override
			public Incident extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Incident aIncident = new Incident();
					
					aIncident.setIncidentId(rs.getString("INCIDENT_ID"));
					aIncident.setIncidenttitle(rs.getString("INCIDENT_TITLE"));
					aIncident.setIncidentdesc(rs.getString("INCIDENT_DESC"));
					aIncident.setIncidentresolution(rs.getString("INCIDENT_RESOLUTION"));
					aIncident.setIncidentthreatseverity(rs.getString("INCIDENT_THREAT_SEVERITY"));
				aIncident.setIncidentcategory(rs.getString("INCIDENT_CATEGORY"));
				aIncident.setUserotpid(rs.getLong("USEROTP_ID"));
				aIncident.setIncidentconfnum(rs.getString("INCIDENT_CONF_NUM"));
				aIncident.setIncidentparticipantcode(rs.getString("INCIDENT_PARTICIPANT_CODE"));
				aIncident.setUserdevictokenstr(rs.getString("USERDEVICE_TOKENSTR"));
				System.out.println("i n DAO**************************___________*********************Start");
			aIncident.setDateCreated(rs.getString("DATE_CREATED"));
			aIncident.setDateUpdated(rs.getString("DATE_UPDATED"));
			System.out.println("i n DAO**************************___________******************End ***");
				aIncident.setUpdatedBy(rs.getString("UPDATED_BY"));
				aIncident.setStatus(rs.getString("STATUS"));
				aIncident.setIncidentStatus(rs.getString("INCIDENT_STATUS"));
					
				return aIncident;
				}
				
				return null;
			}
		
			
		});
		} catch (EmptyResultDataAccessException  ex) {
			System.out.println("no record found: " + ex.getMessage());
		} catch (Exception  ex) {
			System.out.println("Exception: " + ex.getMessage());
		}    
		  return incident;
	}*/
	
	public Incident getIncidentById(String incidentId) {
		System.out.println("i n getIncidentById**************************___________*********************Start");
		String sql = "SELECT * FROM incident WHERE INCIDENT_ID="+incidentId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Incident>() {

			@Override
			public Incident extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Incident aIncident = new Incident();
					
					aIncident.setIncidentId(rs.getString("INCIDENT_ID"));
					aIncident.setIncidenttitle(rs.getString("INCIDENT_TITLE"));
					aIncident.setIncidentdesc(rs.getString("INCIDENT_DESC"));
					aIncident.setIncidentresolution(rs.getString("INCIDENT_RESOLUTION"));
					aIncident.setIncidentthreatseverity(rs.getString("INCIDENT_THREAT_SEVERITY"));
				aIncident.setIncidentcategory(rs.getString("INCIDENT_CATEGORY"));
				aIncident.setUserotpid(rs.getLong("USEROTP_ID"));
				aIncident.setIncidentconfnum(rs.getString("INCIDENT_CONF_NUM"));
				aIncident.setIncidentparticipantcode(rs.getString("INCIDENT_PARTICIPANT_CODE"));
				aIncident.setUserdevictokenstr(rs.getString("USERDEVICE_TOKENSTR"));
				System.out.println("i n DAO**************************___________*********************Start");
			aIncident.setDateCreated(rs.getString("DATE_CREATED"));
			aIncident.setDateUpdated(rs.getString("DATE_UPDATED"));
			System.out.println("i n DAO**************************___________******************End ***");
				aIncident.setUpdatedBy(rs.getString("UPDATED_BY"));
				aIncident.setStatus(rs.getString("STATUS"));
				aIncident.setIncidentStatus(rs.getString("INCIDENT_STATUS"));
					
				return aIncident;
				}
				
				return null;
			}
		
			
		});
		
	}

}
