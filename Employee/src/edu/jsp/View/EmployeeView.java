package edu.jsp.View;

import java.util.List;
import java.util.Scanner;

import edu.jsp.Controller.EmployeeController;
import edu.jsp.Model.Employee;
import edu.jsp.Model.SortByAge;
import edu.jsp.Model.SortByEmail;
import edu.jsp.Model.SortByID;
import edu.jsp.Model.SortByName;
import edu.jsp.Model.SortBySalary;

public class EmployeeView {
	
	boolean status = true;
	Scanner scanner = new Scanner(System.in);
	EmployeeController controller = new EmployeeController();

	public static void main(String[] args) {
		EmployeeView view = new EmployeeView();
		while(view.status) {
			view.displayView();
		}
	}
	
	public void displayView(){
		
		System.out.println("");
		System.out.println("-----WELCOME-----");
		System.out.println("ENTER YOUR CHOICE");
		System.out.println("1.SAVE EMPLOYEE");
		System.out.println("2.LIST OF EMPLOYEE");
		System.out.println("3.SEARCH AN EMPLOYEE THROUGH ID");
		System.out.println("4.DELETE EMPLOYEE");
		System.out.println("5.UPDATE EMPLOYEE");
		System.out.println("6.EXIT");
		System.out.println("------------------");
		
		byte userInput = scanner.nextByte();
		
		switch(userInput) {
			case 1:{
				saveEmployee();
				break;
			}
			case 2:{
				getAllEmp();
				break;
			}
			case 3:{
				getEmpById();
				break;
			}
			case 4:{
				deleteEmp();
				break;
			}
			case 5:{
				updateEmp();
				break;
			}
			case 6:{
				this.status = false;
				System.out.println("THANK YOU");
				break;
			}
			default:{
				System.out.println("INVALID CHOICE PLEASE TRY AGAIN.");
			}
				
				
		}
		
	}
	
	
	
	public void saveEmployee(){
		System.out.println("Enter Employee ID: \n");
		int id = scanner.nextInt();		
		scanner.nextLine();
		
		System.out.println("Enter name of employee: \n");
		String name = scanner.next();
		scanner.nextLine();
		
		System.out.println("Enter Employee salary: \n");
		double salary = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("Enter employee age...\n");
		int age = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter email id...\n");
		String email = scanner.next();
		scanner.nextLine();
		
	
		Employee empData = new Employee(name, id, age, email, salary);
		
		if(controller.saveEmployee(empData) != null) {
			System.out.println("Employee saved\n");
		}else {
			System.out.println("Employee not saved\n");
		}

	}
	
	public void getAllEmp() {
		
		if(controller.searchEmployee().isEmpty()) {
			System.out.println("No employees are been registered \n");
			return;
		}
		
		
		List<Employee> empList = controller.searchEmployee();
		
		for(Employee i: empList) {
			System.out.println("");
			System.out.println("name: " + i.getEmpName());
			System.out.println("id: " + i.getEmpId());
			System.out.println("age: " + i.getEmpAge());
			System.out.println("email: " + i.getEmpEmail());
			System.out.println("salary: " + i.getEmpSalary());
			System.out.println("-------------------");
		}
	}
	
	public void getEmpById() {
		System.out.println("ENTER the employee Id: \n");
		int searchId = scanner.nextInt();
		
		sortEmployee();
		
		Employee displayEmp = controller.searchEmployee(searchId);
		
		if(displayEmp != null) {
			System.out.println("name: " + displayEmp.getEmpName());
			System.out.println("id: " + displayEmp.getEmpId());
			System.out.println("age: " + displayEmp.getEmpAge());
			System.out.println("email: " + displayEmp.getEmpEmail());
			System.out.println("salary: " + displayEmp.getEmpSalary());
			System.out.println("----------------------\n");
		}else {
			System.out.println("Employee not found...\n");
		}
	}
	
	public void deleteEmp() {
		
		getAllEmp();
		
		System.out.println("Enter Employee ID: \n");
		int id = scanner.nextInt();		
		scanner.nextLine();
		
		System.out.println("Enter name of employee: \n");
		String name = scanner.next();
		scanner.nextLine();
		
		System.out.println("Enter Employee salary: \n");
		double salary = scanner.nextDouble();
		scanner.nextLine();

		System.out.println("Enter employee age...\n");
		int age = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter email id...\n");
		String email = scanner.next();
		scanner.nextLine();
		

		Employee empData = new Employee(name, id, age, email, salary);
		
		for(Employee empIndex : controller.searchEmployee()) {
			if(empIndex.getEmpId() == empData.getEmpId()) {
				if(empIndex.getEmpName().equalsIgnoreCase(empData.getEmpName())) {
					if(empIndex.getEmpAge() == empData.getEmpAge()) {
						if(empIndex.getEmpEmail().equalsIgnoreCase(empData.getEmpEmail())) {
							if(empIndex.getEmpSalary() == empData.getEmpSalary()) {
								controller.deleteEmployee(empData);
								System.out.println("The employee has been deleted \n");
								return;
							}
						}
					}
				}
			}
		}
		System.out.println("Employee not found \n");	
	}
	
