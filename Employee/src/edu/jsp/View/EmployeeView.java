package edu.jsp.View;

import java.util.List;
import java.util.Scanner;

import edu.jsp.Controller.EmployeeController;
import edu.jsp.Model.Employee;

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
		System.out.println("4.UPDATE EMPLOYEE");
		System.out.println("5.DELETE EMPLOYEE");
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
				updateEmp();
				break;
			}
			case 5:{
				deleteEmp();
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
		
		int result = controller.saveEmployee(empData);
		if(result < 0) {
			System.out.println("Something went wrong.");
		}
		else if(result == 0) {
			System.out.println(result + " rows affected\n");
			System.out.println("Employee not saved\n");
		}else {
			System.out.println(result + " rows affected\n");
			System.out.println("Employee saved\n");
		}

	}
	
	public void getAllEmp() {
		
		System.out.println("SELECT SORTING BASED ON... \n" 
					+ "1.SORT BY ID\n"
					+ "2.SORT BY NAME\n"
					+ "3.SORT BY AGE\n"
					+ "4.SORT BY EMAIL\n"
					+ "5.SORT BY SALARY\n"
					+ "6.UNSORTED");
			
		int userInput = scanner.nextInt();
		scanner.nextLine();
			
		if(userInput < 1 || userInput > 6) {
			System.out.println("INVALID SORTING CHOICE.\n");
			return;
		}
		
		List<Employee> empList = controller.getAllEmployee(userInput);
		
		if(empList == null) {
			System.out.println("Something went wrong.");
			return;
		}
		
		if(empList.isEmpty()) {
			System.out.println("No employees are been registered \n");
			return;
		}
		
//		for(Employee i: empList) {
//			System.out.println("");
//			System.out.println("name: " + i.getEmpName());
//			System.out.println("id: " + i.getEmpId());
//			System.out.println("age: " + i.getEmpAge());
//			System.out.println("email: " + i.getEmpEmail());
//			System.out.println("salary: " + i.getEmpSalary());
//			System.out.println("-------------------");
//		}
		
		printList(empList);
	}
	
	public void getEmpById() {
		System.out.println("ENTER the employee Id: \n");
		int searchId = scanner.nextInt();
		
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
		
		printList(controller.getAllEmployee(6));
		
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
		
		for(Employee empIndex : controller.getAllEmployee(6)) {
			if(empIndex.getEmpId() == empData.getEmpId()) {
				if(empIndex.getEmpName().equalsIgnoreCase(empData.getEmpName())) {
					if(empIndex.getEmpAge() == empData.getEmpAge()) {
						if(empIndex.getEmpEmail().equalsIgnoreCase(empData.getEmpEmail())) {
							if(empIndex.getEmpSalary() == empData.getEmpSalary()) {
								
								int result = controller.deleteEmployee(empData);
								
								if(result < 0) {
									System.out.println("Something went wrong.");
								}
								else if(result == 0) {
									System.out.println("Employee not found \n");
								}else {
									System.out.println("The employee has been deleted \n");
								}
							}
						}
					}
				}
			}
		}
		System.out.println("Employee not found \n");	
	}
	
	public void updateEmp() {
		printList(controller.getAllEmployee(6));
		
		System.out.println("Enter employee ID");
		int targetId = scanner.nextInt();
		scanner.nextLine();
		if(!controller.checkEmpId(targetId)) {
			System.out.println("Employee not found \n");	
			return;
		}
		
		System.out.println("WHAT DO YOU WANT TO CHANGE.\n" + "1.ID\n" + "2.NAME\n" + "3.AGE\n" + "4.EMAIL\n" + "5.SALARY\n" + "6.UPDATE ALL\n");
		
		int userInput = scanner.nextInt();
		scanner.nextLine();
		
		switch (userInput) {
		case 1:{
			System.out.println("Enter Employee ID: \n");
			int id = scanner.nextInt();		
			scanner.nextLine();
			
			int result = controller.updateEmployee(targetId, id , null , -1, null, -1);
			
			if(result < -1) {
				System.out.println("Something went wrong.\n");
			}
			else if(result == -1) {
				System.out.println("Incorrect details recived.\n");
			}
			else if(result == 0){
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id was not updated\n");
			}
			else {
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id has been updated\n");
			}
			break;
		}
		case 2:{
			System.out.println("Enter name of employee: \n");
			String name = scanner.next();
			scanner.nextLine();
			
			int result = controller.updateEmployee(targetId, -1 , name , -1, null, -1);
			
			if(result < -1) {
				System.out.println("Something went wrong.\n");
			}
			else if(result == -1) {
				System.out.println("Incorrect details recived.\n");
			}
			else if(result == 0){
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id was not updated\n");
			}
			else {
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id has been updated\n");
			}
			
			break;
		}
		case 3:{
			System.out.println("Enter employee age...\n");
			int age = scanner.nextInt();
			scanner.nextLine();

			int result = controller.updateEmployee(targetId, -1 , null , age, null, -1);
			
			if(result < -1) {
				System.out.println("Something went wrong.\n");
			}
			else if(result == -1) {
				System.out.println("Incorrect details recived.\n");
			}
			else if(result == 0){
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id was not updated\n");
			}
			else {
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id has been updated\n");
			}
			
			break;
		}
		case 4:{
			System.out.println("Enter email id...\n");
			String email = scanner.next();
			scanner.nextLine();

			int result = controller.updateEmployee(targetId, -1 , null , -1, email, -1);
			
			if(result < -1) {
				System.out.println("Something went wrong.\n");
			}
			else if(result == -1) {
				System.out.println("Incorrect details recived.\n");
			}
			else if(result == 0){
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id was not updated\n");
			}
			else {
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id has been updated\n");
			}
			
			break;
		}
		case 5:{
			System.out.println("Enter Employee salary: \n");
			double salary = scanner.nextDouble();
			scanner.nextLine();
			
			int result = controller.updateEmployee(targetId, -1 , null , -1, null, salary);
			
			if(result < -1) {
				System.out.println("Something went wrong.\n");
			}
			else if(result == -1) {
				System.out.println("Incorrect details recived.\n");
			}
			else if(result == 0){
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id was not updated\n");
			}
			else {
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id has been updated\n");
			}
			
			break;
		}
		case 6:{
			
			System.out.println("Enter Employee ID: \n");
			int id = scanner.nextInt();		
			scanner.nextLine();
			
			System.out.println("Enter name of employee: \n");
			String name = scanner.next();
			scanner.nextLine();
			

			System.out.println("Enter employee age...\n");
			int age = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("Enter email id...\n");
			String email = scanner.next();
			scanner.nextLine();
			
			System.out.println("Enter Employee salary: \n");
			double salary = scanner.nextDouble();
			scanner.nextLine();

			int result = controller.updateEmployee(targetId, id , name , age, email, salary);
			
			if(result < -1) {
				System.out.println("Something went wrong.\n");
			}
			else if(result == -1) {
				System.out.println("Incorrect details recived.\n");
			}
			else if(result == 0){
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id was not updated\n");
			}
			else {
				System.out.println(result + " rows affected\n");
				System.out.println("Employee id has been updated\n");
			}
			break;
		}
		default:{
			System.out.println("INVALID CHOICE PLEASE TRY AGAIN.");
		}	
		}
	}
	
	void printList(List<Employee> empList) {
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
}
