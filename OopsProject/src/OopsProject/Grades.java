package OopsProject;


public class Grades{
	final static char DEFAULT_GRADE = '0';
	public char grade;
	public int studentID;
	
	Grades(int studentID){
		grade = DEFAULT_GRADE;
		this.studentID = studentID; 
	}
	
	public void setGrade(char grade)
	{
		this.grade = grade;
	}
	public char getGrade()
	{
		return grade;
	}
	
	public boolean isFail()
	{
		try {
			if(grade>='F')
				return true;
			else if(grade>='A')
				return false;
			else 
				throw new GradeNotSetException("Grade Not Set/ Cannot compare null value");
		}
		catch(GradeNotSetException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public void showGrade() {
		if(isFail())
		{
			System.out.println("Warning: Course Failed");
		}
		else
		{
			/*show grade corresponding to the student id*/
		}
	}
	
}