	public void updateEmp() {
		getAllEmp();
		
		System.out.println("Enter employee ID");
		int targetId = scanner.nextInt();
		scanner.nextLine();
		
		for (Employee empIndex : controller.searchEmployee()) {
			if(empIndex.getEmpId() == targetId) {
				System.out.println("WHAT DO YOU WANT TO CHANGE.\n" + "1.ID\n" + "2.NAME\n" + "3.AGE\n" + "4.EMAIL\n" + "5.SALARY\n" + "6.UPDATE ALL\n");
				
				int userInput = scanner.nextInt();
				scanner.nextLine();
				
				switch (userInput) {
				case 1:{
					System.out.println("Enter Employee ID: \n");
					int id = scanner.nextInt();		
					scanner.nextLine();
					
					empIndex.setEmpId(id);
					System.out.println("Employee id has been updated\n");
					break;
				}
				case 2:{
					System.out.println("Enter name of employee: \n");
					String name = scanner.next();
					scanner.nextLine();
					
					empIndex.setEmpName(name);
					System.out.println("Employee name has been updated \n");
					
					break;
				}
				case 3:{
					System.out.println("Enter employee age...\n");
					int age = scanner.nextInt();
					scanner.nextLine();

					empIndex.setEmpAge(age);
					System.out.println("Employee age has been updated \n");
					
					break;
				}
				case 4:{
					System.out.println("Enter email id...\n");
					String email = scanner.next();
					scanner.nextLine();

					empIndex.setEmpEmail(email);
					System.out.println("Employee email has been updated \n");
					
					break;
				}
				case 5:{
					System.out.println("Enter Employee salary: \n");
					double salary = scanner.nextDouble();
					scanner.nextLine();
					
					empIndex.setEmpSalary(salary);
					System.out.println("Employee salary has been updated \n");
					
					break;
				}
				case 6:{
					
					System.out.println("Enter Employee ID: \n");
					int id = scanner.nextInt();		
					scanner.nextLine();
					
					System.out.println("Enter name of employee: \n");
					String name = scanner.next();
					scanner.nextLine();
					
					System.out.println("Enter Employee salary: \n");
					double salary = scanner.nextDouble();
					scanner.nextLine();

					System.out.println("Enter employee age...\n");
					int age = scanner.nextInt();
					scanner.nextLine();
					
					System.out.println("Enter email id...\n");
					String email = scanner.next();
					scanner.nextLine();

					empIndex.setEmpId(id);
					empIndex.setEmpName(name);
					empIndex.setEmpAge(age);
					empIndex.setEmpEmail(email);
					empIndex.setEmpSalary(salary);

					System.out.println("ALL EMPLOYEE DETAILS HAVE BEEN UPDATED \n");
					
					break;
				}
				default:{
					System.out.println("INVALID CHOICE PLEASE TRY AGAIN.");
				}
				}
				
			}
		}
		System.out.println("Employee not found \n");			
	}
	
	
	public void sortEmployee(){
		

		System.out.println("SELECT SORTING BASED ON... \n" 
				+ "SORT BY ID\n"
				+ "SORT BY NAME\n"
				+ "SORT BY AGE\n"
				+ "SORT BY EMAIL\n"
				+ "SORT BY SALARY\n"
				+ "UNSORTED");
		
		int userInput = scanner.nextInt();
		scanner.nextLine();
		
		switch(userInput) {
			case 1: {
				controller.sortEmpList(new SortByID());
				break;
			}
			case 2:{
				controller.sortEmpList(new SortByName());
				break;
			}
			case 3:{
				controller.sortEmpList(new SortByAge());
				break;
			}
			case 4: {
				controller.sortEmpList(new SortByEmail());
				break;
			}
			case 5: {
				controller.sortEmpList(new SortBySalary());
				break;
			}
			default: {
				System.out.println("INVALID SORTING CHOICE.\n");
			}
			
		}
		
		
		}
	
	
}
