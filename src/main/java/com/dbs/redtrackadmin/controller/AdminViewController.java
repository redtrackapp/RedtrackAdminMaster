/**
 * 
 * @author Imran
 *
 */
package com.dbs.redtrackadmin.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.redtrackadmin.business.service.AdminViewService;
import com.dbs.redtrackadmin.integration.model.ApplicationStatusFormBean;
import com.dbs.redtrackadmin.integration.model.ApplicationStatusFormBean1;
import com.dbs.redtrackadmin.integration.model.NewApplicationFormBean;
import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO;
import com.dbs.redtrackadmin.request.dto.ApplicationsDTO;
import com.dbs.redtrackadmin.request.dto.BusinessUnitsDTO;
import com.dbs.redtrackadmin.request.dto.CountryMasterDTO;

/**
 * @author IBM
 * 
 *         Controller to manage admin views
 * 
 */
@Controller
public class AdminViewController {

	private static final Logger logger = Logger
			.getLogger(AdminViewController.class);
 
	@Autowired
	ServletContext context;

	@Autowired
	AdminViewService adminViewService; 
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView viewAddUpdateApplicationRoot()  {
		logger.info("start AdminViewController :: viewAddUpdateApplications :: start WEB >>>>>> ");
		setContext();
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/adminModule", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView viewAddUpdateApplications()  {
		logger.info("start AdminViewController :: viewAddUpdateApplications :: start WEB >>>>>> ");
		setContext();
		return new ModelAndView("index");
	}
	
	
	@RequestMapping(value ="/addNewApplicaiton", method = RequestMethod.GET)
	public @ResponseBody
	ModelAndView addNewApplicaiton() {
		logger.info("start AdminViewController :: addNewApplicaiton :: start WEB >>>>>> ");
		setContext();
		return new ModelAndView("addNewApplication");
	}
	
	
	
	
	@RequestMapping(value ="/updateApplicaitonStatus", method = RequestMethod.GET)
	public @ResponseBody ModelAndView updateApplicaitonStatus() {
		logger.info("start AdminViewController :: updateApplicaitonStatus :: start WEB >>>>>> ");
		setContext();
		 List<ApplicationStatusDTO> applicationStatusList = adminViewService.getApplicationStatusList();	
		  
		 context.setAttribute("applicationStatusList", applicationStatusList);
		 context.setAttribute("applicationStatusList2", applicationStatusList);	 
		 ApplicationStatusFormBean1 applicationStatusFormBean1 = new ApplicationStatusFormBean1();
		 applicationStatusFormBean1.setApplicationStatusList(applicationStatusList);
		 applicationStatusFormBean1.setApplicationStatusList2(applicationStatusList);
		 
//		  for(ApplicationStatusDTO str:applicationStatusList){
//			logger.info(">>>APPID: "+str.getAppId() + " AppName: " + str.getApplicationName() + " Status: " +str.getStatus());
//		  }
		 
		 logger.info("start AdminViewController :: updateApplicaitonStatus :: End >>>>>> ");
		 return new ModelAndView("appstatus2", "applicationStatusFormBean1", applicationStatusFormBean1);
	
	}

