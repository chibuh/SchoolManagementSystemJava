package OopsProject;

import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.ActionListener;

import javax.swing.*;

//import oops.eclipse.java.first.Teacher;

public class TeacherGUI implements ActionListener{

	private static JPanel panel3;
	private static JFrame frame3;
	private static JButton button1,logout,Holibutton;
	private static JButton button4Pass,button4,button,button2,button3;
	private static JTextField adCourseIDText,confirmPassText,changePassText,marksText,attendText,studText;
	private int loginID;
	
	public void main(Teacher t) {
		loginID = Integer.parseInt(t.getId());
		panel3 = new JPanel();
		frame3 = new  JFrame();       //window
		frame3.setSize(400,500);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.add(panel3);
		panel3.setLayout(null);
		

		  JLabel logo = new JLabel();
	      logo.setIcon(new ImageIcon("C:\\Users\\Lenovo\\eclipse-workspace\\OopsProject\\src\\OopsProject\\TeacherPic.png"));
	      panel3.add(logo);
	      logo.setBounds(20, 10, 100, 100);
	      

		     JLabel stdID = new JLabel("T"+t.getId());      //++t.getID()++
		     stdID.setBounds(20, 70, 100, 100); 
		     panel3.add(stdID);
		     JLabel stdName = new JLabel(t.getName());		//++t.getName()++
		     stdName.setBounds(20, 90, 100, 100); 
		     panel3.add(stdName);
		     	     
		    button = new JButton("See Details");
			button.setBounds(160, 20, 180, 25);
			panel3.add(button);
			
			Holibutton = new JButton("Apply Holiday");
			Holibutton.setBounds(160, 70, 180, 25);
			panel3.add(Holibutton);
				
				button1 = new JButton("Track");
				button1.setBounds(60, 170, 70, 25);
				panel3.add(button1);
				
				studText = new JTextField(20);
				studText.setBounds(140,170,60,25);
				panel3.add(studText);
				studText.setText("S");
				
				attendText = new JTextField(20);
				attendText.setBounds(210,170,70,25);
				panel3.add(attendText);
				attendText.setText("attendance");
				
				marksText = new JTextField(20);
				marksText.setBounds(290,170,50,25);
				panel3.add(marksText);
				marksText.setText("marks");
				
			//
				button2 = new JButton("View Course Details");
				button2.setBounds(60, 220, 280, 25);
				panel3.add(button2);
				
			//--
				button3 = new JButton("Add Course");
				button3.setBounds(60, 270, 150, 25);
				panel3.add(button3);
				
				adCourseIDText = new JTextField(20);
				adCourseIDText.setBounds(220,270,120,25);
				panel3.add(adCourseIDText);
				
			//--
	//Change Password.................................................		
				button4 = new JButton("Change Password");
				button4.setBounds(60, 320, 150, 25);
				panel3.add(button4);
				
				changePassText = new JTextField(20);
				changePassText.setBounds(220,320,120,25);
				panel3.add(changePassText);
				
				confirmPassText = new JTextField(20);
				confirmPassText.setBounds(220,350,120,25);
				panel3.add(confirmPassText);
				
				changePassText.setText("Enter new password!");
				confirmPassText.setText("Confirm password!");
	//................................................................		
				
				logout = new JButton("Log Out");
				logout.setBounds(160, 400, 80, 25);
				panel3.add(logout);
				

				button.addActionListener(this);
				Holibutton.addActionListener(this);
				button1.addActionListener(this);
				button2.addActionListener(this);
				button3.addActionListener(this);
				button4.addActionListener(this);

				logout.addActionListener(this);
				

		
		frame3.setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent evt)
	{
	     if (evt.getSource()==button1)
	         {
	    	 	String addStud = studText.getText();
	    	 	String loginIDstud= addStud.substring(addStud.length()-(addStud.length()-1));
	            int marks= 	Integer.parseInt(marksText.getText());
	            int attendance=	Integer.parseInt(attendText.getText());
	            
	            DBConnection conn = new DBConnection();
	            //System.out.println(loginID);
	            //conn.update("update logs set C"+loginID+"Attendance=, C"+loginID+"Marks) values("+loginID+","+attendance+","+marks+"); ");
	            ResultSet rs = conn.select("select * from teachertable where teacherID="+loginID);
	            String courseName = null;
				try {
					rs.next();
					courseName = rs.getString("CourseTaught");
				} catch (SQLException e) {
					System.out.println(e);
				}
	            ResultSet rs1 = conn.select("select courseID from coursetable where courseName='"+courseName+"';");
	            String courseID = null;
				try {
					rs1.next();
					courseID = rs1.getString("courseID");
				} catch (SQLException e) {
					System.out.println(e);
				}
	            conn.update("update logs set C"+courseID+"Attendance="+attendance+" where studentID ="+Integer.parseInt(loginIDstud)+";");
	            conn.update("update logs set C"+courseID+"Marks="+marks+" where studentID ="+Integer.parseInt(loginIDstud)+";");
	            

			    JOptionPane.showMessageDialog(frame3,"Added records of Student"); 
	         } 
	     
	     else if (evt.getSource()==button2)
	         {
	            ;//do something
	         }	     
	     else if (evt.getSource()==button4Pass)
         {
            String newPass1 = changePassText.getText();
            String newPass2 = confirmPassText.getText();
            if(newPass1.equals(newPass2)) {
            	if(newPass1.isEmpty()) {
//            		changePassText.setText("Can't be empty!");
//            		confirmPassText.setText("");
    			    JOptionPane.showMessageDialog(frame3,"Password can't be EMPTY!"); 
            	}
            	else {
            		//Change Password in sql

            		DBConnection conn = new DBConnection();
            		conn.update("update teachertable set teacherPass='"+newPass2+"' where teacherID='"+loginID+"'");
            		
//            		changePassText.setText("CHANGED!");
    			    JOptionPane.showMessageDialog(frame3,"Password CHANGED!");
    			    
            		confirmPassText.setText("");
            	}
            }
            else {
//            	confirmPassText.setText("Doesn't Matches");
			    JOptionPane.showMessageDialog(frame3,"Password can't be EMPTY!"); 
            }
            
         }
	     
	     else if(evt.getSource()==Holibutton) {
	    	 int holidaysTaken = 0;
	    	 DBConnection conn = new DBConnection();
	    	 ResultSet rs = conn.select("select * from teachertable where teacherId="+loginID+";");
	    	 try {
	    		 rs.next();
	    		 holidaysTaken = rs.getInt("HolidaysTaken");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 Teacher t = new Teacher();
	    	 if(t.applyHoliday(holidaysTaken, loginID))	JOptionPane.showMessageDialog(frame3,"Holiday Granted!");
	    	 else	JOptionPane.showMessageDialog(frame3,"Holidays exceeded! Application Denied!");
	     }
	     
	     
	     else if (evt.getSource()==logout)
         {
				frame3.setVisible(false);
				
			LoginGUI lg = new LoginGUI();
			lg.main();
         }
	}

}
