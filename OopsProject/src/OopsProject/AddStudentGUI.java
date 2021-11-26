package OopsProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.sql.*;

public class AddStudentGUI implements ActionListener{

	private static JPanel panel2;
	private static JFrame frame2;
	private static JButton button;
	private static JTextField contactText,dobText,nameText,classText;
	private static JComboBox<String> sex,section;
	String studentId = null ;    // set using SQL
	int currID;
	
	DBConnection conn = new DBConnection();
	ResultSet rs = conn.select("select max(studentID) from studenttable;");
	{
	try {
		rs.next();
		currID = rs.getInt("max(studentID)");
		currID++;
		studentId ="S"+currID;
	}
	catch(SQLException ex)
	{
		System.out.println(ex);		
	}
	}
	
	
	public void main() {
		panel2 = new JPanel();
		frame2 = new  JFrame("Add Student");       //window
		frame2.setSize(400,400);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(panel2);
		panel2.setLayout(null);
		
		JLabel labelid = new JLabel("LoginID    " + ":  "+studentId);		
		labelid.setBounds(40, 20, 200, 25);
		panel2.add(labelid);
		
		JLabel labelpass = new JLabel("Password : "+" default");		
		labelpass.setBounds(40, 50, 200, 25);
		panel2.add(labelpass);	
		
//		
		JLabel label = new JLabel("Name");	
		label.setBounds(40, 90, 80, 25);
		panel2.add(label);		
		
		nameText = new JTextField(20);		
		nameText.setBounds(100,90,225,25);
		panel2.add(nameText);
//	
		JLabel label1 = new JLabel("D.O.B.");	
		label1.setBounds(40, 140, 80, 25);
		panel2.add(label1);			   
		
		dobText = new JTextField(20);		
		dobText.setBounds(100,140,225,25);
		panel2.add(dobText);
//		
		JLabel label2 = new JLabel("Contact");		
		label2.setBounds(40, 180, 80, 25);
		panel2.add(label2);		
		
		contactText = new JTextField(20);		
		contactText.setBounds(100,180,225,25);
		panel2.add(contactText);
//	
		JLabel label3 = new JLabel("Gender");	
		label3.setBounds(40, 230, 80, 25);
		panel2.add(label3);		
		
		String[] genderoption = {"Male", "Female", "Prefer not to say"};
		sex = new JComboBox<>(genderoption);
        sex.setBounds(100, 230, 120, 25);
        panel2.add(sex);
//	
        JLabel label4 = new JLabel("Class/Sec");	
		label4.setBounds(30, 270, 80, 25);
		panel2.add(label4);	
		
        String[] sectionOption = {"A","B","C","D","E"};
        section = new JComboBox<>(sectionOption);
        section.setBounds(160, 270, 60, 25);
        panel2.add(section);
		
        classText = new JTextField(20);		
		classText.setBounds(100,270,50,25);
		panel2.add(classText);
//
		button = new JButton("Add Student");					
		button.setBounds(50, 310, 120, 25);
		panel2.add(button);
		button.addActionListener(new AddStudentGUI());
        
		frame2.setVisible(true);	
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		
		String studentPass="default";  
		String name = nameText.getText();
		String dOB = dobText.getText();
		char gender;
		String contact = contactText.getText();
//		int HolidaysTaken;
		int standard = Integer.parseInt(classText.getText());
		String section1 = section.getItemAt(section.getSelectedIndex());
		
		String genderType = sex.getItemAt(sex.getSelectedIndex());
		if(genderType.equals("Male")) {
			gender='M';
		}
		else if(genderType.equals("Female")) {
			gender='F';
		}
		else	{
			gender='X';
		}
				
		
		Student stnew = new Student(currID,studentPass,name,dOB,gender,contact,standard,section1.charAt(0));
						

	    JOptionPane.showMessageDialog(frame2,"Added Student "+ stnew.getName()); 
	    frame2.setVisible(false);
	    
	}
}


