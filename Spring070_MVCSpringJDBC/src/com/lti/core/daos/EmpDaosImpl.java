package com.lti.core.daos;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.lti.core.entities.Employee;
import com.lti.core.exceptions.EmpException;

@Repository("empDao")
@Scope("singleton")
@Lazy(true)
public class EmpDaosImpl implements EmpDao {

//	@Autowired
//	private DataSource dataSource;
	@Autowired
	private JdbcTemplate jTemp;
	
	
	
	@Override
	public List<Employee> getAllEmps() throws EmpException 
	{	
		List<Employee> empList = null;
		
		
		//ArrayList<Employee> empList = new ArrayList<Employee>();
		
		//Connection open,close is all taken care of by the template
		
		/*List<Map<String,Object>> mapList=jTemp.queryForList("SELECT employee_id,first_name FROM employees");
		
		for(Map<String,Object> mapRec:mapList)
		{
			BigDecimal bdEmpId=(BigDecimal)mapRec.get("employee_id");
			int empId = bdEmpId.intValue();
			
			
			//int empId = Integer.parseInt(mapRec.get("employee_id").toString());
			String empName = mapRec.get("first_name").toString();
			Employee emp = new Employee(empId,empName);
			empList.add(emp);
		}*/
		
		String query1="SELECT employee_id,first_name FROM employees";
		empList = jTemp.query(query1, new RowMapperFactory());
		
		return empList;
	}

	@Override
	public Employee getEmpOnId(int empId) throws EmpException 
	{
		String query="SELECT employee_id,first_name,last_name,job_id,hire_date,salary,email FROM employees WHERE employee_id=?";
		
		Employee emp = jTemp.queryForObject(query,new RowMapperFactory(),empId);
		
		return emp;
	}

	@Override
	public int insertNewEmployee(Employee emp) throws EmpException 
	{
		String insertQuery = "INSERT INTO employees (employee_id,first_name,last_name,job_id,hire_date,salary,email) "
				+ "values(?,?,?,?,?,?,?)";
		Object[] params = {emp.getEmployeeId(),emp.getFirstName(),emp.getLastName(),emp.getJobId(),
				emp.getHireDate(),emp.getSalary(),emp.getEmail()};
		int recordsUpdated = jTemp.update(insertQuery, params);
		return recordsUpdated;		
	}
}

class RowMapperFactory implements RowMapper<Employee>
{
	@Override
	public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
		int empId = rs.getInt("employee_id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		String jobId = rs.getString("job_id");
		Date hireDate = rs.getDate("hire_date");
		Float salary = rs.getFloat("salary");
		String email = rs.getString("email");
		Employee emp = new Employee();
		emp.setEmployeeId(empId);
		emp.setFirstName(firstName);
		emp.setLastName(lastName);
		emp.setJobId(jobId);
		emp.setHireDate(hireDate);
		emp.setSalary(salary);
		emp.setEmail(email);
		
		return emp;
	}	
}