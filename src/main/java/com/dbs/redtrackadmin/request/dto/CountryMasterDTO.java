package com.dbs.redtrackadmin.request.dto;
/**
 * 
 * @author Imran
 *
 */
import java.util.Date;


public class CountryMasterDTO {
	

	private String countryCode;
	

	private String countryDescription;


	private Date dateCreated;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryDescription() {
		return countryDescription;
	}

	public void setCountryDescription(String countryDescription) {
		this.countryDescription = countryDescription;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	

	

	
	
}


 