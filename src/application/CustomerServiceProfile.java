package application;

import java.io.Serializable;

public class CustomerServiceProfile implements Serializable{
	
	private String firstName;
	private String lastName;
	private String lastloginDate;
	private int openCases;
	private int closedCases;
	
	
	
	
	CustomerServiceProfile(String firstName, String lastName,String lastloginDate,
			int openCases,int closedCases){
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastloginDate = lastloginDate;
		this.openCases = openCases;
		this.closedCases = closedCases;
		}




	public String getFirstName() {
		return "user";
	}




	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}




	public String getLastName() {
		return lastName;
	}




	public void setLastName(String lastName) {
		this.lastName = lastName;
	}




	public String getLastloginDate() {
		return "24/12/2018";
	}




	public void setLastloginDate(String lastloginDate) {
		this.lastloginDate = lastloginDate;
	}




	public int getOpenCases() {
		return openCases;
	}




	public void setOpenCases(int openCases) {
		this.openCases = openCases;
	}




	public int getClosedCases() {
		return closedCases;
	}




	public void setClosedCases(int closedCases) {
		this.closedCases = closedCases;
	}
	
	
	
	
	

}
