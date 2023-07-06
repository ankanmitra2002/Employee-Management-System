package employee;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;




public class Information {

	private static Information info = null;
	private Mapping<String,Employees> empMap;
	private Mapping<String,Department> deptMap;
	private Mapping<String,Project> projMap;
	
	private Information() {
		empMap = new Mapping<>(1000);
        deptMap = new Mapping<>(100);
        projMap = new Mapping<>(500);
	}
	public static Information getInstance(){
        if(info == null){
            info = new Information();
        }
        return info;
    }

    public void addEmployee(Employees emp){
        empMap.insert(emp.empId,emp);
    }
    public Employees getEmployee(String empId){
        return empMap.get(empId);
    }
    public void removeEmployee(String empId){
        empMap.delete(empId);
    }
    public void updateEmployee(Employees emp){
        empMap.update(emp.empId,emp);
    }
    public Mapping<String,Employees> getEmployeeMap(){
        return empMap;
    }
    public String[][] getEmployees(){
        String[][] empinfo = new String[empMap.getSize()][5];
        int i = 0;
        for(String key : empMap.getKeys(String.class)){
            Employees emp = empMap.get(key);
            empinfo[i][0] = emp.empId;
            empinfo[i][1] = emp.name;
            empinfo[i][2] = emp.designation;
            empinfo[i][3] = emp.dept.name;
            empinfo[i][4] = String.valueOf(emp.salary);
            i++;
        }
        return empinfo;
    }
    public void addWorker(Employees leader){
        for(String key : empMap.getKeys(String.class)){
            Employees emp = empMap.get(key);
            if(emp.dept.equals(leader.dept) && emp.designation.equals("Worker")){
                ((Leader) leader).add(emp);
            }
        }
    }
    public void addDepartment(Department dept){
        deptMap.insert(dept.name,dept);
    }
    public Department getDepartment(String deptId){
        return deptMap.get(deptId);
    }
    public void removeDepartment(String deptId){
        empMap.delete(deptId);
    }
    public void updateDepartment(Department dept){
        deptMap.update(dept.name,dept);
    }
    public Mapping<String,Department> getDeptartmentMap(){
        return deptMap;
    }
    public String[][] getDepartments(){
        String[][] deptinfo = new String[deptMap.getSize()][3];
        int i = 0;
        for(String key : deptMap.getKeys(String.class)){
            Department dept = deptMap.get(key);
            deptinfo[i][0] = dept.deptId;
            deptinfo[i][1] = dept.name;
            deptinfo[i][2] = dept.address;
            i++;
        }
        return deptinfo;
    }
    public String[] getDepartmentNames(){
        String[] deptinfo = new String[deptMap.getSize()];
        int i = 0;
        for(String key : deptMap.getKeys(String.class)){
            Department dept = deptMap.get(key);
            deptinfo[i] = dept.name;
            i++;
        }
        return deptinfo;
    }
    public String getEmployeeinDeptartment(Department dept){
        String empList = "";
        String[][] arr = getEmployees();
        for(int i=0;i< arr.length;i++){
            if (dept.name.equals(arr[i][3])){
                empList += arr[i][1]+",";
            }
        }
        return empList.substring(0,empList.length());
    }

    public String getProjectinDeptartment(Department dept){
        String projList="";
        String[][] arr = getProjects();
        for(int i=0;i< arr.length;i++){
            if (dept.name.equals(arr[i][2])){
                projList += arr[i][1]+",";
            }
        }
        return projList.substring(0,projList.length());
    }
    public void addProject(Project proj){
        projMap.insert(proj.projId,proj);
    }
    public Project getProject(String projId){
        return projMap.get(projId);
    }
    public void removeProject(String projId){
        empMap.delete(projId);
    }
    public void updateProject(Project proj){
        projMap.update(proj.projId,proj);
    }
    public Mapping<String,Project> getProjMap(){
        return projMap;
    }
    public String[][] getProjects(){
        String[][] projinfo = new String[projMap.getSize()][4];
        int i = 0;
        for(String key : projMap.getKeys(String.class)){
            Project proj = projMap.get(key);
            projinfo[i][0] = proj.projId;
            projinfo[i][1] = proj.name;
            projinfo[i][2] = proj.dept.name;
            projinfo[i][3] = proj.empId;
            i++;
        }
        return projinfo;
    }
    public void loadData(){
        try{
            BufferedReader br = new BufferedReader(new FileReader("src//report.txt"));
            while(br.ready()) {
                String str1 = br.readLine();
                String values[] = str1.split(",");
                if(values[0].equals("D")) {
                    Department d = new Department(values[1], values[2], values[3]);
                    info.addDepartment(d);
                } else if (values[0].equals("E")) {
                    if(values[3].equals("Worker")) {
                        Department d = info.getDepartment(values[4]);
                        Employees w = new Worker(values[1], values[2], values[3], d, Integer.parseInt(values[5]));
                        info.addEmployee(w);
                    } else  {
                        Department d = info.getDepartment(values[4]);
                        Employees l = new Leader(values[1], values[2], values[3], d, Integer.parseInt(values[5]));
                        info.addWorker(l);
                        info.addEmployee(l);
                    }
                } else if (values[0].equals("P")) {
                    Department d = info.getDepartment(values[3]);
                    Project p = new Project(values[1], values[2], d, values[4]);
                    info.addProject(p);
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public void refresh(){
        String[][] emps = this.getEmployees();
        String[][] depts = this.getDepartments();
        String[][] projs=this.getProjects();

        try (PrintWriter writer = new PrintWriter(new File("src//report.txt"))) {
            for(int i=0;i< depts.length;i++){
                writer.println("D,"+depts[i][0]+","+depts[i][1]+","+depts[i][2]);
            }
            for(int i=0;i< emps.length;i++){
                if(emps[i][2].equals("Worker")){
                    writer.println("E,"+emps[i][0]+","+emps[i][1]+","+emps[i][2]+","+emps[i][3]+","+emps[i][4]);
                }
            }
            for(int i=0;i< emps.length;i++){
                if(!emps[i][2].equals("Worker")){
                    writer.println("E,"+emps[i][0]+","+emps[i][1]+","+emps[i][2]+","+emps[i][3]+","+emps[i][4]);
                }
            }
            for(int i=0;i< projs.length;i++){
                writer.println("P,"+projs[i][0]+","+projs[i][1]+","+projs[i][2]+","+projs[i][3]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
