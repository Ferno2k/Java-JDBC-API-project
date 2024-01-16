package edu.jsp.Model;

import java.util.Comparator;

public class SortBySalary implements Comparator<Employee> {

	@Override
	public int compare(Employee o1, Employee o2) {
		// TODO Auto-generated method stub
		return (int)(o1.getEmpSalary()- o1.getEmpSalary());
	}
}
