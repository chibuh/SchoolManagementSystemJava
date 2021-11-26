package OopsProject;


import java.util.*;

public class Admin{
	private String adPass;
	private String adID = "A000";
	Admin(){
		adPass="default";
	}
	public String getAdPass() {
		return adPass;
	}
	public void setAdPass(String adPass) {
		this.adPass=adPass;
	}
	public String getAdId() {
		return adID;
	}
		
}