package com.dbs.redtrackadmin.integration.model;

import java.util.List;

import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO;

public class ApplicationStatusFormBean {


	
	private String newappdtextid;
	
	 private String appid;
	  
		private List<String> countries;
		
		 private List<String> businessUnits;
		 
		 private List<String> status;
		 
		

			private String countryCode;

			
			private String unitId;
			
			List<ApplicationStatusDTO> applicationStatusList;
			
			
		

		public List<ApplicationStatusDTO> getApplicationStatusList() {
				return applicationStatusList;
			}

			public void setApplicationStatusList(
					List<ApplicationStatusDTO> applicationStatusList) {
				this.applicationStatusList = applicationStatusList;
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

		public List<String> getStatus() {
			return status;
		}

		public void setStatus(List<String> status) {
			this.status = status;
		}

		public String getNewappdtextid() {
			return newappdtextid;
		}

		public void setNewappdtextid(String newappdtextid) {
			this.newappdtextid = newappdtextid;
		}

		public String getAppid() {
			return appid;
		}

		public void setAppid(String appid) {
			this.appid = appid;
		}

		public List<String> getCountries() {
			return countries;
		}

		public void setCountries(List<String> countries) {
			this.countries = countries;
		}

		public List<String> getBusinessUnits() {
			return businessUnits;
		}

		public void setBusinessUnits(List<String> businessUnits) {
			this.businessUnits = businessUnits;
		}   
	
	

}
