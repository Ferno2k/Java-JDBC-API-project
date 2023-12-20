package edu.jsp.Model;

import java.util.Comparator;

public class SortByID implements Comparator<Employee> {
	
	public int compare(Employee o1, Employee o2) {
		return o1.getEmpId()- o1.getEmpId();
	}
}
