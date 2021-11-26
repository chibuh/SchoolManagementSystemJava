package OopsProject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Teacher extends User {
	
	final static int HOLIDAYS_ALLOWED = 2;
	private int holidaysTaken;
	private String courseTaught;
	private int salary;
//	private Holidays holiday;
	
	Teacher(String loginID,String pass,String name, String dOB, char gender,String contact,int holidaysTaken,String coursesTaught,int salary){
		setLoginID(loginID);
		setPassword("default");
		setName(name);
		setSex(gender);
		setDOB(dOB);
		setContact(contact); 		
		this.salary = 25000;
		this.courseTaught=courseTaught;
		this.holidaysTaken=holidaysTaken;
	}
	
	Teacher(int loginID,String pass,String name, String dOB, char gender, String contact, String course){
		setLoginID(""+loginID);
		setPassword("default");
		setName(name);
		setSex(gender);
		setDOB(dOB);
		setContact(contact); 		
		this.salary = 25000;
		courseTaught=course;
		holidaysTaken=0;

		DBConnection conn = new DBConnection();
		
		String query1 = "insert into teachertable values("+loginID+",'"+pass+"','"+name+"','"+dOB+"','"+gender+"','"+contact+"',"+holidaysTaken+",'"+courseTaught+"',"+25000+");";
		conn.insert(query1);
		
	}
	
	public Teacher() {
		// TODO
	}
	
	
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public String getCourse() {
		return courseTaught;
	}
	public void setCourses(String cours) {
		this.courseTaught = cours;
	}

	
	public boolean applyHoliday(int holiTaken, int loginID) {
		
		Holidays holiday = new Holidays(HOLIDAYS_ALLOWED,holiTaken);
		
		if(holiday.eligible())	{
			//add holidays+1 in SQL++
			DBConnection conn = new DBConnection();
			int value=0;
			System.out.println(loginID);
			String query = "select HolidaysTaken from teachertable where teacherID="+loginID+";";
			ResultSet rs = conn.select(query);
			try {
				rs.next();
				value=rs.getInt(1);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			query = "update teachertable set HolidaysTaken="+(value+1)+" where teacherID="+loginID+";";
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