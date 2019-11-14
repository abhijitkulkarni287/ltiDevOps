package com.lti.core.services;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lti.core.daos.EmpDao;
import com.lti.core.entities.Employee;
import com.lti.core.exceptions.EmpException;

@Service("empService")
@Scope("singleton") 
@Lazy(false)     
public class EmpServiceImpl implements EmpService
{	
	@Resource          
	private EmpDao empDao;

	@Override
	public List<Employee> getAllEmps() throws EmpException
	{	
		return empDao.getAllEmps();
	}

	@Override
	public Employee getEmployeeDetails(int empId) throws EmpException 
	{
		return empDao.getEmpOnId(empId);		
	}

	@Override
	public int addEmployee(Employee emp) throws EmpException {
		
		return empDao.insertNewEmployee(emp);
	}
}
