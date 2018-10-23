package application;

import java.util.Map;

import javafx.scene.control.DatePicker;

import java.io.Serializable;
import java.util.HashMap;

public class PatientProfile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private int age;
	private int ageYear;
	private int ageMonth;
	boolean caseState;
	
	
	private int ageDay;
	private String phone;
	private String address;
	private String weight;
	private String height;
	private String allergies;
	private String lastCheckupDate;
	private String city;
	private String state;
	private String email;
	private String notes;
	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNotes() {
		return notes;
	}


	public void setNotes(String notes) {
		this.notes = notes;
	}


	private int lastCheckupDateYear;
	private int lastCheckupDateMonth;
	private int lastCheckupDateDay;
	String doB;
	//Map<String,String, String> resultsMap = new HashMap<>();
	//private String name;
	
	public PatientProfile(String firstName,String lastName,int age,int ageYear,int ageMonth,int ageDay,String phone,String address,
			String weight,String height,String allergies,int lastCheckupDateYear, int lastCheckupDateMonth,int lastCheckupDateDay, String doB,
			String city, String state,String email, String notes, boolean caseState) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.ageYear = ageYear;
		this.ageMonth = ageMonth;
		this.ageDay = ageDay;
		this.phone = phone;
		this.address = address;
		this.weight = weight;
		this.height = height;
		this.allergies = allergies;
		this.lastCheckupDateYear = lastCheckupDateYear;
		this.lastCheckupDateMonth = lastCheckupDateMonth;
		this.lastCheckupDateDay = lastCheckupDateDay;
		this.city = city;
		this.state = state;
		this.email = email;
		this.notes = notes;
		this.caseState = caseState;
		
		this.doB = doB;
				
	}

	
	public int getLastCheckupDateYear() {
		return lastCheckupDateYear;
	}


	public void setLastCheckupDateYear(int lastCheckupDateYear) {
		this.lastCheckupDateYear = lastCheckupDateYear;
	}


	public int getLastCheckupDateMonth() {
		return lastCheckupDateMonth;
	}


	public void setLastCheckupDateMonth(int lastCheckupDateMonth) {
		this.lastCheckupDateMonth = lastCheckupDateMonth;
	}


	public int getLastCheckupDateDay() {
		return lastCheckupDateDay;
	}


	public void setLastCheckupDateDay(int lastCheckupDateDay) {
		this.lastCheckupDateDay = lastCheckupDateDay;
	}


	public int getAgeYear() {
		return ageYear;
	}


	public void setAgeYear(int ageYear) {
		this.ageYear = ageYear;
	}


	public int getAgeMonth() {
		return ageMonth;
	}


	public void setAgeMonth(int ageMonth) {
		this.ageMonth = ageMonth;
	}


	public int getAgeDay() {
		return ageDay;
	}


	public void setAgeDay(int ageDay) {
		this.ageDay = ageDay;
	}


	public String getFirstName() {
		return firstName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getLastCheckupDate() {
		return lastCheckupDate;
	}

	public void setLastCheckupDate(String lastCheckupDate) {
		this.lastCheckupDate = lastCheckupDate;
	}

	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String mPhone) {
		this.phone = mPhone;
	}	
	
	public int getAge() {
		return age;
	}

	public boolean getCaseState() {
		return caseState;
	}


	public void setCaseState(boolean caseState) {
		this.caseState = caseState;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/*@Override
	public String toString() {
		return String.format("Patient: %s is %s years old",name, age);
	}*/
}
