package edu.jsp.Model;

import java.util.Comparator;

public class SortByEmail implements Comparator<Employee> {
	
	public int compare(Employee o1, Employee o2) {
		return o1.getEmpEmail().compareTo(o2.getEmpEmail());
	}

}
