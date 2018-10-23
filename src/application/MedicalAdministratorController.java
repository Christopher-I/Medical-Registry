package application;

import java.io.IOException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MedicalAdministratorController implements Serializable {

	private static final long serialVersionUID = 1L;

	PatientProfile patientinformation;
	ArrayList<PatientProfile> patientInfo = new ArrayList<PatientProfile>();

	private String firstName;
	private String lastName;
	private int age;
	private int ageYear;
	private int ageMonth;
	private int ageDay;
	private String phone;
	private String address;
	private String weight;
	private String height;
	private String allergies;
	private String lastCheckupDate;
	private String emailAddress;
	private String city;
	private String state;
	private String notes;
	private String doB;
	private int lastCheckupDateYear;
	private int lastCheckupDateMonth;
	private int lastCheckupDateDay;

	@FXML
	private Label feedBackLabel;
	@FXML
	private Label labelDisplay2;
	@FXML
	private Button button1;
	@FXML
	public Button closeButton;

	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField ageFieldYear;
	@FXML
	private TextField ageFieldMonth;
	@FXML
	private TextField ageFieldDay;
	@FXML
	private TextField weightField;
	@FXML
	private TextField heightField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField allergiesField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField emailAddressField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField notesField;
	@FXML
	private TextField lastCheckupFieldYear;
	@FXML
	private TextField lastCheckupFieldMonth;
	@FXML
	private TextField lastCheckupFieldDay;

	// Method to calculate approximate patients age from input from the Datepicker
	private int getAgeNow(int ageY, int ageM, int ageD) {
		LocalDate now = LocalDate.now();
		LocalDate birthDay = LocalDate.of(ageY, ageM, ageD);
		int period = Period.between(birthDay, now).getYears();

		return period;

	}

//Button control to return to the main page
	public void ReturnToHomePage(ActionEvent event) throws IOException {
		Parent HomePage = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene HomeScene = new Scene(HomePage);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(HomeScene);
		window.show();
	}

	// This method open an new Scene for searching and editing patient records.
	public void searchEditMedicalRecordsPage(ActionEvent event) throws IOException {
		Parent HomePage = FXMLLoader.load(getClass().getResource("/application/Search&EditMedicalRecords.fxml"));
		Scene HomeScene = new Scene(HomePage);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(HomeScene);
		window.show();
	}

	// This method saves typed patient records from textfields and other input
	// mediums on the Medical Personal Main Scene.
	@FXML
	public void saveInformationButtonPress(ActionEvent event) {
		// Check if important input fields are empty and give user feedback.
		if ((firstNameField.getText() != null && !firstNameField.getText().isEmpty())
				&& (lastNameField.getText() != null && !lastNameField.getText().isEmpty())
				&& (phoneField.getText() != null && !phoneField.getText().isEmpty())) {

			// calling method to check if date fields have integer inputs
			if (isInteger(lastCheckupFieldYear.getText()) && isYear(lastCheckupFieldYear.getText())&& 
					isInteger(lastCheckupFieldMonth.getText())&& isMonth(lastCheckupFieldMonth.getText())
					&& isInteger(lastCheckupFieldDay.getText()) && isDay(lastCheckupFieldDay.getText())
					&& isInteger(ageFieldYear.getText())&& isYear(ageFieldYear.getText())
					&& isInteger(ageFieldMonth.getText())&& isMonth(ageFieldMonth.getText()) 
					&& isInteger(ageFieldDay.getText())&& isDay(ageFieldDay.getText())) {

				firstName = firstNameField.getText();
				lastName = lastNameField.getText();
				phone = phoneField.getText();

				lastCheckupDateYear = Integer.parseInt(lastCheckupFieldYear.getText());
				lastCheckupDateMonth = Integer.parseInt(lastCheckupFieldMonth.getText());
				lastCheckupDateDay = Integer.parseInt(lastCheckupFieldDay.getText());

				ageYear = Integer.parseInt(ageFieldYear.getText());
				ageMonth = Integer.parseInt(ageFieldMonth.getText());
				ageDay = Integer.parseInt(ageFieldDay.getText());
				age = getAgeNow(ageYear, ageMonth, ageDay);

				weight = weightField.getText();
				height = heightField.getText();
				allergies = allergiesField.getText();
				address = addressField.getText();
				emailAddress = emailAddressField.getText();
				city = cityField.getText();
				state = stateField.getText();
				notes = notesField.getText();

				feedBackLabel.setText(firstNameField.getText() + "'s, medical information has been stored. ");
				patientinformation = new PatientProfile(firstName, lastName,age, ageYear, ageMonth, ageDay, phone, address,
						weight, height, allergies, lastCheckupDateYear, lastCheckupDateMonth, lastCheckupDateDay, doB, city, state, emailAddress, notes,false);
				patientInfo.add(patientinformation);
				SaveAndLoadFiles.savePatientInfo(patientInfo);

			} else {
				feedBackLabel.setText("Invalid inputs in the date fields, please enter numbers only ");
				// break;
			}

		} else {
			feedBackLabel.setText("Please enter a complete name, phone number and last medical visit date");
		}

	}

	// A method to exit the program by clicking the quit button.
	public void closeButtonAction(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}
	
	//method to check if input is a valid year
	public static boolean isYear(String i) {
		boolean isValidYear = false;
		int j = Integer.parseInt(i);
		
		if(j>1950 && j<3000 ) {
			isValidYear = true;
		} 

		return isValidYear;
	}
	//method to check if input is a valid month
	public static boolean isMonth(String i) {
		boolean isValidMonth = false;
		int j = Integer.parseInt(i);
		
		if(j>=1 && j<=12 ) {
			isValidMonth = true;
		} 

		return isValidMonth;
	}
	//method to check if input is a valid day
	public static boolean isDay(String i) {
		boolean isValidDay = false;
		int j = Integer.parseInt(i);
		
		if(j>=1 && j<=31) {
			isValidDay = true;
		} 

		return isValidDay;
	}
	
	// Method to check if an input value is an integer. I am using this for the date
	// inputs, so letters are not entered as integers
	public static boolean isInteger(String s) {
		boolean isValidInteger = false;
		try {
			Integer.parseInt(s);

			// s is a valid integer

			isValidInteger = true;
		} catch (NumberFormatException ex) {
			// s is not an integer
		}

		return isValidInteger;
	}
}
