package employee;

import java.util.ArrayList;
import java.util.List;

public class Leader extends Employees{
	List<Employees> subordinates = new ArrayList<Employees>();

	public Leader(String empId, String name, String designation, Department dept, int salary) {
		super(empId, name, designation, dept, salary);
	}
	public void add(Employees emp){
		subordinates.add(emp);
	}
	public void remove(Employees emp){
		subordinates.remove(emp);
	}
	@Override
	public int getTotalSalary() {
		int salary = this.salary;
		for (Employees emp : subordinates) {
			salary += emp.getTotalSalary();
		}
		return salary;
	}
	@Override
	public int getNumEmployees() {
		int count = 1;
		for (Employees emp : subordinates) {
			count += emp.getNumEmployees();
		}
		return count;
	}
	@Override
	public List<Employees> getAllEmployees() {
		List<Employees> empList = new ArrayList<Employees>();
		empList.add(this);
		for (Employees emp : subordinates) {
			empList.addAll(emp.getAllEmployees());
		}
		return empList;
	}
}
