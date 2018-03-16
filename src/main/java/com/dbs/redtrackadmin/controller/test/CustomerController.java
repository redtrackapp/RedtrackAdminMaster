package com.dbs.redtrackadmin.controller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.redtrackadmin.business.service.AdminViewService;
import com.dbs.redtrackadmin.business.service.AdminViewService2;
import com.dbs.redtrackadmin.request.dto.ApplicationStatusDTO;
 

@Controller
public class CustomerController {

  @Autowired
  AdminViewService adminviewService;
  
  @Autowired
  AdminViewService2 adminviewService2;
    
    @RequestMapping("/test")
    public ModelAndView test() {
        ModelAndView modelAndView = new ModelAndView("hellopage");
        modelAndView.getModel().put("message", "hello Raymond!");
        return modelAndView; 
    }
     
    
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String handleRequest(Model model){
    	   //arbitrary handling code
    	Map<String, String> listofapptypes = new HashMap<String, String>();
    	Map<String, String> listofcountries = new HashMap<String, String>();
    	Map<String, String> listofbizunits = new HashMap<String, String>();
    	listofapptypes.put("appid1", "App Desc");
    	listofapptypes.put("appid2", "App Desc2");
    	listofapptypes.put("appid3", "App Desc3");
    	listofapptypes = adminviewService.getlistofApps();
    	
    	List<ApplicationStatusDTO> test = adminviewService.getApplicationStatusListById("APP_DIG_322");
    	System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    	for(ApplicationStatusDTO temp: test) {
    		System.out.println("appId: "+ temp.getAppId());
    		System.out.println("getCountryCode: "+ temp.getCountryCode());
    		System.out.println("getUnitId: "+ temp.getUnitId());
    	}
    	 

    	listofcountries.put("countryCd1", "Country Desc");
    	listofcountries.put("countryCd2", "Country Desc2");
    	listofcountries.put("countryCd3", "Country Desc3");

    	listofbizunits.put("bizid1", "Biz Desc");
    	listofbizunits.put("bizid2", "Biz Desc2");
    	listofbizunits.put("bizid3", "Biz Desc3");

    	
    	   model.addAttribute("listofapptypes", listofapptypes);
    	   model.addAttribute("listofcountries", listofcountries);
    	   model.addAttribute("listofbizunits", listofbizunits);
    	   
    	   //etc
    	   return "index";
    	 }
    

    @RequestMapping(value = "/customer1", method = RequestMethod.GET)
    public String handleReques2t(Model model){
    	   //arbitrary handling code
    	Map<String, String> listofapptypes = new HashMap<String, String>();
    	Map<String, String> listofcountries = new HashMap<String, String>();
    	Map<String, String> listofbizunits = new HashMap<String, String>();
    	listofapptypes.put("appid1", "App Desc");
    	listofapptypes.put("appid2", "App Desc2");
    	listofapptypes.put("appid3", "App Desc3");

    	listofapptypes = adminviewService2.getlistofApps();
    	
    	listofcountries.put("countryCd1", "Country Desc");
    	listofcountries.put("countryCd2", "Country Desc2");
    	listofcountries.put("countryCd3", "Country Desc3");

    	listofbizunits.put("bizid1", "Biz Desc");
    	listofbizunits.put("bizid2", "Biz Desc2");
    	listofbizunits.put("bizid3", "Biz Desc3");

    	
    	   model.addAttribute("listofapptypes", listofapptypes);
    	   model.addAttribute("listofcountries", listofcountries);
    	   model.addAttribute("listofbizunits", listofbizunits);
    	   
    	   //etc
    	   return "index";
    	 }
    
    
     

}
