package OopsProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class LoginGUI extends SchoolManagement implements ActionListener {
	private static JPanel panel;
    private static JFrame frame;
    private static JTextField userText;
    private static JPasswordField passText;
    private static JButton button;
    
    public void main() {
    	
//...............................................Frame1................................................................................
	    panel = new JPanel();       //for layout
		
		frame = new  JFrame("LogIn");       //window
		frame.setSize(500,400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.add(panel);
		panel.setLayout(null);
		
//BITSlogo
		  JLabel logo = new JLabel();
	      logo.setIcon(new ImageIcon("C:\\Users\\Lenovo\\eclipse-workspace\\OopsProject\\src\\OopsProject\\bitsicon1.png"));
	      panel.add(logo);
	      logo.setBounds(125, 0, 250, 230);
	       
//user
		JLabel label = new JLabel("User");		//Text Label
		label.setBounds(50, 240, 80, 25);
		panel.add(label);		//add label to Panel
		
		userText = new JTextField(20);		//writing box
		userText.setBounds(125,240,225,25);
		panel.add(userText);
		
		
//password		
		JLabel passLabel = new JLabel("Password");		
		passLabel.setBounds(50, 270, 80, 25);
		panel.add(passLabel);
		
		passText = new JPasswordField(20);		//password writing box
		passText.setBounds(125,270,225,25);
		panel.add(passText);
		
//Login Button		
		button = new JButton("Login");					
		button.setBounds(50, 310, 70, 25);
		panel.add(button);
				
		button.addActionListener(new LoginGUI());
		
		frame.setVisible(true);		
//..................................................END Frame1..........................................................................
	        
	        
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
			String userID = userText.getText();
			String password = passText.getText();
			String curPass = null;
			
			DBConnection conn = new DBConnection();
			
			userText.setText("");
			passText.setText("");
			
			if(userID.equals("A000"))	{
				try {
						ResultSet rs = conn.select("select adPass from admintable where adID='A000'");
						while(rs.next()) {
							curPass = rs.getString("adPass");
							//System.out.println(curPass);
						}
				}
				catch(SQLException ex) {
					System.out.println(ex);
				}
				if(password.equals(curPass)) {
					frame.setVisible(false);
				
					AdminGUI adgui = new AdminGUI();
					adgui.main();
				}
				else {
					userText.setText("INVALID PASSWORD");
				}
			}
			else if(userID.charAt(0)=='T') {
				
				String IDlast = userID.substring(userID.length()-(userID.length()-1));
				int ID = Integer.parseInt(IDlast);
				ResultSet rs = conn.select("select teacherPass from teachertable where teacherID="+ID);
				
				try {
					rs.next();
					if(password.equals(rs.getString("teacherPass"))) {
						frame.setVisible(false);
					
						ResultSet rs1 = conn.select("select * from teachertable where teacherID="+ID);
						rs1.next();
						String tloginID = rs1.getString("teacherID");
						String password2 = rs1.getString("teacherPass");
						String name = rs1.getString("name");
						String dOB = rs1.getString("dOB");
						char gender = rs1.getString("Gender").charAt(0);
						String contact = rs1.getString("contact");
						int holidaysTaken = rs1.getInt("holidaysTaken");
						String coursesTaught = rs1.getString("CourseTaught");
						int salary = rs1.getInt("Salary");
						
						TeacherGUI tgui = new TeacherGUI();
						Teacher teach = new Teacher(tloginID,password2,name,dOB,gender,contact,holidaysTaken,coursesTaught,salary);  
						tgui.main(teach);
					}
					else {
						JOptionPane.showMessageDialog(frame,"Incorrect LoginID or Password."); 
					}
				} 
				catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			else if(userID.charAt(0)=='S') {
				
				String IDlast = userID.substring(userID.length()-(userID.length()-1));
				int ID = Integer.parseInt(IDlast);
				ResultSet rs = conn.select("select studentPass from studenttable where studentID="+ID);
				
				try {
					rs.next();
					if(password.equals(rs.getString("studentPass"))) {
						frame.setVisible(false);
					
						ResultSet rs1 = conn.select("select * from studenttable where studentID="+ID);
						rs1.next();
						String loginID = rs1.getString("studentID");
						String password1 = rs1.getString("studentPass");
						String name = rs1.getString("name");
						String dOB = rs1.getString("dOB");
						char gender = rs1.getString("gender").charAt(0);
						String contact = rs1.getString("contact");
						int standard = rs1.getInt("standard");
						char section = rs1.getString("section").charAt(0);
						int holidaysTaken = rs1.getInt("holidaysTaken");
						String coursesTaken = rs1.getString("CoursesTaken");
						
						StudentGUI sgui = new StudentGUI();
						Student stud = new Student(loginID, password1 , name,  dOB,  gender,  contact, standard,  section,  holidaysTaken,  coursesTaken);
						sgui.main(stud);
					}
					else {
						JOptionPane.showMessageDialog(frame,"Incorrect LoginID or Password."); 
					}
				} 
				catch (SQLException ex) {
					System.out.println(ex);
					JOptionPane.showMessageDialog(frame,"Incorrect LoginID or Password.");
				}
			}
			else {
				JOptionPane.showMessageDialog(frame,"Incorrect LoginID or Password."); 
			}	
	}
}

