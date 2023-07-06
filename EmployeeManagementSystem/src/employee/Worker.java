package employee;

import java.util.ArrayList;
import java.util.List;

public class Worker extends Employees{

	public Worker(String empId, String name, String designation, Department dept, int salary) {
		super(empId, name, designation, dept, salary);
		
	}
	@Override
	public int getTotalSalary() {
		return salary;
	}
	@Override
	public int getNumEmployees() {
		return 1;
	}
	@Override
	public List<Employees> getAllEmployees(){
		List<Employees> empList = new ArrayList<Employees>();
		empList.add(this);
		return empList;
	}
}
