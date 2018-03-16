/**
 * 
 */
package com.dbs.redtrackadmin.business.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.dbs.redtrackadmin.business.service.AdminViewService2;

@Service
public class AdminViewServiceImpl2 implements AdminViewService2 {
	private static final Logger logger = Logger.getLogger(AdminViewServiceImpl2.class);
 
	@Override
	public Map<String, String> getlistofApps(){
		 
    	Map<String, String> listofapptypes = new HashMap<String, String>();
    	listofapptypes.put("appid1", "App Desc");
    	listofapptypes.put("appid2", "App Desc2");
    	listofapptypes.put("appid3", "App Desc3");
    	listofapptypes.put("appid4", "App Desc4");
    	listofapptypes.put("appid5", "App Desc5");
    	listofapptypes.put("appid6", "App Desc6");
    	listofapptypes.put("appid7", "App Desc7");
    	listofapptypes.put("appid8", "App Desc8");

    	return listofapptypes;
	}
 
	
	 
	
}
