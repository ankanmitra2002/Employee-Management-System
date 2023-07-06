package employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.PublicKey;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;



public class Operation extends JFrame implements ActionListener{

	 	JTabbedPane dashboard = new JTabbedPane();
	 	Information database;
	    String[] arr;
	    JTextField emp_id,emp_name,emp_designation,emp_salary;
	    JComboBox<String> emp_in_Dept,proj_in_Dept;
	    DefaultComboBoxModel<String> emp_deptModel,proj_deptModel;
	    JTextField dept_id,dept_name,dept_address;
	    JTextField proj_id,proj_name,proj_empId;
	    String[][] empdata,deptdata,projdata;
	    JTable emp_table,dept_table,proj_table;
	    DefaultTableModel emp_model,dept_model,proj_model;
	    JScrollPane emp_sp,dept_sp,proj_sp;
	    Department d;
	    Employees e;
	    Project p;
	    JLabel totalSalary,subordinaries,subNames,totalEmp,totalProj;
	    JLabel emptodept,projtodept;
	public Operation() {
		setTitle("Operations on Employee Management System");
		setExtendedState(MAXIMIZED_BOTH);
		setLocation(100,200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.black));
		getContentPane().setBackground(Color.WHITE);
		database = Information.getInstance();
        database.loadData();
		operationComponents();
		setVisible(true);	
	}
	private void operationComponents() {
		JPanel imgPanel = new JPanel();
		imgPanel.setLayout(null);
		imgPanel.setBackground(new Color(200,200,230));
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("logo.png"));
		Image img = icon.getImage().getScaledInstance(300,300,Image.SCALE_AREA_AVERAGING);
		ImageIcon newIcon = new ImageIcon(img);
		JLabel imageLabel = new JLabel(newIcon);
		imageLabel.setBounds(10,10,400,300);
		imgPanel.add(imageLabel);
		
		JLabel company = new JLabel("A K MITRA INDUSTRIES PVT LTD",JLabel.CENTER);
		company.setFont((new Font("Arial",Font.CENTER_BASELINE,50)));
		company.setBackground(new Color(200,200,230));
		company.setOpaque(true);
		
		add(company,BorderLayout.NORTH);
		
		
		ImageIcon buttonimg = new ImageIcon(ClassLoader.getSystemResource("button.jpg"));
		Image imgbutton = buttonimg.getImage().getScaledInstance(40,40,Image.SCALE_SMOOTH);
		ImageIcon buttonIcon = new ImageIcon(imgbutton);
		
		JButton headline1 = new JButton("Departments");
		headline1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headline1.setFont(new Font("Arial",Font.CENTER_BASELINE,22));
		headline1.setFocusPainted(false);
		headline1.setIcon(buttonIcon);
		headline1.setBounds(120,400,200,50);
		headline1.setBorder(new LineBorder(new Color(200,20,100),6,true));
		headline1.setHorizontalAlignment(SwingConstants.LEFT);
		headline1.setBackground(new Color(90,20,100));
		headline1.setForeground(Color.white);
		headline1.addActionListener(this);
		imgPanel.add(headline1);
		
		JButton headline2 = new JButton("Employees");
		headline2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		headline2.setFont(new Font("Arial",Font.CENTER_BASELINE,24));
		headline2.setFocusPainted(false);
		headline2.setIcon(buttonIcon);
		headline2.setBounds(120,500,200,50);
		headline2.setBorder(new LineBorder(new Color(200,20,100),6,true));
		headline2.setHorizontalAlignment(SwingConstants.LEFT);
		headline2.setBackground(new Color(90,20,100));
		headline2.setForeground(Color.white);
		headline2.addActionListener(this);
		imgPanel.add(headline2);
		
		
		JButton headline3 = new JButton("Projects");
		headline3.setFont(new Font("Arial",Font.CENTER_BASELINE,27));
		headline3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//headline1.setBorderPainted(false);
		headline3.setFocusPainted(false);
		headline3.setIcon(buttonIcon);
		headline3.setHorizontalAlignment(SwingConstants.LEFT);
		headline3.setBounds(120,600,200,50);
		headline3.setBorder(new LineBorder(new Color(200,20,100),6,true));
		headline3.setBackground(new Color(90,20,100));
		headline3.setForeground(Color.white);
		headline3.addActionListener(this);
		imgPanel.add(headline3);
		
		
		
		JPanel empPanel = new JPanel();
        JPanel deptPanel = new JPanel();
        JPanel projPanel = new JPanel();
        dashboard.setBackground(Color.LIGHT_GRAY);
        dashboard.setOpaque(true);
        dashboard.setBounds(350,0,1150,720);
        dashboard.add("Department",deptPanel);
        dashboard.add("Employee",empPanel);
        dashboard.add("Project",projPanel);
        dashboard.setBorder(BorderFactory.createLineBorder(getForeground(),6));
        
       
        empPanel.setLayout(new BorderLayout());
	    empPanel.setBackground(Color.WHITE);
	     
	    JPanel emp_Panel = new JPanel();
        emp_Panel.setLayout(new BorderLayout());
        emp_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
        emp_Panel.setBackground(Color.WHITE);
        
	    JPanel emp_form_panel = new JPanel();
	    emp_form_panel.setLayout(new BoxLayout(emp_form_panel,BoxLayout.Y_AXIS));
	    emp_form_panel.setPreferredSize(new Dimension(300,300));
	  //  emp_form_panel.setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    Font font = new Font("Arial",Font.BOLD,15);
        
        JPanel empidField = new JPanel();
        empidField.setBackground(Color.WHITE);
        empidField.setLayout(new FlowLayout(15));
        JLabel lempId = new JLabel("Employee Id: ");
        lempId.setFont(font);
        empidField.add(lempId);
        emp_id = new JTextField();
        emp_id.setFont(font);
        emp_id.setPreferredSize(new Dimension(300,30));
        empidField.add(emp_id);
        emp_form_panel.add(empidField);
	    
        JPanel empnameField = new JPanel();
        empnameField.setBackground(Color.WHITE);
        empnameField.setLayout(new FlowLayout(15));
        JLabel lempName = new JLabel("Employee Name: ");
        lempName.setFont(font);
        empnameField.add(lempName);
        emp_name = new JTextField();
        emp_name.setFont(font);
        emp_name.setPreferredSize(new Dimension(300,30));
        empnameField.add(emp_name);
        emp_form_panel.add(empnameField);
	    
        JPanel designationField = new JPanel();
        designationField.setBackground(Color.WHITE);
        designationField.setLayout(new FlowLayout(15));
        JLabel lempDesignation = new JLabel("Designation: ");
        lempDesignation.setFont(font);
        designationField.add(lempDesignation);
        emp_designation = new JTextField();
        emp_designation.setFont(font);
        emp_designation.setPreferredSize(new Dimension(300,30));
        designationField.add( emp_designation);
        emp_form_panel.add(designationField);

        JPanel empdeptField = new JPanel();
        empdeptField.setBackground(Color.WHITE);
        empdeptField.setLayout(new FlowLayout(15));
        JLabel lempDeptId = new JLabel("Deptartment Name: ");
        lempDeptId.setFont(font);
        empdeptField.add(lempDeptId);
        
        arr = database.getDepartmentNames();
        emp_deptModel = new DefaultComboBoxModel<>(arr);
        emp_in_Dept = new JComboBox<>(emp_deptModel);
        emp_in_Dept.setFont(font);
        emp_in_Dept.setPreferredSize(new Dimension(300,30));
        empdeptField.add(emp_in_Dept);
        emp_form_panel.add(empdeptField);

        JPanel salaryField = new JPanel();
        salaryField.setBackground(Color.WHITE);
        salaryField.setLayout(new FlowLayout(15));
        JLabel lempSalary = new JLabel("Salary: ");
        lempSalary.setFont(font);
        salaryField.add(lempSalary);
        emp_salary = new JTextField();
        emp_salary.setFont(font);
        emp_salary.setPreferredSize(new Dimension(300,30));
        salaryField.add(emp_salary);
        emp_form_panel.add(salaryField);

        JPanel totalpanel = new JPanel(new BorderLayout());
	    totalEmp = new JLabel();
        totalEmp.setText("Total Number of Employees in the Comapany : "+ database.getEmployees().length);
        totalEmp.setFont(font);
        totalEmp.setOpaque(true);
        totalEmp.setHorizontalAlignment(JLabel.CENTER);
        totalEmp.setBackground(Color.WHITE);
        totalpanel.add(totalEmp);
        emp_form_panel.add(totalpanel);
        
        totalSalary = new JLabel();
        totalSalary.setFont(font);
        totalSalary.setPreferredSize(new Dimension(300,30));
        emp_form_panel.add(totalSalary);

        subordinaries = new JLabel();
        subordinaries.setFont(font);
        subordinaries.setPreferredSize(new Dimension(300,30));
        emp_form_panel.add(subordinaries);

        subNames = new JLabel();
        subNames.setFont(font);
        subNames.setPreferredSize(new Dimension(300,30));
        emp_form_panel.add(subNames);
        
        emp_Panel.add(emp_form_panel,BorderLayout.CENTER);
	           
        JPanel empbtnPanel = new JPanel();
        empbtnPanel.setBackground(Color.WHITE);
        empbtnPanel.setLayout(new FlowLayout());
       // empbtnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JButton Insert = new JButton("Insert");
		Insert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Insert.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		Insert.setFocusPainted(false);
		Insert.setBorder(new LineBorder(new Color(0,0,0),2,true));
		Insert.setHorizontalAlignment(SwingConstants.LEFT);
		Insert.setBackground(Color.blue);
		Insert.setForeground(Color.white);
		Insert.setActionCommand("EmployeeInsert");
		Insert.addActionListener(this);
		empbtnPanel.add(Insert);
		
		JButton Update = new JButton("Update");
		Update.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Update.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		Update.setFocusPainted(false);
		Update.setBorder(new LineBorder(new Color(0,0,0),2,true));
		Update.setHorizontalAlignment(SwingConstants.LEFT);
		Update.setBackground(Color.blue);
		Update.setForeground(Color.white);
		Update.setActionCommand("EmployeeUpdate");
		Update.addActionListener(this);
		empbtnPanel.add(Update);
		
		JButton Delete = new JButton("Delete");
		Delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Delete.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		Delete.setFocusPainted(false);
		Delete.setBorder(new LineBorder(new Color(0,0,0),2,true));
		Delete.setHorizontalAlignment(SwingConstants.LEFT);
		Delete.setBackground(Color.yellow);
		Delete.setForeground(Color.black);
		Delete.setActionCommand("EmployeeDelete");
		Delete.addActionListener(this);
		empbtnPanel.add(Delete);
				
	
		emp_Panel.add(empbtnPanel,BorderLayout.SOUTH);
        empPanel.add(emp_Panel,BorderLayout.NORTH);
		
        empdata = database.getEmployees();
        String[] emp_columns = new String[]{"Employee Id", "Name", "Designation", "Department Name", "Salary"};
        emp_model = new DefaultTableModel(empdata,emp_columns);
        emp_table = new JTable(emp_model);
        emp_table.setRowHeight(20);
        emp_table.getTableHeader().setBackground(new Color(80,5,5));
        emp_table.getTableHeader().setForeground(Color.WHITE);
        emp_table.getTableHeader().setFont(new Font("Arial", Font.CENTER_BASELINE,18));
        emp_table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
        emp_table.setFont(font);
        emp_sp = new JScrollPane(emp_table);
        empPanel.add(emp_sp,BorderLayout.CENTER);
        
        
        
        
        
        
        
        
        
        deptPanel.setLayout(new BorderLayout());
	    deptPanel.setBackground(Color.WHITE);
	     
	    JPanel dept_Panel = new JPanel();
	    dept_Panel.setLayout(new BorderLayout());
	    dept_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
	    dept_Panel.setBackground(Color.WHITE);
        
	    JPanel dept_form_panel = new JPanel();
	    dept_form_panel.setLayout(new BoxLayout(dept_form_panel,BoxLayout.Y_AXIS));
	    dept_form_panel.setPreferredSize(new Dimension(300,300));
	    dept_form_panel.setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    
        
        
	    
	    JPanel deptidField = new JPanel();
        deptidField.setBackground(Color.WHITE);
        deptidField.setLayout(new FlowLayout(15));
        JLabel ldeptId = new JLabel("Department Id: ");
        ldeptId.setFont(font);
        deptidField.add(ldeptId);
        
        dept_id = new JTextField();
        dept_id.setFont(font);
        dept_id.setPreferredSize(new Dimension(300,30));
        deptidField.add(dept_id);
        dept_form_panel.add(deptidField);
	    
        JPanel deptnameField = new JPanel();
        deptnameField.setBackground(Color.WHITE);
        deptnameField.setLayout(new FlowLayout(15));
        JLabel ldeptName = new JLabel("Department Name: ");
        ldeptName.setFont(font);
        deptnameField.add(ldeptName);
        dept_name = new JTextField();
        dept_name.setFont(font);
        dept_name.setPreferredSize(new Dimension(300,30));
        deptnameField.add(dept_name);
        dept_form_panel.add(deptnameField);
	    
        JPanel deptAdressField = new JPanel();
        deptAdressField.setBackground(Color.WHITE);
        deptAdressField.setLayout(new FlowLayout(15));
        JLabel lDeptAdress = new JLabel("Department Address: ");
        lDeptAdress.setFont(font);
        deptAdressField.add(lDeptAdress);
        dept_address = new JTextField();
        dept_address.setFont(font);
        dept_address.setPreferredSize(new Dimension(300,30));
        deptAdressField.add(dept_address);
        dept_form_panel.add(deptAdressField);

        emptodept = new JLabel();
        emptodept.setFont(font);
        emptodept.setPreferredSize(new Dimension(300,30));
        dept_form_panel.add(emptodept);

        projtodept = new JLabel();
        projtodept.setFont(font);
        projtodept.setPreferredSize(new Dimension(300,30));
        dept_form_panel.add(projtodept);
        
        
        dept_Panel.add(dept_form_panel,BorderLayout.CENTER);
	           
        JPanel deptbtnPanel = new JPanel();
        deptbtnPanel.setBackground(Color.WHITE);
        deptbtnPanel.setLayout(new FlowLayout());
       // deptbtnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JButton deptInsert = new JButton("Insert");
		deptInsert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deptInsert.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		deptInsert.setFocusPainted(false);
		deptInsert.setBorder(new LineBorder(new Color(0,0,0),2,true));
		deptInsert.setHorizontalAlignment(SwingConstants.LEFT);
		deptInsert.setBackground(Color.blue);
		deptInsert.setForeground(Color.white);
		deptInsert.setActionCommand("DepartmentInsert");
		deptInsert.addActionListener(this);
		deptbtnPanel.add(deptInsert);
		
		JButton deptUpdate = new JButton("Update");
		deptUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deptUpdate.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		deptUpdate.setFocusPainted(false);
		deptUpdate.setBorder(new LineBorder(new Color(0,0,0),2,true));
		deptUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		deptUpdate.setBackground(Color.blue);
		deptUpdate.setForeground(Color.white);
		deptUpdate.setActionCommand("DepartmentUpdate");
		deptUpdate.addActionListener(this);
		deptbtnPanel.add(deptUpdate);
		
		JButton deptDelete = new JButton("Delete");
		deptDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		deptDelete.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		deptDelete.setFocusPainted(false);
		deptDelete.setBorder(new LineBorder(new Color(0,0,0),2,true));
		deptDelete.setHorizontalAlignment(SwingConstants.LEFT);
		deptDelete.setBackground(Color.yellow);
		deptDelete.setForeground(Color.black);
		deptDelete.setActionCommand("DepartmentDelete");
		deptDelete.addActionListener(this);
		deptbtnPanel.add(deptDelete);
	
		dept_Panel.add(deptbtnPanel,BorderLayout.SOUTH);
		deptPanel.add(dept_Panel,BorderLayout.NORTH);
		
		deptdata = database.getDepartments();
        String[] dept_columns = new String[]{"Department Id", "Department Name", "Department Address"};
        dept_model = new DefaultTableModel(deptdata,dept_columns);
        dept_table = new JTable(dept_model);
        dept_table.setRowHeight(20);
        dept_table.getTableHeader().setBackground(new Color(80,5,5));
        dept_table.getTableHeader().setForeground(Color.WHITE);
        dept_table.getTableHeader().setFont(new Font("Arial", Font.CENTER_BASELINE,18));
        dept_table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
        dept_table.setFont(font);
        dept_sp = new JScrollPane(dept_table);
        deptPanel.add(dept_sp,BorderLayout.CENTER);
		
		
        
        
        
        
        
        projPanel.setLayout(new BorderLayout());
	    projPanel.setBackground(Color.WHITE);
	     
	    JPanel proj_Panel = new JPanel();
	    proj_Panel.setLayout(new BorderLayout());
	    proj_Panel.setBorder(BorderFactory.createLineBorder(Color.black));
	    proj_Panel.setBackground(Color.WHITE);
        
	    JPanel proj_form_panel = new JPanel();
	    proj_form_panel.setLayout(new BoxLayout(proj_form_panel,BoxLayout.Y_AXIS));
	    proj_form_panel.setPreferredSize(new Dimension(300,300));
	   // proj_form_panel.setBorder(BorderFactory.createLineBorder(Color.black));
	    
	    
        
        JPanel projidField = new JPanel();
        projidField.setBackground(Color.WHITE);
        projidField.setLayout(new FlowLayout(15));
        JLabel lprojId = new JLabel("Project Id: ");
        lprojId.setFont(font);
        projidField.add(lprojId);
        proj_id = new JTextField();
        proj_id.setFont(font);
        proj_id.setPreferredSize(new Dimension(300,30));
        projidField.add(proj_id);
        proj_form_panel.add(projidField);
	    
        JPanel projnameField = new JPanel();
        projnameField.setBackground(Color.WHITE);
        projnameField.setLayout(new FlowLayout(15));
        JLabel lprojName = new JLabel("Project Name: ");
        lprojName.setFont(font);
        projnameField.add(lprojName);
        proj_name = new JTextField();
        proj_name.setFont(font);
        proj_name.setPreferredSize(new Dimension(300,30));
        projnameField.add(proj_name);
        proj_form_panel.add(projnameField);
	    
        JPanel projdeptField = new JPanel();
        projdeptField.setBackground(Color.WHITE);
        projdeptField.setLayout(new FlowLayout(15));
        JLabel lprojDeptId = new JLabel("Department Name: ");
        lprojDeptId.setFont(font);
        projdeptField.add(lprojDeptId);
        
        arr = database.getDepartmentNames();
        proj_deptModel = new DefaultComboBoxModel<>(arr);
        proj_in_Dept = new JComboBox<>(proj_deptModel);
        proj_in_Dept.setFont(font);
        proj_in_Dept.setPreferredSize(new Dimension(300,30));
        projdeptField.add(proj_in_Dept);
        
        proj_form_panel.add(projdeptField);
        
        JPanel projempField = new JPanel();
        projempField.setBackground(Color.WHITE);
        projempField.setLayout(new FlowLayout(10));
        JLabel projlempDesignation = new JLabel("Team Lead Id: ");
        projlempDesignation.setFont(font);
        projempField.add(projlempDesignation);
        proj_empId = new JTextField();
        proj_empId.setFont(font);
        proj_empId.setPreferredSize(new Dimension(300,30));
        projempField.add(proj_empId);
        proj_form_panel.add(projempField);
      
        JPanel totalprojpanel = new JPanel(new BorderLayout());
	    totalProj = new JLabel();
        totalProj.setText("Total Projects in the Comapany : "+ database.getEmployees().length);
        totalProj.setFont(font);
        totalProj.setOpaque(true);
        totalProj.setHorizontalAlignment(JLabel.CENTER);
        totalProj.setBackground(Color.WHITE);
        totalprojpanel.add(totalProj);
        proj_form_panel.add(totalprojpanel);
        
        
        proj_Panel.add(proj_form_panel,BorderLayout.CENTER);
	           
        JPanel projbtnPanel = new JPanel();
        projbtnPanel.setBackground(Color.WHITE);
        projbtnPanel.setLayout(new FlowLayout());
       // projbtnPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        JButton projInsert = new JButton("Insert");
        projInsert.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        projInsert.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
        projInsert.setFocusPainted(false);
        projInsert.setBorder(new LineBorder(new Color(0,0,0),2,true));
        projInsert.setHorizontalAlignment(SwingConstants.LEFT);
        projInsert.setBackground(Color.blue);
        projInsert.setForeground(Color.white);
        projInsert.setActionCommand("ProjectInsert");
        projInsert.addActionListener(this);
        projbtnPanel.add(projInsert);
		
		JButton projUpdate = new JButton("Update");
		projUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		projUpdate.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		projUpdate.setFocusPainted(false);
		projUpdate.setBorder(new LineBorder(new Color(0,0,0),2,true));
		projUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		projUpdate.setBackground(Color.blue);
		projUpdate.setForeground(Color.white);
		projUpdate.setActionCommand("ProjectUpdate");
        projUpdate.addActionListener(this);
		projbtnPanel.add(projUpdate);
		
		JButton projDelete = new JButton("Delete");
		projDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		projDelete.setFont(new Font("Arial",Font.CENTER_BASELINE,15));
		projDelete.setFocusPainted(false);
		projDelete.setBorder(new LineBorder(new Color(0,0,0),2,true));
		projDelete.setHorizontalAlignment(SwingConstants.LEFT);
		projDelete.setBackground(Color.yellow);
		projDelete.setForeground(Color.black);
		projDelete.setActionCommand("ProjectDelete");
        projDelete.addActionListener(this);
		projbtnPanel.add(projDelete);
	
		proj_Panel.add(projbtnPanel,BorderLayout.SOUTH);
		projPanel.add(proj_Panel,BorderLayout.NORTH);
		
		projdata = database.getProjects();
        String[] proj_columns = new String[]{"Project Id", "project Name", "Department Name", "TeamLead Id"};
        proj_model = new DefaultTableModel(projdata,proj_columns);
        proj_table = new JTable(proj_model);
        proj_table.setRowHeight(20);
        proj_table.getTableHeader().setBackground(new Color(80,5,5));
        proj_table.getTableHeader().setForeground(Color.WHITE);
        proj_table.getTableHeader().setFont(new Font("Arial", Font.CENTER_BASELINE,18));
        proj_table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.black));
        proj_table.setFont(font);
        proj_sp = new JScrollPane(proj_table);
        projPanel.add(proj_sp,BorderLayout.CENTER);
        
        imgPanel.add(dashboard);
        add(imgPanel);
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		String cmd = event.getActionCommand();
        dept_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int row = dept_table.getSelectedRow();
                dept_id.setText(dept_model.getValueAt(row,0).toString());
                dept_name.setText(dept_model.getValueAt(row,1).toString());
                dept_address.setText(dept_model.getValueAt(row,2).toString());
                emptodept.setText("Number of Employees in the dpartment: "+database.getEmployeeinDeptartment(database.getDepartment(dept_model.getValueAt(row,1).toString())));
                projtodept.setText("Number of Projects in the department: "+database.getProjectinDeptartment(database.getDepartment(dept_model.getValueAt(row,1).toString())));
            }
        });
        emp_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int row = emp_table.getSelectedRow();
                emp_id.setText(emp_model.getValueAt(row,0).toString());
                e = database.getEmployee(emp_id.getText());
                totalSalary.setText("Total Salary: "+e.getTotalSalary());
                subNames.setText("Number of Subordinates here: "+e.getNumEmployees());
                String str = "";
                List<Employees> list = e.getAllEmployees();
                for(int i=0;i< list.size()-1;i++){
                    str +=list.get(i).name+",";
                }
                str +=list.get(list.size()-1).name;
                subordinaries.setText("Subordinaries: "+str);
                
                emp_name.setText(emp_model.getValueAt(row,1).toString());
                emp_designation.setText(emp_model.getValueAt(row,2).toString());
                emp_in_Dept.setSelectedItem(emp_model.getValueAt(row,3).toString());
                emp_salary.setText(emp_model.getValueAt(row,4).toString());
            }
        });
        proj_table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                int row = proj_table.getSelectedRow();
                proj_id.setText(proj_model.getValueAt(row,0).toString());
                proj_name.setText(proj_model.getValueAt(row,1).toString());
                proj_in_Dept.setSelectedItem(proj_model.getValueAt(row,3).toString());
                proj_empId.setText(proj_model.getValueAt(row,3).toString());
            }
        });
        if(cmd.equals("Departments")){
            dashboard.setSelectedIndex(0);
        } else if (cmd.equals("Employees")) {
        	dashboard.setSelectedIndex(1);
        } else if (cmd.equals("Projects")) {
        	dashboard.setSelectedIndex(2);
        } else if (cmd.equals("DepartmentInsert")) {
            Department d = new Department(dept_id.getText(),dept_name.getText(),dept_address.getText());
            database.addDepartment(d);
            dept_model.addRow(new Object[]{d.deptId,d.name,d.address});
            dept_model.fireTableChanged(null);
            database.refresh();
            System.out.println("Department Created "+d.deptId);
        } else if (cmd.equals("DepartmentUpdate")) {
            int row = dept_table.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select a row to update");
            }
            else{
                d = new Department(dept_id.getText(),dept_name.getText(),dept_address.getText());
                database.updateDepartment(d);
                database.refresh();
                dept_model.setValueAt(dept_id.getText(),row,0);
                dept_model.setValueAt(dept_name.getText(),row,1);
                dept_model.setValueAt(dept_address.getText(),row,2);
                dept_model.fireTableChanged(null);
                arr = database.getDepartmentNames();
                emp_in_Dept.setModel(new DefaultComboBoxModel(arr));
                System.out.println("Department Updated "+ d.deptId);
            }
        } else if (cmd.equals("DepartmentDelete")) {
            int row = dept_table.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select a row to delete");
            }
            else{
                dept_model.removeRow(row);
                database.removeDepartment(dept_id.getText());
                database.refresh();
                dept_model.fireTableChanged(null);
                dept_id.setText("");
                dept_name.setText("");
                dept_address.setText("");
                arr = database.getDepartmentNames();
                emp_in_Dept.setModel(new DefaultComboBoxModel(arr));
                System.out.println("Department Deleted "+d.deptId);
            }
        } else if (cmd.equals("EmployeeInsert")) {
            if(emp_id.getText().equals("Worker")){
                e = new Worker(emp_id.getText(),emp_name.getText(),emp_designation.getText(),database.getDepartment(emp_in_Dept.getSelectedItem().toString()),Integer.parseInt(emp_salary.getText()));
            }else {
                e = new Leader(emp_id.getText(),emp_name.getText(),emp_designation.getText(),database.getDepartment(emp_in_Dept.getSelectedItem().toString()),Integer.parseInt(emp_salary.getText()));
            }
            database.addEmployee(e);
            emp_model.addRow(new Object[]{e.empId,e.name,e.designation,e.dept.name,e.salary});
            database.refresh();
            totalEmp.setText("Total Number of Employees in the Company : "+database.getEmployees().length);
            System.out.println("Employee Created "+e.empId);
        } else if (cmd.equals("EmployeeUpdate")) {
            int row = emp_table.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select a row to update");
            }
            else{
            	if(emp_id.getText().equals("Worker")){
                    e = new Worker(emp_id.getText(),emp_name.getText(),emp_designation.getText(),database.getDepartment(emp_in_Dept.getSelectedItem().toString()),Integer.parseInt(emp_salary.getText()));
                }else {
                    e = new Leader(emp_id.getText(),emp_name.getText(),emp_designation.getText(),database.getDepartment(emp_in_Dept.getSelectedItem().toString()),Integer.parseInt(emp_salary.getText()));
                }
                database.updateEmployee(e);
                database.refresh();
                emp_model.setValueAt(emp_id.getText(),row,0);
                emp_model.setValueAt(emp_name.getText(),row,1);
                emp_model.setValueAt(emp_designation.getText(),row,2);
                emp_model.setValueAt(emp_in_Dept.getSelectedItem(),row,3);
                emp_model.setValueAt(emp_salary.getText(),row,4);
                emp_model.fireTableChanged(null);
            }
        } else if (cmd.equals("EmployeeDelete")) {
            int row = emp_table.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select a row to delete");
            }
            else{
                emp_model.removeRow(row);
                database.removeEmployee(emp_id.getText());
                database.refresh();
                totalEmp.setText("Total Number of Employees in the company : "+database.getEmployees().length);
                emp_model.fireTableChanged(null);
                emp_id.setText("");
                emp_name.setText("");
                emp_designation.setText("");
                emp_salary.setText("");
            }
        } else if (cmd.equals("ProjectInsert")) {
            p = new Project(proj_id.getText(),proj_name.getText(),database.getDepartment(proj_in_Dept.getSelectedItem().toString()),proj_empId.getText());
            database.addProject(p);
            database.refresh();
            totalProj.setText("Total Projects in the company : "+database.getProjects().length);
            proj_model.addRow(new Object[]{p.projId,p.name,p.dept.name,p.empId});
            proj_model.fireTableChanged(null);
        } else if (cmd.equals("ProjectUpdate")) {
            int row = proj_table.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select a row to update");
            }
            else{
                p = new Project(proj_id.getText(),proj_name.getText(),database.getDepartment(proj_in_Dept.getSelectedItem().toString()),proj_empId.getText());
                database.updateProject(p);
                database.refresh();
                proj_model.setValueAt(proj_id.getText(),row,0);
                proj_model.setValueAt(proj_name.getText(),row,1);
                proj_model.setValueAt(proj_in_Dept.getSelectedItem(),row,2);
                proj_model.setValueAt(proj_empId.getText(),row,3);
                proj_model.fireTableChanged(null);
            }
        } else if (cmd.equals("ProjectDelete")) {
            int row = proj_table.getSelectedRow();
            if(row == -1){
                JOptionPane.showMessageDialog(this,"Select a row to delete");
            }
            else{
                proj_model.removeRow(row);
                database.removeProject(proj_id.getText());
                database.refresh();
                totalProj.setText("Total Projects in the company : "+database.getProjects().length);
                proj_model.fireTableChanged(null);
                proj_id.setText("");
                proj_name.setText("");
                proj_empId.setText("");
            }
        }
    }
}
