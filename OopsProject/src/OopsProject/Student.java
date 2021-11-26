package OopsProject;

import java.util.*;
import java.sql.*;

public class Student extends User{
	final static int HOLIDAYS_ALLOWED = 3;
	
	public List<Courses> coursesEnrolled;
	public int stuAttendance;
//	public List<Grades> stuGrades;
//	public Holidays holiday;
	private int holidaysTaken;
	private int numberOfCoursesTaken; 
	private int standard;
	private char section;
	private String coursesTaken;
	private ArrayList<String> coursesDoing;
	public int studentID;
	
	Student() {
		// TODO constructor 
	}
	
	Student(String loginID,String password ,String name, String dOB, char gender, String contact, int standard, char section, int holidaysTaken, String coursesTaken){    //called from Login where all values are given VIA SQL
		
		setLoginID(loginID);
		setPassword(password);
		setName(name);
		setSex(gender);
		setDOB(dOB);
		setContact(contact);
		this.section=section;
		this.standard=standard;
		
//		studentID = generateStudentID();
		coursesEnrolled = new ArrayList<Courses>();
//		this.stuAttendance = new Attendance(attendance,studentID);
//		stuGrades = new ArrayList<Grades>();
		this.standard = standard;
		this.section = section;
		
		
		this.coursesTaken= coursesTaken;    
		coursesDoing = new ArrayList();
		StringTokenizer stuff = new StringTokenizer(coursesTaken,",");
		this.numberOfCoursesTaken= stuff.countTokens();
		while (stuff.hasMoreTokens()) { 
				coursesDoing.add(stuff.nextToken()); 
			} 
		
	}
	
	Student(int loginID,String password ,String name, String dOB, char gender, String contact, int standard, char section){    //called from Admin - Add Student
		setLoginID("S"+loginID);
		setPassword(password);
		setName(name);
		setSex(gender);
		setDOB(dOB);
		setContact(contact);
		this.section=section;
		this.standard=standard;
		this.holidaysTaken = 0;
		studentID = loginID; 
		
		this.coursesTaken= "Maths";
		this.numberOfCoursesTaken= 1;
		
		DBConnection conn = new DBConnection();
		//String query = "Insert into studenttable values("+loginID+",'"+password+"','"+name+"','"+dOB+"','"+gender+"','"+contact+"',"+holidaysTaken+","+standard+",'"+section+"','Maths';";
		String query1 = "insert into studenttable values("+loginID+",'"+password+"','"+name+"','"+dOB+"','"+gender+"','"+contact+"',"+holidaysTaken+","+standard+",'"+section+"','Maths');";
		String query2 = "insert into logs(studentID) values("+loginID+")";
		conn.insert(query1);
		conn.insert(query2);
		
	}
	
	public void addCourse(String cours) {
		this.coursesTaken= this.coursesTaken+","+cours;
		this.numberOfCoursesTaken++;
		
	}
	
	public void setHolidays(int holi) {			//Always setHolidays "taken" via SQL before apply Holidays
		this.holidaysTaken=holi;
	}
	
	public boolean applyHoliday(int holiTaken, int loginID) {
		
		Holidays holiday = new Holidays(HOLIDAYS_ALLOWED,holiTaken);
		
		if(holiday.eligible())	{
			//add holidays+1 in SQL++
			DBConnection conn = new DBConnection();
			int value=0;
			System.out.println(loginID);
			String query = "select HolidaysTaken from studenttable where studentID="+loginID+";";
			ResultSet rs = conn.select(query);
			try {
				rs.next();
				value=rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			query = "update studenttable set HolidaysTaken="+(value+1)+" where studentID="+loginID+";";
			conn.update(query);
			holidaysTaken++;
			holiday.taken++;
			
			return true;   //++ returns true to StudentGUI where add PROMPT if true++
		}
		else {
			
			return false;
		}
	}
	
	
}