	@SuppressWarnings("unused")
	public void setContext() {

		logger.info("start AdminViewController :: setContext :: start");

		JSONArray jsonarrobj = new JSONArray();
		JSONArray udidsArrObj = null;
		try {
			
			jsonarrobj = getJsonArray();
			// List of application types
			context.setAttribute("listofapptypes", jsonarrobj.get(0));
			// List of Countries
			context.setAttribute("listofcountries", jsonarrobj.get(1));
			// List of Business Units
			context.setAttribute("listofbizunits", jsonarrobj.get(2));
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		logger.info("END AdminViewController :: setContext :: END");
	}

	@SuppressWarnings("unchecked")
	public JSONArray getJsonArray() {

		logger.info("start AdminViewController :: getJsonArray :: start");

		JSONArray jsonarrobj = new JSONArray();

		// TODO:List Of Countries need to fill with DAO
		Map<String, String> listofcountries = new HashMap<>();
		List<CountryMasterDTO> countryList = adminViewService.getCountryList();
		
		 for(CountryMasterDTO str:countryList){
				listofcountries.put(str.getCountryCode(),str.getCountryDescription());
		  }
	
		
		// TODO:List Of business units need fill with DAO
		Map<String, String> listofbizunits = new HashMap<>();
		
		List<BusinessUnitsDTO> businessUnitList = adminViewService.getBusinessUnitList();
		
		  for(BusinessUnitsDTO str:businessUnitList){
			  listofbizunits.put(str.getUnitId(),str.getDescription());
		  }
	

		// TODO:List Of application types need to fill with DAO
		Map<String, String> listofapptypes = new HashMap<>();
		
		List<ApplicationsDTO> applicationList = adminViewService.getApplicationList();
			
		for(ApplicationsDTO str:applicationList){
			listofapptypes.put(str.getAppId(), str.getApplicationName());
		}

		
		jsonarrobj.add(listofapptypes);
		jsonarrobj.add(listofcountries);
		jsonarrobj.add(listofbizunits);

		logger.info("start AdminViewController :: getJsonArray :: start");
		return jsonarrobj;

	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/submitapplicationFormBean",method = RequestMethod.POST)
	public ModelAndView submitapplicationFormBean(Model model, ApplicationStatusFormBean applicationStatusFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response){
		logger.info("start AdminViewController :: submitapplicationFormBean :: start WEB >>>>>> ");
		
		  String appid=applicationStatusFormBean.getAppid();
		    
		 List<String> countriesList =  applicationStatusFormBean.getCountries();
		 
	  List<String> businessUnitsList =  applicationStatusFormBean.getBusinessUnits();
	  String form=""; 
	  String StatusRemove=adminViewService.findandRemoveApplicationStatusById(appid);
 
			  for(String country:countriesList){
				  for(String businessUnit:businessUnitsList){
					  form=adminViewService.submitapplicationFormBean(appid,country,businessUnit);
					  
				  }
			  }
		
			  context.setAttribute("selectedcountries", countriesList);
			  context.setAttribute("selectedBusinessunits", businessUnitsList);
			  context.setAttribute("appid", appid);
		logger.info("start AdminViewController :: submitapplicationFormBean :: End >>>>>> ");
		return new ModelAndView("index");
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/saveNewapplicationForm",method = RequestMethod.POST)
	public String saveNewapplicationForm(Model model, NewApplicationFormBean applicationFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response){
		logger.info("start AdminViewController :: saveNewapplicationForm :: start WEB >>>>>> ");
		String applicationName=applicationFormBean.getApplicationName();;
		  String appId=applicationFormBean.getAppId();
		  String appCode=applicationFormBean.getAppCode();
		  String appActive=applicationFormBean.getAppActive();
		  String countryCode=applicationFormBean.getCountryCode();
	
				String	 form=adminViewService.addNewapplicationForm(appId,applicationName,appCode,appActive,countryCode);
			  
		model.addAttribute("applicationFormBean", applicationFormBean);
		logger.info("start AdminViewController :: saveNewapplicationForm :: END >>>>>> ");
		return "successMember";
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/onchangeAppId",method = RequestMethod.POST)
	public ModelAndView onchangeAppId(Model model, ApplicationStatusFormBean applicationStatusFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response){
		logger.info("start AdminViewController :: onchangeAppId :: start WEB >>>>>> ");
		
		  String appid=applicationStatusFormBean.getAppid();
		  
		  String status="";
	  Set<String>  listofselectedcountries = new HashSet<>();
	  Set<String> listofselectedbizunits = new HashSet<>();
		List<ApplicationStatusDTO> applicationStatusList = adminViewService.getApplicationStatusListById(appid);
			
			  for(ApplicationStatusDTO str:applicationStatusList){
				
				  listofselectedcountries.add(str.getCountryCode());
				  listofselectedbizunits.add(str.getUnitId());
				 
			  }
	
	  context.setAttribute("selectedcountries", listofselectedcountries);
	  context.setAttribute("selectedBusinessunits", listofselectedbizunits);
	  context.setAttribute("appid", appid);
	  context.setAttribute("appid", appid);
	  logger.info("start AdminViewController :: onchangeAppId :: End >>>>>> ");
	  return new ModelAndView("index");
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/onchangeAppIdforStatus",method = RequestMethod.POST)
	public ModelAndView onchangeAppIdforStatus(Model model, ApplicationStatusFormBean applicationStatusFormBean,
			BindingResult result1,HttpServletRequest request,	HttpServletResponse response){
		logger.info("start AdminViewController :: onchangeAppIdforStatus :: start WEB >>>>>> ");
		
		  String appid=applicationStatusFormBean.getAppid();
		  
		  String status="";
	  Set<String>  listofselectedcountries = new HashSet<>();
	  Set<String> listofselectedbizunits = new HashSet<>();
	  Set<String> listofselectedstatus = new HashSet<>();
		List<ApplicationStatusDTO> applicationStatusList = adminViewService.getApplicationStatusListById(appid);
			
			  for(ApplicationStatusDTO str:applicationStatusList){
				 
				  listofselectedcountries.add(str.getCountryCode());
				  listofselectedbizunits.add(str.getUnitId());
				  listofselectedstatus.add(str.getStatus());
			  }
	  context.setAttribute("appid", appid);
	  context.setAttribute("applicationStatusList", applicationStatusList);
	  context.setAttribute("applicationStatusList2", applicationStatusList);
	  
	  ApplicationStatusFormBean1 applicationStatusFormBean1 = new ApplicationStatusFormBean1();
	  applicationStatusFormBean1.setApplicationStatusList(applicationStatusList);
		logger.info("start AdminViewController :: onchangeAppIdforStatus :: End >>>>>> ");
	  return new ModelAndView("updateApplicaitonStatus", "applicationStatusFormBean1", applicationStatusFormBean1);
	}
	

	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/saveApplicaitonStatusForm",method = RequestMethod.POST)
	public ModelAndView saveApplicaitonStatusForm(@ModelAttribute("applicationStatusFormBean1") ApplicationStatusFormBean1 applicationStatusFormBean1, HttpServletRequest request){
		logger.info("start AdminViewController :: saveApplicaitonStatusForm :: start WEB >>>>>> ");
		Map<String, String> notifyMsgMap = null ;
		List<ApplicationStatusDTO> applicationStatusDTOList = applicationStatusFormBean1.getApplicationStatusList();
		String appId = request.getParameter("appidhidden");

		List<ApplicationStatusDTO> applicationStatusDTOList2 = applicationStatusFormBean1.getApplicationStatusList2();
 
 		Map <String, String> mapper = new HashMap<String, String>();
		 for (ApplicationStatusDTO app: applicationStatusDTOList2) {
			 //logger.info("appid: " + app.getAppId()+ " app_name: "+ app.getApplicationName()+ " Status: " + app.getStatus());
			 mapper.put(app.getAppId(), app.getStatus());
			 
		 }
		 //logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ applicationStatusFormBean1.getJustification());
		 if(null != applicationStatusDTOList && applicationStatusDTOList.size() > 0) {
			 notifyMsgMap = new HashMap<String, String>();
			for (ApplicationStatusDTO applicationStatusDTO : applicationStatusDTOList) {
				if(mapper.containsKey(applicationStatusDTO.getAppId())) {
					if(!mapper.get(applicationStatusDTO.getAppId()).equals(applicationStatusDTO.getStatus())) {
						
						//this row changed: put to a new list that is going to be saved
						//todo: justification not yet saved into the database
						String form = adminViewService.saveApplicaitonStatusForm(applicationStatusDTO.getAppId(),applicationStatusDTO.getCountryCode(),applicationStatusDTO.getUnitId(),
								applicationStatusDTO.getStatus(),applicationStatusDTO.getAppComments());
						//Set Status based on number
						 if(null != applicationStatusDTO.getStatus()) {
								if(applicationStatusDTO.getStatus().equalsIgnoreCase("1")){
									applicationStatusDTO.setStatus("RED");
									
								}else if(applicationStatusDTO.getStatus().equalsIgnoreCase("2")){
									applicationStatusDTO.setStatus("YELLOW");
								}	    	
								else if(applicationStatusDTO.getStatus().equalsIgnoreCase("3")){
									applicationStatusDTO.setStatus("ORANGE");
								}
								else if(applicationStatusDTO.getStatus().equalsIgnoreCase("4")){
									applicationStatusDTO.setStatus("GREEN");
								}
				         	}
						
						//Put app status message for notification
//						notifyMsgMap.put(applicationStatusDTO.getAppId(), "Country: "+ applicationStatusDTO.getCountryCode()+"," + "Bussiness Unit: "+ applicationStatusDTO.getUnitId()+ "," +
//						"Status: "+ applicationStatusDTO.getStatus() + ","+ "Application: "+applicationStatusDTO.getApplicationName());
							notifyMsgMap.put(applicationStatusDTO.getAppId(), //"Country: "+ applicationStatusDTO.getCountryCode()+"," + 
//									"Application: "+applicationStatusDTO.getApplicationName() + 
//									","+ 
//									"Status: "+ applicationStatusDTO.getStatus()
									""
									+ ","+ applicationStatusFormBean1.getJustification()
									);
						
					}
				}
			}
						
			//Send Push notification for applications status update
			adminViewService.sendAppStatusNotification(notifyMsgMap);
			
		 }
		logger.info("start AdminViewController :: saveApplicaitonStatusForm :: End>>>>>> ");
		return new ModelAndView("redirect:/updateApplicaitonStatus");
	}
}
