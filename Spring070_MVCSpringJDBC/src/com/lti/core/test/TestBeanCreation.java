package com.lti.core.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lti.core.entities.Employee;
import com.lti.core.exceptions.EmpException;
import com.lti.core.services.EmpService;

//By default spring creates objects as singletons. they are created once and whole application can
// use its reference
// By default singleton objects are created along with context
//


public class TestBeanCreation 
{

	public static void main(String[] args) 
	{
		//Constructing application context
		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		EmpService service = ctx.getBean("empService",EmpService.class);
		
		Employee emp = new Employee();
		emp.setEmployeeId(207);
		emp.setFirstName("babu");
		emp.setLastName("jalela");
		emp.setJobId("AC_MGR");
		java.util.Date dt = new java.util.Date();
		emp.setHireDate(new java.sql.Date(dt.getTime()));
		emp.setSalary(350000);
		emp.setEmail("babuJalela@gmail.com");
		
		try {
			/* List<Employee> empList = service.getAllEmps();
			
			for(Employee emp : empList)
			{
				System.out.println(emp);
			}
			*/
			
				System.out.println(service.addEmployee(emp)+" Record inserted");
				Employee emp1 = service.getEmployeeDetails(207);
				System.out.println(emp1);
			
		} catch (EmpException e) {
			e.printStackTrace();
		}
			
		ctx.close();
	}
}
