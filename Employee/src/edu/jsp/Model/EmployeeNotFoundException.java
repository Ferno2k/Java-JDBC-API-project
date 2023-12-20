package edu.jsp.Model;

public class EmployeeNotFoundException extends Exception{
	public EmployeeNotFoundException(){
		super("Employee not found!!!");
	}
}
