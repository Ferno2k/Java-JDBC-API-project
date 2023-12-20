package edu.jsp.Model;

import java.util.Objects;

public class Employee {
	private String empName;
	private int empId;
	private int empAge;
	private String empEmail;
	private double empSalary;
	

	public Employee() {
		super();
	}

	public Employee(String empName, int empId, int empAge, String empEmail, double empSalary) {
		super();
		this.empName = empName;
		this.empId = empId;
		this.empAge = empAge;
		this.empEmail = empEmail;
		this.empSalary = empSalary;
	}



	public String getEmpName() {
		return empName;
	}



	public void setEmpName(String empName) {
		this.empName = empName;
	}



	public int getEmpId() {
		return empId;
	}



	public void setEmpId(int empId) {
		this.empId = empId;
	}



	public int getEmpAge() {
		return empAge;
	}



	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}



	public String getEmpEmail() {
		return empEmail;
	}



	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}



	public double getEmpSalary() {
		return empSalary;
	}



	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}



	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empId=" + empId + ", empAge=" + empAge + ", empEmail=" + empEmail
				+ ", empSalary=" + empSalary + "]\n";
	}

	@Override
	public int hashCode() {
		return Objects.hash(empAge, empEmail, empId, empName, empSalary);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return empAge == other.empAge && Objects.equals(empEmail, other.empEmail) && empId == other.empId
				&& Objects.equals(empName, other.empName)
				&& Double.doubleToLongBits(empSalary) == Double.doubleToLongBits(other.empSalary);
	}

}
