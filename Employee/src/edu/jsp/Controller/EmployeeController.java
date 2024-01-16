package edu.jsp.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.jsp.ConnectionPool.ConnectionPool;
import edu.jsp.Model.Employee;

public class EmployeeController{
	
	

	public int saveEmployee(Employee emp){
		try {
			Connection connection = ConnectionPool.getConnection();
			String query = "INSERT INTO employees VALUES(?,?,?,?,?)";
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			statement.setInt(1,emp.getEmpId());
			statement.setString(2,emp.getEmpName());
			statement.setInt(3, emp.getEmpAge());
			statement.setString(4,emp.getEmpEmail());
			statement.setDouble(5,emp.getEmpSalary());
			
			int result = statement.executeUpdate();
			
			statement.close();
			ConnectionPool.receiveConnection(connection);
			
			return result;
		}catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public int deleteEmployee(Employee emp) {			
			try {
				Connection connection = ConnectionPool.getConnection();
				String query = "DELETE FROM employees WHERE emp_id=?";
				
				PreparedStatement statement = connection.prepareStatement(query);
				
				statement.setInt(1, emp.getEmpId());
				
				int result = statement.executeUpdate();
				
				statement.close();
				ConnectionPool.receiveConnection(connection);
				
				
				return result;
			}catch(SQLException e) {
				e.printStackTrace();
				return -1;
			}					
			
			
	}
	
	public List<Employee> getAllEmployee(int orderby){
		
		List<Employee> empList = new ArrayList<>();
		String orderbyQuery;
		
		switch(orderby) {
		case 1: {
			orderbyQuery = "ORDER BY emp_id";
			break;
		}
		case 2:{
			orderbyQuery = "ORDER BY emp_name";
			break;
		}
		case 3:{
			orderbyQuery = "ORDER BY emp_age";
			break;
		}
		case 4: {
			orderbyQuery = "ORDER BY emp_email";
			break;
		}
		case 5: {
			orderbyQuery = "ORDER BY emp_sal";
			break;
		}
		case 6:{
			orderbyQuery = "";
			break;
		}
		default: {
			return null;
		}
		}
		
		try {
			Connection connection = ConnectionPool.getConnection();
			String query = "SELECT * FROM employees " + orderbyQuery;
			
			PreparedStatement statement = connection.prepareStatement(query);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				int age = resultSet.getInt(3);
				String email = resultSet.getString(4);
				int salary = resultSet.getInt(5);
				
				Employee empObj = new Employee(name,id,age,email,salary);		
				empList.add(empObj);
			}
			
			statement.close();
			ConnectionPool.receiveConnection(connection);
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return empList;
	}
	
	public Employee searchEmployee(int id){
		
		List<Employee> empList = getAllEmployee(6);
		
		if(id> 0) {
			for(Employee i : empList) {
				if(i.getEmpId() == id) {
					return i;
				}
			}
			return null;
		}else {
			return null;
		}
	}

	public int updateEmployee(int targetId, int id, String name, int age, String email, double salary) {
		try {
			Connection connection = ConnectionPool.getConnection();
			
			if(id > 0 && name != null && age > 0 && email != null && salary > 0) {
				String query = "UPDATE employees set emp_id =?, emp_name=?,emp_age=?, emp_email=?,emp_sal=? WHERE emp_id=?";
				
				PreparedStatement statement = connection.prepareStatement(query);
				
				statement.setInt(1, id);
				statement.setString(2, name);
				statement.setInt(3, age);
				statement.setString(4, email);
				statement.setDouble(5, salary);
				statement.setInt(6, targetId);
				
				int result = statement.executeUpdate();
				
				statement.close();
				ConnectionPool.receiveConnection(connection);
				return result;
			}else {
				if(id > 0) {
					String query = "UPDATE employees set emp_id =? WHERE emp_id=?";
					
					PreparedStatement statement = connection.prepareStatement(query);
					
					statement.setInt(1, id);
					statement.setInt(2, targetId);
					
					int result = statement.executeUpdate();
					
					statement.close();
					ConnectionPool.receiveConnection(connection);
					return result;
				}
				else if(name != null) {
					String query = "UPDATE employees set emp_name=? WHERE emp_id=?";
					
					PreparedStatement statement = connection.prepareStatement(query);
					
					statement.setString(1, name);
					statement.setInt(2, targetId);
					
					int result = statement.executeUpdate();
					
					statement.close();
					ConnectionPool.receiveConnection(connection);
					return result;
				}
				else if(age > 0) {
					String query = "UPDATE employees set emp_age=? WHERE emp_id=?";
					
					PreparedStatement statement = connection.prepareStatement(query);
					
					statement.setInt(1, age);
					statement.setInt(2, targetId);
					
					int result = statement.executeUpdate();
					
					statement.close();
					ConnectionPool.receiveConnection(connection);
					return result;
				}
				else if(email != null) {
					String query = "UPDATE employees set emp_email=? WHERE emp_id=?";
					
					PreparedStatement statement = connection.prepareStatement(query);
					
					statement.setString(1, email);
					statement.setInt(2, targetId);
					
					int result = statement.executeUpdate();
					
					statement.close();
					ConnectionPool.receiveConnection(connection);
					return result;
				}
				else if(salary > 0) {
					String query = "UPDATE employees set emp_sal=? WHERE emp_id=?";
					
					PreparedStatement statement = connection.prepareStatement(query);
					
					statement.setDouble(1, salary);
					statement.setInt(2, targetId);
					
					int result = statement.executeUpdate();
					
					statement.close();
					ConnectionPool.receiveConnection(connection);
					return result;
				}else {
					return -1;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return -2;
		}
	}

	public boolean checkEmpId(int id) {
		
			try {
				Connection connection = ConnectionPool.getConnection();
				
				String checkQuery = "SELECT * FROM employees WHERE emp_id = ?";
				PreparedStatement statement = connection.prepareStatement(checkQuery);
				statement.setInt(1, id);
				
				ResultSet resultSet = statement.executeQuery();
				
				if(resultSet.next()) {
					if(resultSet.getInt(1) == id) {
						statement.close();
						ConnectionPool.receiveConnection(connection);
						return true;
					}
					}else {
						statement.close();
						ConnectionPool.receiveConnection(connection);
						return false;
					}
			
			statement.close();
			ConnectionPool.receiveConnection(connection);
			return false;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
}
