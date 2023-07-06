package employee;

import java.util.List;

public abstract class Employees {
	String empId;
	String name;
	String designation;
	Department dept;
	int salary;
	
	public Employees(String empId, String name, String designation, Department dept, int salary) {
		this.empId = empId;
		this.name = name;
		this.designation = designation;
		this.dept = dept;
		this.salary = salary;
	}
	public abstract int getTotalSalary();
	public abstract int getNumEmployees();
	public abstract List<Employees> getAllEmployees();
}
