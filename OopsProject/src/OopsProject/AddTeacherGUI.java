package OopsProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//import oops.eclipse.java.first.Teacher;

import java.sql.*;

public class AddTeacherGUI implements ActionListener{
	
	private static JPanel panel2;
	private static JFrame frame2;
	private static JButton button;
	private static JTextField contactText,dobText,nameText,classText;
	private static JComboBox<String> sex,section;
	
	String teacherId = null ;    // set using SQL
	int currID;
	
	DBConnection conn = new DBConnection();
	ResultSet rs = conn.select("select max(teacherID) from teachertable;");
	{
	try {
		rs.next();
		currID = rs.getInt("max(teacherID)");
		currID++;
		teacherId ="S"+currID;
	}
	catch(SQLException ex)
	{
		System.out.println(ex);		
	}
	}
	
	public void main() {
		panel2 = new JPanel();
		frame2 = new  JFrame("Add Teacher");       //window
		frame2.setSize(400,400);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(panel2);
		panel2.setLayout(null);
		
		JLabel labelid = new JLabel("LoginID    " + ":  T"+currID);		
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
		
		String[] coursesOption = {"Male", "Female", "Prefer not to say"};
		sex = new JComboBox<>(coursesOption);
        sex.setBounds(100, 230, 120, 25);
        panel2.add(sex);
//	
        JLabel label4 = new JLabel("Course");	
		label4.setBounds(30, 270, 80, 25);
		panel2.add(label4);	
		
		int num = 0;    //no of column in Course table
		DBConnection conn = new DBConnection();
		ResultSet rs = conn.select("select count(courseID) from coursetable");
		try {
			rs.next();
			num = rs.getInt("count(courseID)");
		} 
		catch (SQLException e) {
			System.out.println(e);
		}
        String[] sectionOption = new String[num];
        
        ResultSet rs1 = conn.select("select courseName from coursetable");
        try {
			int i=0;
			while(rs1.next())
			{
				sectionOption[i] = rs1.getString("courseName");
				i++;
			}
		} 
        catch (SQLException e) {
			System.out.println(e);
		}
        
        section = new JComboBox<>(sectionOption);
        section.setBounds(100, 270, 120, 25);
        panel2.add(section);
		
//        classText = new JTextField(20);		
//		classText.setBounds(100,270,50,25);
//		panel2.add(classText);
//
		button = new JButton("Add Teacher");					
		button.setBounds(50, 310, 120, 25);
		panel2.add(button);
		button.addActionListener(new AddTeacherGUI());
        
		
		
		
		frame2.setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent e) {
		String teacherID;    //
		
		String teacherPass="default";  
		
//		String name;
//		String dOB;
//		char gender;
//		String Contact;
////	int HolidaysTaken;
		
		String courseTaught = section.getItemAt(section.getSelectedIndex());;
		
		String name = nameText.getText();
		String dOB = dobText.getText();
		char gender;
		String contact = contactText.getText();
//		int standard = Integer.parseInt(classText.getText());
		 
		
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
		
		Teacher tnew = new Teacher(currID,teacherPass,name,dOB,gender,contact,courseTaught);

	    JOptionPane.showMessageDialog(frame2,"Added Teacher "+ tnew.getName()); 
	    frame2.setVisible(false);	
	}

}
