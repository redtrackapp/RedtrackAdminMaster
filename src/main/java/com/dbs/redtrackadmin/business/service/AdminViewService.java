/**
 * 
 */
package com.dbs.redtrackadmin.business.service;

import java.util.List;
import java.util.Map;

import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO;
import com.dbs.redtrackadmin.request.dto.ApplicationsDTO;
import com.dbs.redtrackadmin.request.dto.BusinessUnitsDTO;
import com.dbs.redtrackadmin.request.dto.CountryMasterDTO;

public interface AdminViewService {
	
	public List<ApplicationsDTO> getApplicationList() ;


	public List<BusinessUnitsDTO> getBusinessUnitList();


	public List<CountryMasterDTO> getCountryList();


	public String submitapplicationFormBean(String appid, String country, String businessUnit);
	
	public List<ApplicationStatusDTO> getApplicationStatusList();
	
	public List<ApplicationStatusDTO> getApplicationStatusListById(String appid);
	
	public String findandRemoveApplicationStatusById(String appid);


	public String addNewapplicationForm(String appId, String applicationName, String appCode, String appActive, String countryCode);


/*	String saveApplicaitonStatusForm(String appid, String country, String businessUnit, String status);*/
	public String saveApplicaitonStatusForm(String appid, String country, String businessUnit, String status,String appComments);


	public Map<String, String> getlistofApps();
	
	public void sendAppStatusNotification(Map<String, String> notifyMsgMap);
}
