/**
 * 
 */
package com.dbs.redtrackadmin.business.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbs.redtrackadmin.business.service.AdminViewService;
import com.dbs.redtrackadmin.controller.NoteClass;
import com.dbs.redtrackadmin.integration.dao.AdminViewDAO;
import com.dbs.redtrackadmin.integration.dao.UserRegistrationDAO;
import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO;
import com.dbs.redtrackadmin.request.dto.ApplicationsDTO;
import com.dbs.redtrackadmin.request.dto.BusinessUnitsDTO;
import com.dbs.redtrackadmin.request.dto.CountryMasterDTO;
import com.dbs.redtrackadmin.request.dto.DeviceTokenHelper;

@Service
public class AdminViewServiceImpl implements AdminViewService {
	private static final Logger logger = Logger.getLogger(AdminViewServiceImpl.class);

	@Autowired
	AdminViewDAO adminViewDAO;
	@Autowired
	UserRegistrationDAO  userRegistrationDAO;
	@Autowired
	ServletContext context;
	 
	@Override
	public Map<String, String> getlistofApps(){
		return adminViewDAO.getlistofApps();
	}

	@Override
	public List<ApplicationsDTO> getApplicationList() {
		return this.adminViewDAO.getApplicationList();
	}
	
	@Override
	public List<BusinessUnitsDTO> getBusinessUnitList() {
		
		return this.adminViewDAO.getBusinessUnitList();
	}

	@Override
	public List<CountryMasterDTO> getCountryList() {
		
		return this.adminViewDAO.getCountryList();
	}

	@Override
	public String submitapplicationFormBean(String appid, String country, String businessUnit) {
		
		  String form=adminViewDAO.submitapplicationFormBean(appid,country,businessUnit);
		return form;
	}

	@Override
	public List<ApplicationStatusDTO> getApplicationStatusList() {
		return this.adminViewDAO.getApplicationStatusList();
	}

	@Override
	public String findandRemoveApplicationStatusById(String appid) {
		return this.adminViewDAO.findandRemoveApplicationStatusById(appid);
	}

	@Override
	public List<ApplicationStatusDTO> getApplicationStatusListById(String appid) {
		return this.adminViewDAO.getApplicationStatusListById(appid);
	}

	@Override
	public String addNewapplicationForm(String appId, String applicationName, String appCode, String appActive, String countryCode) {
		return this.adminViewDAO.addNewapplicationForm(appId,applicationName,appCode,appActive);
	}

	@Override
	public String saveApplicaitonStatusForm(String appid, String country, String businessUnit,
			String status,String appComments) {
		return this.adminViewDAO.saveApplicaitonStatusForm(appid,country,businessUnit,status,appComments);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void sendAppStatusNotification(Map<String, String> notifyMsgMap){
		logger.info("AdminViewServiceImpl : sendAppStatusNotification : start *******>>>" + notifyMsgMap);
		//***********  start push notification **********
				//User user = findUserByID(createIncidentRequestDTO.getUserotpid());//to be used for push notification
				//logger.info("IncidentServiceImpl :createIncident:Start User(" + user+")");	
				
				//String userID = String.valueOf(user.getId());
				//List<String> userTokenList = getUserTokenStringList(userID); //returns ios/android token string
		        //		
				Map tokenMap = getTokenStringList();
				
				List<String> iosList = (List<String>) tokenMap.get("iosList");
				List<String> androidList = (List<String>) tokenMap.get("androidList");
				//incidentTemp.setUserDetail(user);
				
				//Start sending push notification to IOS
					logger.info("TokenList IOS :(" +iosList+ ")");
					try {
					pushMessage(iosList, notifyMsgMap);
				} catch (Exception ex) {
					logger.info("Unable to send push notification to IOS device(" + ex.getMessage() + ")");
				}
			
				//Start sending push notification to Andorid
					logger.info("TokenList Android : (" +androidList+ ")");		
				try {
					NoteClass aa=new NoteClass();
					aa.sendAndroid(androidList,notifyMsgMap);
				} catch(Exception ex) {
					logger.info("Unable to send push notification to Adnroid device(" + ex.getMessage() + ")");
				}
			
				logger.info("AdminViewServiceImpl : sendAppStatusNotification : End " );
		
	}
	

	//Helper Method for notification
	public void pushMessage(List<String> tokenlist, Map<String, String> notifyMsgMap) {
		//    	notificationMsg="CBG Health Check - All applications are fully operational.";
		try	{
			if(tokenlist!=null && tokenlist.size()>0) {
					String certsrootpath = context.getRealPath("certspath");
					certsrootpath=certsrootpath+File.separator+"LifestyleUAT29052017(1).p12";
					logger.info("The new complete path is ==> "+certsrootpath);
					NoteClass aa=new NoteClass();
					aa.sendnotes(certsrootpath, tokenlist, notifyMsgMap);
			}
		}catch(Exception e) {
			//e.printStackTrace();
			e.getMessage();
			//throw new RedTrackProcessingException("0004", "Unable to send push notification. please check with the admin.");
		}    	
    }
	
	
	
	private Map<String, List<String>> getTokenStringList() {
		
		Map<String, List<String>> tokenMap = new HashMap<String, List<String>>();		
		List<String> iosList = new ArrayList<String>();
		List<String> androidList = new ArrayList<String>();
		
		List<DeviceTokenHelper> deviceTokenList = getUserTokenStringList();		
				
		for (DeviceTokenHelper deviceToken : deviceTokenList) {
			if (null!=deviceToken && null!=deviceToken.getDeviceTokenString()) {
				//TODO: Tmp condition for phone number need to remove.
				//logger.info("deviceToken.getDevicePlatform() : " +deviceToken.getDevicePlatform()+ "  deviceToken.getPhoneNumber() : " + deviceToken.getPhoneNumber());
				if (deviceToken.getDevicePlatform() == 1 && deviceToken.getPhoneNumber().equalsIgnoreCase("91082953") ) {
				//if (deviceToken.getDevicePlatform() == 1 ) {
					iosList.add(deviceToken.getDeviceTokenString());
				//} else if (deviceToken.getDevicePlatform() == 0 && deviceToken.getPhoneNumber().equalsIgnoreCase("91499614")) {
				} else if (deviceToken.getDevicePlatform() == 0 ) {
					androidList.add(deviceToken.getDeviceTokenString());
				}
			}
		}
		
		tokenMap.put("iosList", iosList);
		tokenMap.put("androidList", androidList);
		
		return tokenMap;
	}
	

	/*
	 * comments/changes: DAO Needs to be change
	 * 					 
	 * */
	private List<DeviceTokenHelper> getUserTokenStringList() {
		
		logger.info("AdminViewServiceImpl : getUserTokenStringList : start");
	
		List<DeviceTokenHelper> userTokenList  = userRegistrationDAO.getDeviceTokenStringList();
		
		logger.info("AdminViewServiceImpl : getUserTokenStringList : end" );
		return userTokenList;
	}	
}
