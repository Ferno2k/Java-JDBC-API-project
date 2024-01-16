package edu.jsp.Model;

import java.util.Comparator;

public class SortByEmail implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return o1.getEmpEmail().compareTo(o2.getEmpEmail());
	}
}
