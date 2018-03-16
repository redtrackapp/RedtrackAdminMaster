package com.dbs.redtrackadmin.integration.model;

import java.util.List;

import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO;

public class ApplicationStatusFormBean1 {

		List<ApplicationStatusDTO> applicationStatusList;
		List<ApplicationStatusDTO> applicationStatusList2;
		String justification;

		public List<ApplicationStatusDTO> getApplicationStatusList() {
				return applicationStatusList;
		}

		public void setApplicationStatusList(List<ApplicationStatusDTO> applicationStatusList) {
			this.applicationStatusList = applicationStatusList;
		}

		public List<ApplicationStatusDTO> getApplicationStatusList2() {
			return applicationStatusList2;
		}

		public void setApplicationStatusList2(List<ApplicationStatusDTO> applicationStatusList2) {
			this.applicationStatusList2 = applicationStatusList2;
		}

		public String getJustification() {
			return justification;
		}

		public void setJustification(String justification) {
			this.justification = justification;
		}
		
		

}
