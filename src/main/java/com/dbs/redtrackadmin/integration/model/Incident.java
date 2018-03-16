package com.dbs.redtrackadmin.integration.model;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
 

public class Incident {
	
	


	private String incidentId;

	private String incidenttitle;
	

	private String incidentdesc;
	
	
	private String incidentconfnum;
	
	
	private String incidentparticipantcode;
	
	
	private String incidentresolution;

	
	private String incidentthreatseverity;
	
	
	private String incidentcategory;
	
	
	private Long userotpid;
	

	private String userdevictokenstr;
	
	
	private String dateCreated;

	
	private String updatedBy;
	
	
	private String dateUpdated;
	 
	
	private String status;
	
	
	private String incidentStatus;
	
	
	public Incident() {
	}
 

	public Incident(String incidentId, String incidenttitle, String incidentdesc,
			String incidentconfnum, String incidentparticipantcode,
			String incidentresolution, String incidentthreatseverity,
			String incidentcategory, Long userotpid, String userdevictokenstr,
			String dateCreated, String updatedBy, String dateUpdated, String status,
			String incidentStatus) {
		//super();
		this.incidentId = incidentId;
		this.incidenttitle = incidenttitle;
		this.incidentdesc = incidentdesc;
		this.incidentconfnum = incidentconfnum;
		this.incidentparticipantcode = incidentparticipantcode;
		this.incidentresolution = incidentresolution;
		this.incidentthreatseverity = incidentthreatseverity;
		this.incidentcategory = incidentcategory;
		this.userotpid = userotpid;
		this.userdevictokenstr = userdevictokenstr;
		this.dateCreated = dateCreated;
		this.updatedBy = updatedBy;
		this.dateUpdated = dateUpdated;
		this.status = status;
		this.incidentStatus = incidentStatus;
	}

	public String getIncidentStatus() {
		return incidentStatus;
	}

	public void setIncidentStatus(String incidentStatus) {
		this.incidentStatus = incidentStatus;
	}



	public String getIncidentId() {
		return incidentId;
	}

	public void setIncidentId(String string) {
		this.incidentId = string;
	}

	public String getIncidenttitle() {
		return incidenttitle;
	}

	public void setIncidenttitle(String incidenttitle) {
		this.incidenttitle = incidenttitle;
	}

	public String getIncidentdesc() {
		return incidentdesc;
	}

	public void setIncidentdesc(String incidentdesc) {
		this.incidentdesc = incidentdesc;
	}

	public String getIncidentconfnum() {
		return incidentconfnum;
	}

	public void setIncidentconfnum(String incidentconfnum) {
		this.incidentconfnum = incidentconfnum;
	}

	public String getIncidentparticipantcode() {
		return incidentparticipantcode;
	}

	public void setIncidentparticipantcode(String incidentparticipantcode) {
		this.incidentparticipantcode = incidentparticipantcode;
	}

	public String getIncidentresolution() {
		return incidentresolution;
	}

	public void setIncidentresolution(String incidentresolution) {
		this.incidentresolution = incidentresolution;
	}

	public String getIncidentthreatseverity() {
		return incidentthreatseverity;
	}

	public void setIncidentthreatseverity(String incidentthreatseverity) {
		this.incidentthreatseverity = incidentthreatseverity;
	}

	public String getIncidentcategory() {
		return incidentcategory;
	}

	public void setIncidentcategory(String incidentcategory) {
		this.incidentcategory = incidentcategory;
	}
 

	public String getUserdevictokenstr() {
		return userdevictokenstr;
	}

	public void setUserdevictokenstr(String userdevictokenstr) {
		this.userdevictokenstr = userdevictokenstr;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
 

	public String getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(String dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	public Long getUserotpid() {
		return userotpid;
	}

	public void setUserotpid(Long userotpid) {
		this.userotpid = userotpid;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String string) {
		this.updatedBy = string;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}


 