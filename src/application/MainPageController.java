package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MainPageController {
	
	UserNameAndPassword userNameAndPassword = new UserNameAndPassword();

	@FXML
	private TextField customerServiceUserNameField;
	@FXML
	private TextField customerServicepasswordField;
	@FXML
	private TextField medicalPersonnelUserNameField;
	@FXML
	private TextField medicalPersonnelPasswordField;
	@FXML
	Label feedbackLabel;

	private boolean checkUsernamePassword(String username, String password) {

		if (username.equals(userNameAndPassword.getUserName()) && password.equals(userNameAndPassword.getUserPassword())) {
			return true;
		}
		return false;
	}

	public void goToCustomerServicePage(ActionEvent event) throws IOException {
		String userName = customerServiceUserNameField.getText();
		String password = customerServicepasswordField.getText();
		if (userName.isEmpty() || password.isEmpty() || userName.equals("")) {

			feedbackLabel
					.setText("Customer Service username and/or password empty, please enter username and password");
		} else if (checkUsernamePassword(userName, password)) {

			Parent CustomerService = FXMLLoader
					.load(getClass().getResource("/application/CustomerServiceMainPage.fxml"));
			Scene CustomerServiceHomePage = new Scene(CustomerService);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(CustomerServiceHomePage);
			window.show();
		} else {
			feedbackLabel.setText("Customer Service username and password invalid, please enter username and password");
		}
	}

	public void goToMedicalPersonnelPage(ActionEvent event) throws IOException {
		String userName = medicalPersonnelUserNameField.getText();
		String password = medicalPersonnelPasswordField.getText();

		if (userName.isEmpty() || password.isEmpty() || userName.equals("")) {

			feedbackLabel
					.setText("Customer Service username and/or password empty, please enter username and password");
		} else if (checkUsernamePassword(userName, password)) {
			Parent MedService = FXMLLoader
					.load(getClass().getResource("/application/MedicalAdministratorMainPage.fxml"));
			Scene MedServiceHomePage = new Scene(MedService);

			Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
			window.setScene(MedServiceHomePage);
			window.show();
		} else {
			feedbackLabel
					.setText("Medical Personnel username and password invalid, please enter username and password");
		}
	}

}
