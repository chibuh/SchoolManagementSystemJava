package OopsProject;


public class Holidays{
	int left;
	int taken;
	
	Holidays(int days, int taken){
		this.taken = taken;
		left = days-taken;
	}
	
	public boolean eligible()
	{
		if(left>0)
			return true;
		else
			return false;
	}
	
	public void grantHoliday() {
		left--;
		taken++;
		System.out.println("Holiday Approved");
	}
	
	public void denyHoliday() {
		System.out.println("Holidays Left =0\nHoliday Denied.");
	}
}
