package com.dbs.redtrackadmin.controller;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javapns.Push;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

public class NoteClass {

	private static final Logger logger = Logger.getLogger(NoteClass.class);
	
		@SuppressWarnings("rawtypes")
		public void sendnotes(String pathtoCerts, List tokenlist,Map<String, String> notifyMsgMap)
		{
			logger.info("sendnotes : iOS : start");
			String notificationMsg ="";
			if(null!=notifyMsgMap)
			{
				Iterator it = notifyMsgMap.entrySet().iterator();
//				notificationMsg="Application Status  has been updated for :\n";
			    while (it.hasNext()) {
			        Map.Entry pair = (Map.Entry)it.next();
			        System.out.println("KEY > "+ pair.getKey() + " VAlue > " + pair.getValue());
			        
			        StringTokenizer st = new StringTokenizer(pair.getValue().toString()); 
			       // notifyMsgMap.put(applicationStatusDTO.getAppId(), applicationStatusDTO.getCountryCode()+"," +applicationStatusDTO.getUnitId()+ "," + applicationStatusDTO.getStatus() + ","+applicationStatusDTO.getApplicationName());
			        while (st.hasMoreTokens()) {  
			         // printing next token  
			          notificationMsg=notificationMsg+st.nextToken(",") + "\n";
			        }
			        if (it.hasNext())
			        	notificationMsg=notificationMsg +"\n";			
			     }   
			     
				
			System.out.println(" ios Text Message >>>>*****  " + notificationMsg);
			}
			try
			{
				BasicConfigurator.configure();
			    try 
			    {
				        PushNotificationPayload payload = PushNotificationPayload.complex();
				        payload.addAlert(notificationMsg);
				        //payload.addBadge(1);
				        payload.addSound("default");
				        payload.addCustomDictionary("id", "1");
				        System.out.println(payload.toString());
				        System.out.println(" ios Text payload Message >>>>*****  " + payload.toString());
				        /*
				        ArrayList listObj=new ArrayList();
				    	listObj.add("b8d7e414194b74c5a2dd88cf4d39420a2a6440f79d710b4de12b71cf319cf8d2");
				    	listObj.add("afeb05ecc2cc71ada44045c7234459f1126a6ff233866b1aeef9d1880c8da426");
				    	listObj.add("e68f28f01d57fadd156ac011ca74ee7be4c2da6a53cfa4ced39083f3eea1eca7");
				    	*/
//				        List<PushedNotification> NOTIFICATIONS = Push.payload(payload, pathtoCerts, "redtrack", true, tokenlist);
				        List<PushedNotification> NOTIFICATIONS = Push.payload(payload, pathtoCerts, "123456", true, tokenlist);
			    } 
			    catch(Exception e) 
			    {
			        e.printStackTrace();
			    }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
 
		
		
		
		public void sendAndroid(List<String> tokenlist,Map<String, String> notifyMsgMap) {
			
			HttpURLConnection connection = null; 
			try
			{			
				       logger.info("sendAndroid : Android : start");		
				       String url = "https://fcm.googleapis.com/fcm/send";

				        //Instantiate an HttpClient
				        HttpClient client = new HttpClient();

				        //Instantiate a GET HTTP method
				        PostMethod method = new PostMethod(url);
				        method.setRequestHeader("Content-type", "application/json"); 
				        method.setRequestHeader("Authorization", "key=AIzaSyC9U2t3TZT_kLDfDn4IhuQj2NCA-qq90rI");
                        //Create Message 
				        String notificationMsg ="";
						if(null!=notifyMsgMap)
						{
							Iterator it = notifyMsgMap.entrySet().iterator();
							//notificationMsg="Application Status  has been updated for :\n";
						    while (it.hasNext()) {
						        Map.Entry pair = (Map.Entry)it.next();
						        System.out.println("KEY > "+ pair.getKey() + " VAlue > " + pair.getValue());
						        
						        StringTokenizer st = new StringTokenizer(pair.getValue().toString()); 
						       // notifyMsgMap.put(applicationStatusDTO.getAppId(), applicationStatusDTO.getCountryCode()+"," +applicationStatusDTO.getUnitId()+ "," + applicationStatusDTO.getStatus() + ","+applicationStatusDTO.getApplicationName());
						        while (st.hasMoreTokens()) {  
						          notificationMsg=notificationMsg+st.nextToken(",")+"\n";
						        }
						        if (it.hasNext())
						        	notificationMsg=notificationMsg +"\n";						        	
						     }   
						     
							
						
						} 
						System.out.println(" Android Text Message >>>>*****  " + notificationMsg);
				        
						JSONObject jsonObj1=new JSONObject();
				        jsonObj1.put("title", "RedTrack");
				        jsonObj1.put("text", notificationMsg);
				         
				        
				        JSONObject jsonObj2=new JSONObject();
				        jsonObj2.put("fromfirebase", "RedTrack");
				        //jsonObj2.put("data", "Application status has been updated by Admin: "+ notifyMsgMap.toString()); 
				        
				        
				        JSONArray jsonArr1=new JSONArray();
				        
//				        /destination
//				        jsonArr1.put("dyN-kxpxBs4:APA91bFw_NSAXI5y-AxAaclevyX6mTkLKdh7GGoSHFYm5QA_vx0BdKnOX4dIODHm-WXa8xme_LJ7kIyzG7c6bYHlVL_JclxG5G3tjwYsNC5bAIwztKFTMquaaYv5FbRrXQhltSGyAb_I");
//				        jsonArr1.put("cV669k2FgEU:APA91bFX5kRGCB7d5_UPDVg1zLFxcVfvM9QPadgORAq62hmKuWJKX8M51w9_zXXsC_EpKAdSo9FMOEX0mcZM3e3FYMrBP4i-P6tA1qaNqFzCMneppJCEgkd2r6LoNE-rzuBcDb8-clC_");
				        for (String temp : tokenlist) {
				        	jsonArr1.put(temp);
				        }
		 			        
				        JSONObject mainJsonObj=new JSONObject();
				        mainJsonObj.put("notification", jsonObj1);
				        mainJsonObj.put("data", jsonObj2);
				        mainJsonObj.put("registration_ids", jsonArr1);
				        
	  
				        
				        RequestEntity entity = new StringRequestEntity(mainJsonObj.toString(), "application/json", null);
				        method.setRequestEntity(entity);
				        
  
				        try
				        {
				            int statusCode = client.executeMethod(method);

				            System.out.println("Status Code = "+statusCode);
				            System.out.println("QueryString>>> "+method.getQueryString());
				            System.out.println("Status Text>>>"+HttpStatus.getStatusText(statusCode));

				            //Get data as a String
				            System.out.println("getResponseBodyAsString : >> " +method.getResponseBodyAsString());

				            //OR as a byte array
				            byte [] res  = method.getResponseBody();

				            //write to file
				            FileOutputStream fos= new FileOutputStream("donepage.html");
				            fos.write(res);

				            //release connection
				            method.releaseConnection();
				        }
				        catch(Exception e) 
				        {
				        	logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>:Exception whilesending message:" + e.getMessage());
				            e.printStackTrace();
				        }
				
				 
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
}
