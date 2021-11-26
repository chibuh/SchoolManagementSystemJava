package OopsProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AdminGUI extends SchoolManagement implements ActionListener{

	private static JPanel panel1;
	private static JFrame frame1;
	private static JButton button1;
	private static JButton button2,button2C,button2mod,button1mod;
	private static JButton button3;
	private static JTextField delUserIDText;
	private static JButton button4;
	private static JTextField changePassText, confirmPassText,addCourseText;
	private static JButton logout;
	
	
	public void main() {
		// TODO Auto-generated method stub
		
		panel1 = new JPanel();
		frame1 = new  JFrame("Admin");       //window
		frame1.setSize(400,500);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.add(panel1);
		panel1.setLayout(null);
		
		
		button1 = new JButton("Add Student");
		button1.setBounds(60, 20, 280, 25);
		panel1.add(button1);
		
		button2 = new JButton("Add Teacher");
		button2.setBounds(60, 70, 280, 25);
		panel1.add(button2);
		
		button2C = new JButton("Add Course");
		button2C.setBounds(60, 120, 150, 25);
		panel1.add(button2C);

		addCourseText = new JTextField(20);
		addCourseText.setBounds(220,120,120,25);
		panel1.add(addCourseText);
		
		button1mod = new JButton("Modify Student");     //
		button1mod.setBounds(60, 170, 280, 25);
		panel1.add(button1mod);
		
		button2mod = new JButton("Modify Teacher");		//
		button2mod.setBounds(60, 220, 280, 25);
		panel1.add(button2mod);
		
		
		
		button3 = new JButton("Delete ID");
		button3.setBounds(60, 270, 150, 25);
		panel1.add(button3);
		
		delUserIDText = new JTextField(20);
		delUserIDText.setBounds(220,270,120,25);
		panel1.add(delUserIDText);
		
//Change Password.................................................		
		button4 = new JButton("Change Password");
		button4.setBounds(60, 320, 150, 25);
		panel1.add(button4);
		
		changePassText = new JTextField(20);
		changePassText.setBounds(220,320,120,25);
		panel1.add(changePassText);
		
		confirmPassText = new JTextField(20);
		confirmPassText.setBounds(220,350,120,25);
		panel1.add(confirmPassText);
		
		changePassText.setText("Enter new password!");
		confirmPassText.setText("Confirm password!");
//................................................................		
		
		logout = new JButton("Log Out");
		logout.setBounds(160, 400, 80, 25);
		panel1.add(logout);
		
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		button3.addActionListener(this);
		button4.addActionListener(this);
		button2C.addActionListener(this);
		logout.addActionListener(this);
		
		frame1.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent evt)
	{
	     if (evt.getSource()==button1)
	         {
	            AddStudentGUI adst= new AddStudentGUI();
	            adst.main();
	         } 
	     
	     else if (evt.getSource()==button2)
	         {
	    	 	AddTeacherGUI adtc= new AddTeacherGUI();
	            adtc.main();
	         }
	     
	     else if (evt.getSource()==button3)
	         {
				String addeleteID= delUserIDText.getText();
				String deltype =null;
				String IDlast = addeleteID.substring(addeleteID.length()-(addeleteID.length()-1));
				int delID = Integer.parseInt(IDlast);
				DBConnection conn = new DBConnection();
				
				if(addeleteID.charAt(0)=='C'){
					deltype = "Course";
					if(conn.delete("delete from coursetable where courseID ="+delID)) {
						
						conn.update("alter table logs drop column C"+delID+"Attendance;");
						conn.update("alter table logs drop column C"+delID+"Marks");
						conn.update("update teachertable set courseTaught=null where courseTaught="+delID);
						JOptionPane.showMessageDialog(frame1,deltype+" "+addeleteID+" deleted!");
						delUserIDText.setText("");
					}
					else{
						JOptionPane.showMessageDialog(frame1,"Invalid ID!");
						delUserIDText.setText("");
					}
					
				}
				else  if(addeleteID.charAt(0)=='S')	{
					deltype = "Student";
					if(conn.delete("delete from studenttable where studentID ="+delID)) {
						
						conn.delete("delete from logs where studentID ="+delID);
						JOptionPane.showMessageDialog(frame1,deltype+" "+addeleteID+" deleted!");
						delUserIDText.setText("");
					}
					else{
						JOptionPane.showMessageDialog(frame1,"Invalid ID!");
						delUserIDText.setText("");
					}
				}
				else  if(addeleteID.charAt(0)=='T')	{
					deltype = "Teacher";
					if(conn.delete("delete from teachertable where teacherID ="+delID)) {
						
						JOptionPane.showMessageDialog(frame1,deltype+" "+addeleteID+" deleted!");
						delUserIDText.setText("");
					}
					else{
						JOptionPane.showMessageDialog(frame1,"Invalid ID!");
						delUserIDText.setText("");
					}
				}
				else	 JOptionPane.showMessageDialog(frame1,"Invalid ID!");
				
			    
				delUserIDText.setText("");
	         }
	     
	     else if (evt.getSource()==button4)
         {
            String newPass1 = changePassText.getText();
            String newPass2 = confirmPassText.getText();
            if(newPass1.equals(newPass2)) {
            	if(newPass1.isEmpty()) {

    			    JOptionPane.showMessageDialog(frame1,"Password can't be EMPTY!"); 
            		confirmPassText.setText("");
            	}
            	else {
            		adm.setAdPass(newPass1);
            		
            		DBConnection conn = new DBConnection();
            		conn.update("update admintable set adPass='"+newPass2+"' where adID='A000'");
            		
    			    JOptionPane.showMessageDialog(frame1,"Password CHANGED!"); 
            	}
            }
            else {
//            	confirmPassText.setText("Doesn't Matches");
			    JOptionPane.showMessageDialog(frame1,"Password doesn't MATCH!"); 
            }
            
         }
	     
	     else if (evt.getSource()==logout)
         {
			    JOptionPane.showMessageDialog(frame1,"Logged Out Successfully!"); 
				frame1.setVisible(false);
				
			LoginGUI lg = new LoginGUI();
			lg.main();
         }
	     
	     else if (evt.getSource()==button2C) {
	    	String courseName = addCourseText.getText();
	    	int currID;
	    	String courseId=null;
	    	DBConnection conn = new DBConnection();
	    	ResultSet rs = conn.select("select max(courseID) from coursetable;");
	    	{
	    	try {
	    		rs.next();
	    		currID = rs.getInt("max(courseID)");
	    		currID++;
	    		courseId ="C"+currID;
	    		Courses course = new Courses(courseName,currID);
	    	}
	    	catch(SQLException ex)
	    	{
	    		System.out.println(ex);		
	    	}
	    	}
	    	addCourseText.setText("");
	    	JOptionPane.showMessageDialog(frame1,"Course "+ courseName+": "+courseId+" added!"); 
	     }

			confirmPassText.setText("");
			changePassText.setText("");
			
	}

}
