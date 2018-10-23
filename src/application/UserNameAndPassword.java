package application;

import java.io.Serializable;

public class UserNameAndPassword implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	
	
	
	
	private void UserNameAndPassword(String firstName,String lastName,String userName,
			String password) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;		
	}

	public String getFirstName() {
		this.firstName = "new";
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = "user";
	}

	public String getLastName() {
		lastName = "user";
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		this.userName = "username";
		return userName;
	}
	
	public String getUserPassword() {
		this.password = "password";
		return password;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
