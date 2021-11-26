package OopsProject;


public class Courses{
	public String name;
	public String ID;
	
	Courses(String name,int ID){
		this.name = name;
		this.ID = "C"+ID;
		
		DBConnection conn = new DBConnection();
		conn.insert("insert into coursetable values("+ID+",'"+name+"');");
		
		conn.insert("alter table logs add(C"+ID+"Attendance int default 0);");
		conn.insert("alter table logs add(C"+ID+"Marks int default 0);");
		
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setID(int ID) {
		this.ID = "C"+ID;
	}
	public String getName()
	{
		return name;
	}
	public String getID() {
		return ID;
	}
}
