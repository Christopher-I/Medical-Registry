package application;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class CustomerServiceController implements Serializable {

	PatientProfile patientinformation;
	ArrayList<PatientProfile> patientInfo = new ArrayList<PatientProfile>();

	CustomerServiceProfile customerServiceProfile;
	ArrayList<CustomerServiceProfile> customerServiceProfiles = new ArrayList<CustomerServiceProfile>();
	UserNameAndPassword userNameAndPassword = new UserNameAndPassword();

	private String tempPhone;

	@FXML
	private Label customerServicePersonnelProfileLabel;
	@FXML
	private Button button1;
	@FXML
	public Button closeButton;

	@FXML
	private TextField searchMinAge;
	@FXML
	private TextField searchMaxAge;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;
	@FXML
	private TextField ageField;
	@FXML
	private TextField lastCheckupFieldYear;
	@FXML
	private TextField lastCheckupFieldMonth;
	@FXML
	private TextField lastCheckupFieldDay;
	@FXML
	private TextField phoneField;
	@FXML
	private TextField emailAddressField;
	@FXML
	private TextField notesField;
	@FXML
	private TextField caseState;
	@FXML
	private ListView<String> listview;
	int openCases;
	int closedCases;
	boolean caseState1;

	// LocalDate now = LocalDate.now();// To calculate current date, which will be
	// used to track customer service representative login.

	public void initialize() {

		openCases= 5;
		closedCases= 8;
		caseState1 = false;
		LocalDate now = LocalDate.now();
		String nowString = now.toString();

		String firstName = "user";
		String lastName = "001";
		//int openCases = 0;
		//int closedCases = 0;

		customerServiceProfile = new CustomerServiceProfile(firstName, lastName, nowString, openCases, closedCases);

		customerServicePersonnelProfileLabel.setText(customerServiceProfile.getFirstName() + "!  "
				+ "Last login date was: " + customerServiceProfile.getLastloginDate() + ".  " + "Open Cases: "
				+ openCases + "  " + "Closed Cases: "
				+ closedCases);

		/*
		 * customerServiceProfiles.add(customerServiceProfile);
		 * SaveAndLoadFiles.saveCustomerServiceProfile(customerServiceProfiles);
		 */
	}

	public void handleMouseClick(MouseEvent arg0) {
		// String clickedName = listview.getSelectionModel().getSelectedItem();
		populateTable(tempPhone);
	}

	// make patient contact information visible when case is opened
	public void openCase(ActionEvent event) {
		
		if(!caseState1) {
		++openCases;
		--closedCases;
		emailAddressField.setVisible(true);
		notesField.setVisible(true);
		phoneField.setVisible(true);
		caseState1 = true;

		customerServicePersonnelProfileLabel.setText(customerServiceProfile.getFirstName() + "!  "
				+ "Last login date was: " + customerServiceProfile.getLastloginDate() + ".  " + "Open Cases: "
				+ openCases + "  " + "Closed Cases: "
				+ closedCases);
		}else {
			customerServicePersonnelProfileLabel.setText("This patients case is already open, please select a different patient");
					
		}

	}

	public void closeCase(ActionEvent event) {

		if(caseState1) {
	    --openCases;
		++closedCases;
		emailAddressField.setVisible(false);
		notesField.setVisible(false);
		phoneField.setVisible(false);
		caseState1 = false;

		customerServicePersonnelProfileLabel.setText(customerServiceProfile.getFirstName() + "!  "
				+ "Last login date was: " + customerServiceProfile.getLastloginDate() + ".  " + "Open Cases: "
				+ openCases + "  " + "Closed Cases: "
				+ closedCases);

	}else {
		customerServicePersonnelProfileLabel.setText("This patients case is already closed, please select a different patient");
		
	}
	}

	public void clearForm() {
		phoneField.setText("");
		firstNameField.setText("");
		lastNameField.setText("");
		ageField.setText("");
		lastCheckupFieldYear.setText("");
		lastCheckupFieldMonth.setText("");
		lastCheckupFieldDay.setText("");
		emailAddressField.setText("");
		notesField.setText("");
		listview.getItems().clear();

	}

	// Run a search based on patients phone number
	public void searchUserButtonPress(ActionEvent event) throws IOException {
		// feedBackLabel = null;

		clearForm();

		patientInfo = SaveAndLoadFiles.loadPatientInfo();
		// Check the search form if the search inputs for age, to ensure they are not
		// empty
		if (searchMaxAge.getText() != null && !searchMaxAge.getText().isEmpty() && searchMinAge != null
				&& !searchMinAge.getText().isEmpty()) {

			int age;
			int maxAge = Integer.parseInt(searchMaxAge.getText());
			int minAge = Integer.parseInt(searchMinAge.getText());

			for (PatientProfile patient : patientInfo) {
				age = patient.getAge();
				
								
//				if (minAge >= maxAge) {
//					System.out.println("Please fill out the form correctly, ");
//				}

				if ((maxAge >= age && age >= minAge)||(minAge >= age && age >= maxAge)) {
					//the if statement above takes into consideration human error of typing max age into the min input and vice-versa
					String firstName = patient.getFirstName();
					String lastName = patient.getLastName();
					String phone = patient.getPhone();// this created so that the telephone number could be used as
														// unique ID for the searched patient, it is later used in the
														// population method

					listview.getItems().addAll(firstName + "  " + lastName);
					tempPhone = phone;
					// feedBackLabel.setText("Records found");
				}
				age = 0;
			}

		} else {
			customerServicePersonnelProfileLabel.setText("Please enter a valid search input");
			// System.out.println("Please enter a valid input");
		}

	}

	// fill out table with only relevant information to customer service
	// representative
	public void populateTable(String clickedPhone) {

		String phone = null;
		// clickedPhone = clickedName.toLowerCase();

		patientInfo = SaveAndLoadFiles.loadPatientInfo();

		for (PatientProfile patient : patientInfo) {
			phone = patient.getPhone();
			// phone = convertToLowerCase(phone);

			if (clickedPhone.equals(phone)) {

				phoneField.setText(patient.getPhone());
				firstNameField.setText(patient.getFirstName());
				lastNameField.setText(patient.getLastName());

				ageField.setText(Integer.toString(patient.getAge()));

				lastCheckupFieldYear.setText(Integer.toString(patient.getLastCheckupDateYear()));
				lastCheckupFieldMonth.setText(Integer.toString(patient.getLastCheckupDateMonth()));
				lastCheckupFieldDay.setText(Integer.toString(patient.getLastCheckupDateDay()));

				emailAddressField.setText(patient.getEmail());
				notesField.setText(patient.getNotes());
				/*
				 * cityField.setText(patient.getCity()); stateField.setText(patient.getState());
				 */

				emailAddressField.setVisible(false);
				notesField.setVisible(false);
				phoneField.setVisible(false);

			}

		}
	}

	private boolean checkUsernamePassword(String username, String password) {

		if (username.equals(userNameAndPassword.getUserName())
				&& password.equals(userNameAndPassword.getUserPassword())) {
			return true;
		}
		return false;
	}

	public void ReturnToHomePage(ActionEvent event) throws IOException {

		Parent HomePage = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
		Scene HomeScene = new Scene(HomePage);

		Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
		window.setScene(HomeScene);
		window.show();
	}

	public void closeButtonAction(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
	}

}
