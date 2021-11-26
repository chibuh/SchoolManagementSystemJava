package OopsProject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;


public class SchoolManagement{
	
    public Admin adm = new Admin();
    public static ArrayList<Student> student = new ArrayList<Student>();
    public static ArrayList<Teacher> teacher = new ArrayList<Teacher>();
    public static ArrayList<Courses> courses = new ArrayList<Courses>();
    
	public static void main(String[] args) {
		
	 //Adding Sample Students
	    Student std = new Student();
	    student.add(std);
	    
	 //Adding Sample Teachers
//	    Teacher tec = new Teacher();
//	    teacher.add(tec);
	    
	    
	    LoginGUI loggui = new LoginGUI();
		loggui.main();
    
	        
	}
		
}











//class User{
//	int id;
//	String password;
//	Date dt;
//	int phoneNumber;
//	String address;
//	char sex;
//	
//	int getId() {
//		return id;
//	}
//	String getPassword() {
//		return password;
//	}
//	Date getDOB() {
//		return dt;
//	}
//	int getPhone() {
//		return phoneNumber;
//	}
//	String getAddress() {
//		return address;
//	}
//	char getSex() {
//		return sex;
//	}
//	
//	void setId(int id) {
//		this.id=id;
//	}
//	void setPassword(String password) {
//		this.password=password;
//	}
//	void set() {
//		
//	}
//	
//}


