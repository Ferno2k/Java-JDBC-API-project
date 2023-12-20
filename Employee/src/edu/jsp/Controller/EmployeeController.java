package edu.jsp.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import edu.jsp.Model.Employee;
import edu.jsp.Model.SortByAge;
import edu.jsp.Model.SortByEmail;
import edu.jsp.Model.SortByID;
import edu.jsp.Model.SortByName;
import edu.jsp.Model.SortBySalary;

public class EmployeeController{
	private List<Employee> empList = new ArrayList<>();

	public Employee saveEmployee(Employee emp){
		if (emp != null) {
			empList.add(emp);
			return emp;
		}else {
			return null;
		}
	}
	
	public Employee deleteEmployee(Employee emp) {
		if(emp != null) {
			for(Employee i: empList) {
				if(i.equals(emp)) {
					empList.remove(emp);
					return emp;
				}
			}
			
//			new EmployeeNotFoundException();	
			return null;
		}else {
			return null;
		}
	}
	
	public List<Employee> searchEmployee(){
		return sortEmpList(null);
	}
	
	public Employee searchEmployee(int id){
		
		if(id> 0) {
			for(Employee i : empList) {
				if(i.getEmpId() == id) {
					return i;
				}
			}
//			throw new EmployeeNotFoundException();	
			return null;
		}else {
			return null;
		}
			}
	
	public List<Employee> sortEmpList(Comparator<Employee> comparator){
		if(comparator instanceof SortByID) {
			Collections.sort(empList, comparator);
			return empList;
		}else if(comparator instanceof SortByName) {
			Collections.sort(empList, comparator);
			return empList;
		}else if(comparator instanceof SortByAge) {
			Collections.sort(empList, comparator);
			return empList;
		}else if(comparator instanceof SortByEmail) {
			Collections.sort(empList, comparator);
			return empList;
		}else if(comparator instanceof SortBySalary) {
			Collections.sort(empList, comparator);
			return empList;
		}else {
			return empList;
		}
		
	}
}
