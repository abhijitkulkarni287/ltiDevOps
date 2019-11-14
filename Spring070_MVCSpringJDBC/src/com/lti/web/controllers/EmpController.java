package com.lti.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lti.core.entities.Employee;
import com.lti.core.exceptions.EmpException;
import com.lti.core.services.EmpService;

// http://localhost:9090/Spring070_MVCSpringJDBC/home.hr
@Controller
public class EmpController 
{
	@Autowired
	private EmpService service;
	
	@RequestMapping(name="/home.hr")     //RequestMapping declares the url for controller method
	public ModelAndView getHomePage()
	{
		String company = "Larson and toubro information services";
		String jspName = "/pages/HomePage.jsp";
		
		ModelAndView mAndV = new ModelAndView();
		mAndV.addObject("companyName",company);
		mAndV.setViewName(jspName);
		return mAndV;
	}
	
	//Method to return list of employees
	@RequestMapping(name="/empList.hr")
	public ModelAndView getEmpList() throws EmpException
	{
		List< Employee> empList = service.getAllEmps();
		String jspName = "/pages/EmpList.jsp";
		
		ModelAndView mAndV = new ModelAndView();
		mAndV.addObject("empList",empList);
		mAndV.setViewName(jspName);
		return mAndV;
	}
}
