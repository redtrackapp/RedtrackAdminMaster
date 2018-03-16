package com.dbs.redtrackadmin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dbs.redtrackadmin.integration.dao.impl.IncidentDAOImpl;
import com.dbs.redtrackadmin.integration.model.Incident;


@Controller
public class IncidentController {

	@Autowired
	private IncidentDAOImpl incidentDAO;
	
//	@RequestMapping(value="/")
//	public ModelAndView listContact(ModelAndView model) throws IOException{
//	
//		List<Incident> listofIncidents = incidentDAO.getAllIncidents();
//	
//		model.addObject("listofIncidents", listofIncidents);
//		model.setViewName("allIncidents");
//		
//		return model;
//	}
	
	
	@RequestMapping(value="/listIncidents")
	public ModelAndView listContact(ModelAndView model) throws IOException{
	
		List<Incident> listofIncidents = incidentDAO.getAllIncidents();
	
		model.addObject("listofIncidents", listofIncidents);
		model.setViewName("allIncidents");
		
		return model;
	}	
	
	
	@RequestMapping(value = "/newIncident", method = RequestMethod.GET)
	public ModelAndView newIncident(ModelAndView model) {
		
		Incident incident=new Incident();
		System.out.println("in controller *********************newIncident**********************start ");
		model.addObject("incident", incident);
		model.setViewName("IncidentForm");
		System.out.println("in controller*********************newIncident**********************end ");
		return model;
	}
	
	
	@RequestMapping(value = "/saveIncident", method = RequestMethod.POST)
	public ModelAndView saveIncident(@ModelAttribute Incident incident) {
		System.out.println("in controller saveIncident*********************saveIncident**********************start ");
		System.out.println("incident id is ********************** "+incident.getIncidentId());
		incidentDAO.saveOrUpdateIncident(incident);		
		System.out.println("in controller saveIncident*********************saveIncident**********************end ");
		return new ModelAndView("redirect:/listIncidents");
	}
	
	
	
	@RequestMapping(value = "/editIncident", method = RequestMethod.POST)
	public ModelAndView editIncident(@RequestParam("incidentId") String incidentId) {
	/*	String incidentId =request.getParameter("incidentId");*/
		String incidentId2 =incidentId;
		System.out.println("incidentId  from request "+incidentId);
		System.out.println("incidentId  from incidentId2 "+incidentId2);
		Incident incident2 = incidentDAO.getIncidentById(incidentId2);
		ModelAndView model = new ModelAndView("IncidentForm");
		model.addObject("incident", incident2);
		
		return model;
	}
}
