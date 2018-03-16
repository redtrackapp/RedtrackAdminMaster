package com.dbs.redtrackadmin.request.dto;



public class ApplicationStatusDTO  {
	
		
	private String appId;
	
	private String applicationName;
	
	private String countryCode;

	
	private String unitId;
	

	private String status;
	private String appComments;
	
	

	public String getAppComments() {
		return appComments;
	}

	public void setAppComments(String appComments) {
		this.appComments = appComments;
	}

	

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	
	
}


 