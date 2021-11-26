package OopsProject;

import java.util.*;

abstract class User{
	private String loginID;
	private String password;
	private String name;
	private String dOB;
	private char sex;
	private String contact;
	
	
//	private String address; 
	
	public void setLoginID(String loginID)
	{
		this.loginID=loginID;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public void setDOB(String dOB)
	{
		this.dOB=dOB;
	}
	public void setSex(char sex)
	{
		this.sex=sex;
	}
	public void setPassword(String password)
	{
		//password generator 
		this.password=password;
	}
	public void setContact(String contact)
	{
		this.contact=contact;
	}
	
	public Boolean verifyLogin(String pass) {
		//check by SQL if password matches
		// allow user to use forgotPassword()
		if(pass.equals(password))
			return true;
		else
			return false;
	}
	
	String getId() {
		return loginID;
	}
	String getPassword() {
		return password;
	}
	String getDOB() {
		return dOB;
	}
	String getContact() {
		return contact;
	}
	char getGender() {
		return sex;
	}
	String getName() {
		return name;
	}
	
	abstract public boolean applyHoliday(int holiTaken, int x);
		
}
 
