package edu.jsp.Model;

import java.util.Comparator;

public class SortByAge implements Comparator<Employee> {
	
	public int compare(Employee o1, Employee o2) {
		return o1.getEmpAge()- o1.getEmpAge();
	}
}
