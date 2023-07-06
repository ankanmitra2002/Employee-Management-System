package employee;

import javax.management.OperationsException;
import javax.management.loading.PrivateClassLoader;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.PasswordAuthentication;

public class LoginPage extends JFrame implements ActionListener{
	
	JTextField username;
	JPasswordField passcode;
	
	public LoginPage(){
		setTitle("Ankan's Employee Management System");
		setSize(1400,700);
		setLocation(40,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getRootPane().setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.black));
		getContentPane().setBackground(Color.WHITE);	
		components();
		
	}
	private void components() {
		JLabel jl = new JLabel("THE EMPLOYEE MANAGEMENT SYSTEM",JLabel.CENTER);
		jl.setFont(new Font("Arial",Font.CENTER_BASELINE,40));
		jl.setForeground(Color.DARK_GRAY);	
		add(jl,BorderLayout.NORTH);
		
		JPanel imgpanel = new JPanel(new BorderLayout());
		
		ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Loginpage.jpg"));
		Image img = icon.getImage().getScaledInstance(700,800,Image.SCALE_AREA_AVERAGING);
		ImageIcon newIcon = new ImageIcon(img);
		JLabel imageLabel = new JLabel(newIcon);
				
		imageLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		imgpanel.add(imageLabel);
		add(imgpanel,BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel userId = new JLabel("User Id");
		userId.setFont(new Font("Arial",Font.BOLD,20));
		userId.setBounds(90,200,100,30);
		panel.add(userId);
		username =  new JTextField("",15);
		username.setBounds(90,230,200,30);
		panel.add(username);
		
		JLabel password = new JLabel("Password");
		password.setFont(new Font("Arial",Font.BOLD,20));
		password.setBounds(90,265,100,30);
		panel.add(password);
		passcode =  new JPasswordField("",10);
		passcode.setBounds(90,295,200,30);
		panel.add(passcode);
		
		JButton button = new JButton("Login");
		button.setBorder(BorderFactory.createEmptyBorder());
		button.setBounds(90,330,200,30);
		button.setFocusPainted(false);
		button.setBackground(Color.blue);
		button.setForeground(Color.white);
		button.addActionListener(this);
		panel.add(button);
		
		JButton exit = new JButton("Exit");
		exit.setBorder(BorderFactory.createEmptyBorder());
		exit.setBounds(90,370,200,30);
		exit.setFocusPainted(false);
		exit.setBackground(Color.blue);
		exit.setForeground(Color.white);
		exit.addActionListener(this);
		panel.add(exit);
		
		add(panel,BorderLayout.CENTER);
	}
	public static void main(String[] args) {
		
		LoginPage front = new LoginPage();
		front.setVisible(true);	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cmd = e.getActionCommand();
		if(cmd.equals("Login"))
		{
			String check1 = username.getText();
			String check2 = passcode.getText();
			if((check1.equals("admin")) && (check2.equals("admin123"))) {
				setVisible(false);
				dispose();
				new Operation();
			}	
			else {
				JOptionPane.showMessageDialog(null,"Incorrect username or password.Please login again","Error Message",JOptionPane.ERROR_MESSAGE);	
			}
		}
		else if(cmd.equals("Exit")) {
			System.exit(0);
		}
		
	}
}

