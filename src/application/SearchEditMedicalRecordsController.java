package application;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class SearchEditMedicalRecordsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private TextField lastCheckupDateField;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField allergiesField;
	@FXML
	private TextField addressField;
	@FXML
	private TextField searchField;
	@FXML
	private TextField lastCheckupFieldYear;
	@FXML
	private TextField lastCheckupFieldMonth;
	@FXML
	private TextField lastCheckupFieldDay;
	@FXML
	private TextField emailAddressField;
	@FXML
	private TextField cityField;
	@FXML
	private TextField stateField;
	@FXML
	private TextField notesField;

	public ListView<String> listview;
	ObservableList<String> nameList;
	String tempPhone;
	private int ageYear;
	private int ageMonth;
	private int ageDay;
	private int lastCheckupDateYear;
	private int lastCheckupDateMonth;
	private int lastCheckupDateDay;

	SaveAndLoadFiles files;
	PatientProfile patientinformation;
	MedicalAdministratorController medAdmin;
	ArrayList<PatientProfile> patientInfo = new ArrayList<PatientProfile>();

	@FXML
	private int getAge(DatePicker datePicker) {

		int birthYear = datePicker.getValue().getYear();
		int birthmonth = datePicker.getValue().getMonthValue();
		int birthDay = datePicker.getValue().getDayOfYear();

		// LocalDate curDate = LocalDate.now();
		LocalDate fullBirthday = LocalDate.of(birthYear, birthmonth, birthDay);
		LocalDate now = LocalDate.now();
		long daysSinceBirth = now.toEpochDay() - fullBirthday.toEpochDay();
		return (int) (daysSinceBirth / 365);

	}

	public void closeButtonAction(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

	public void returnToPatientForm(ActionEvent event) throws IOException {
		Parent medicalAdminMainPage = FXMLLoader
				.load(getClass().getResource("/application/MedicalAdministratorMainPage.fxml"));
		Scene patientForm = new Scene(medicalAdminMainPage);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(patientForm);
		window.show();
	}

	// Run a search based on patients phone number
	public void searchUserButtonPress(ActionEvent event) throws IOException {
		// feedBackLabel = null;
		phoneField.setText("");
		firstNameField.setText("");
		lastNameField.setText("");
		ageFieldYear.setText("");
		ageFieldDay.setText("");
		ageFieldYear.setText("");
		heightField.setText("");
		weightField.setText("");
		addressField.setText("");
		allergiesField.setText("");
		lastCheckupFieldYear.setText("");
		lastCheckupFieldMonth.setText("");
		lastCheckupFieldDay.setText("");

		listview.getItems().clear();
		patientInfo = SaveAndLoadFiles.loadPatientInfo();

		String enteredTxt = searchField.getText();
		if ((enteredTxt != null && !enteredTxt.isEmpty())) {

			String phone = null;

			for (PatientProfile patient : patientInfo) {
				phone = patient.getPhone();

				if (enteredTxt.equals(phone)) {

					listview.getItems().addAll(phone);
					tempPhone = phone;// Store phone number in a global variable that can be accesed laster during the
										// edit and save method
					feedBackLabel.setText("Records found");
				}
				phone = null;
			}

		} else {
			feedBackLabel.setText("Please enter a valid phone number");

		}

	}

	public String convertToLowerCase(String input) {

		String tempValue = input.toLowerCase();
		return tempValue;
	}

// collect string value of the search parameter(in this case phone number)
	@FXML
	public void handleMouseClick(MouseEvent arg0) {
		String clickedPhone = listview.getSelectionModel().getSelectedItem();
		populateTable(clickedPhone);
	}

	// Retrieve patient information and fill the patient form with it.
	public void populateTable(String clickedPhone) {
		String phone = null;
		// clickedPhone = clickedName.toLowerCase();

		patientInfo = SaveAndLoadFiles.loadPatientInfo();

		for (PatientProfile patient : patientInfo) {
			phone = patient.getPhone();

			if (clickedPhone.equals(phone)) {
				phoneField.setText(patient.getPhone());
				firstNameField.setText(patient.getFirstName());
				lastNameField.setText(patient.getLastName());

				ageYear = patient.getAgeYear();
				ageFieldYear.setText(Integer.toString(patient.getAgeYear()));
				ageFieldMonth.setText(Integer.toString(patient.getAgeMonth()));
				ageFieldDay.setText(Integer.toString(patient.getAgeDay()));

				lastCheckupFieldYear.setText(Integer.toString(patient.getLastCheckupDateYear()));
				lastCheckupFieldMonth.setText(Integer.toString(patient.getLastCheckupDateMonth()));
				lastCheckupFieldDay.setText(Integer.toString(patient.getLastCheckupDateDay()));

				// Integer.toString(int)
				heightField.setText(patient.getHeight());
				weightField.setText(patient.getWeight());
				addressField.setText(patient.getAddress());
				allergiesField.setText(patient.getAllergies());

				emailAddressField.setText(patient.getEmail());
				cityField.setText(patient.getCity());
				stateField.setText(patient.getState());
				notesField.setText(patient.getNotes());

			}

		}
	}

	// Edit and save patient information form after searching
	@FXML
	public void editAndSaveInformationButtonPress(ActionEvent event) {
		if ((firstNameField.getText() != null && !firstNameField.getText().isEmpty())
				&& (lastNameField.getText() != null && !lastNameField.getText().isEmpty())
				&& (phoneField.getText() != null && !phoneField.getText().isEmpty())) {
			patientInfo = SaveAndLoadFiles.loadPatientInfo();

			for (PatientProfile patient : patientInfo) {
				String phone = patient.getPhone();

				if (tempPhone.equals(phone)) {

					// calling method to check if date fields have integer inputs
					if (isInteger(lastCheckupFieldYear.getText()) && isInteger(lastCheckupFieldMonth.getText())
							&& isInteger(lastCheckupFieldDay.getText()) && isInteger(ageFieldYear.getText())
							&& isInteger(ageFieldMonth.getText()) && isInteger(ageFieldDay.getText())) {

						patient.setFirstName(firstNameField.getText());
						patient.setLastName(lastNameField.getText());
						patient.setPhone(phoneField.getText());
						patient.setPhone(phoneField.getText());

						patient.setLastCheckupDateYear(Integer.parseInt(lastCheckupFieldYear.getText()));
						patient.setLastCheckupDateDay(Integer.parseInt(lastCheckupFieldMonth.getText()));
						patient.setLastCheckupDateMonth(Integer.parseInt(lastCheckupFieldDay.getText()));

						patient.setAgeYear(Integer.parseInt(ageFieldYear.getText()));
						patient.setAgeMonth(Integer.parseInt(ageFieldMonth.getText()));
						patient.setAgeDay(Integer.parseInt(ageFieldDay.getText()));

						patient.setWeight(weightField.getText());
						patient.setHeight(heightField.getText());
						patient.setAllergies(allergiesField.getText());
						patient.setAddress(addressField.getText());

						patient.setEmail(emailAddressField.getText());
						patient.setCity(cityField.getText());
						patient.setState(stateField.getText());
						patient.setNotes(notesField.getText());
					} else {
						feedBackLabel.setText("Invalid inputs in the date fields, please enter numbers only ");
						break;
					}

					feedBackLabel.setText(firstNameField.getText() + "'s, medical information has been stored. ");
				} else {
					feedBackLabel.setText("Please enter a complete name, phone number and last medical visit date");
				}

				/*
				 * patientinformation = new PatientInformation( firstName, lastName, age, phone,
				 * address, weight, height, allergies, lastCheckupDate);
				 * patientInfo.add(patientinformation);
				 */
				SaveAndLoadFiles.savePatientInfo(patientInfo);

			}
		}
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
