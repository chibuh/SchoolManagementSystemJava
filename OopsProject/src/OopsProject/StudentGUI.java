package OopsProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;

public class StudentGUI implements ActionListener{

	private static JPanel panel3;
	private static JFrame frame3;
	private static JButton button1,logout,Holibutton;
	private static JButton button4Pass,button4,button,button2,button3;
	private static JTextField adCourseIDText,confirmPassText,changePassText,coursText;
	private int loginID;
//	private S noSID;
	
	public void main(Student st) {
		panel3 = new JPanel();
		frame3 = new  JFrame();       //window
		frame3.setSize(400,500);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.add(panel3);
		panel3.setLayout(null);
		
		loginID = Integer.parseInt(st.getId());
		
		
		  JLabel logo = new JLabel();
	      logo.setIcon(new ImageIcon("C:\\Users\\Lenovo\\eclipse-workspace\\OopsProject\\src\\OopsProject\\StudentPic.png"));
	      panel3.add(logo);
	      logo.setBounds(20, 10, 100, 100);
	      
	     JLabel stdID = new JLabel("S"+st.getId());      //++st.getID()++
	     stdID.setBounds(20, 70, 100, 100); 
	     panel3.add(stdID);
	     JLabel stdName = new JLabel(st.getName());		//++st.getName()++
	     stdName.setBounds(20, 90, 100, 100); 
	     panel3.add(stdName);
	     	     
	    button = new JButton("See Details");
		button.setBounds(160, 20, 180, 25);
		panel3.add(button);
		
		Holibutton = new JButton("Apply Holiday");
		Holibutton.setBounds(160, 70, 180, 25);
		panel3.add(Holibutton);
			
			button1 = new JButton("Modify Details");
			button1.setBounds(60, 170, 280, 25);
			panel3.add(button1);
			
			button2 = new JButton("Course Details");
			button2.setBounds(60, 220, 140, 25);
			panel3.add(button2);
			coursText = new JTextField(20);
			coursText.setBounds(220,220,120,25);
			panel3.add(coursText);
		//--
			button3 = new JButton("Add Course");
			button3.setBounds(60, 270, 150, 25);
			panel3.add(button3);
			
			adCourseIDText = new JTextField(20);
			adCourseIDText.setBounds(220,270,120,25);
			panel3.add(adCourseIDText);
		//--
//Change Password.................................................		
			button4Pass = new JButton("Change Password");
			button4Pass.setBounds(60, 320, 150, 25);
			panel3.add(button4Pass);
			
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
			button4Pass.addActionListener(this);

			logout.addActionListener(this);
			
		frame3.setVisible(true);	
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
	    	 	String courseIDview = coursText.getText();
	    	 	String msg = null;
	    	 	int courseid = Integer.parseInt(courseIDview.substring(courseIDview.length()-(courseIDview.length()-1)));
	    	 	//System.out.println(loginID);
	    	 	DBConnection conn = new DBConnection();
	    	 	ResultSet rs = conn.select("select * from logs where studentID="+loginID+";");
	    	 	try {
	    	 		rs.next();

    			    msg = "Marks: "+rs.getInt(2*courseid+1);
//					System.out.println("Marks: "+rs.getInt(2*courseid+1));
				} catch (SQLException e) {
					System.out.println(e);
				}
	    	 	try {

    			    msg = msg + "\n Attendance: "+rs.getInt(2*courseid);

    			    JOptionPane.showMessageDialog(frame3,msg);
				} catch (SQLException e) {
					System.out.println(e);
				}
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
            		conn.update("update studenttable set studentPass='"+newPass2+"' where studentID="+loginID+");");
            		
//            		changePassText.setText("CHANGED!");
    			    JOptionPane.showMessageDialog(frame3,"Password CHANGED!");
    			    
            	}
            }
            else {
//            	confirmPassText.setText("Doesn't Matches");
			    JOptionPane.showMessageDialog(frame3,"Password can't be EMPTY!"); 
            }

    		confirmPassText.setText("");
    		changePassText.setText("");
         }
	     
	     else if(evt.getSource()==Holibutton) {
	    	 int holidaysTaken = 0;
	    	 DBConnection conn = new DBConnection();
	    	 ResultSet rs = conn.select("select * from studenttable where studentId="+loginID+";");
	    	 try {
	    		 rs.next();
	    		 holidaysTaken = rs.getInt("HolidaysTaken");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 Student s = new Student();
	    	 if(s.applyHoliday(holidaysTaken, loginID))	JOptionPane.showMessageDialog(frame3,"Holiday Granted!");
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
