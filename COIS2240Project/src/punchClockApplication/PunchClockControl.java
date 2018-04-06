package punchClockApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.xml.transform.Source;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.event.Event;

import punchClock.*;

public class PunchClockControl {

	Database db = new Database();

	Employee employee = new Employee();

	Administrator admin = new Administrator();

	LoginNumBtnHandler loginNumBtnHandler = new LoginNumBtnHandler();

	@FXML
	private Label lblLogin;

	@FXML
	private TextField jobSearchTerm;

	@FXML
	private TextField employeeSearchID;

	@FXML
	private TextField employeeNewGivenName;

	@FXML
	private TextField employeeNewSurname;

	@FXML
	private TextField employeeNewJob;

	@FXML
	private TextField employeeUpdateGivenName;

	@FXML
	private TextField employeeUpdateSurname;

	@FXML
	private TextField employeeUpdateJob;

	@FXML
	private TextArea jobSearchResults;

	@FXML
	private TextArea employeeSearchResults;

	@FXML
	private TextField adminStartHour;

	@FXML
	private TextField adminStartMin;

	@FXML
	private TextField adminEndHour;

	@FXML
	private TextField adminEndMin;

	@FXML
	private TextField adminEmployeeID;

	@FXML
	private StackPane root;

	@FXML
	private GridPane loginPage;

	@FXML
	private GridPane adminPage;

	@FXML
	private GridPane employeeDBPage;

	@FXML
	private GridPane jobDBPage;

	@FXML
	private Button btnLogin1;

	@FXML
	private Button btnLogin2;

	@FXML
	private Button btnLogin3;

	@FXML
	private Button btnLogin4;

	@FXML
	private Button btnLogin5;

	@FXML
	private Button btnLogin6;

	@FXML
	private Button btnLogin7;

	@FXML
	private Button btnLogin8;

	@FXML
	private Button btnLogin9;

	@FXML
	private Button btnLogin0;

	@FXML
	private TextField loginTextField;

	public PunchClockControl() {
	}

	@FXML
	private void initialize() {
		btnLogin0.setOnAction(loginNumBtnHandler);
		btnLogin1.setOnAction(loginNumBtnHandler);
		btnLogin2.setOnAction(loginNumBtnHandler);
		btnLogin3.setOnAction(loginNumBtnHandler);
		btnLogin4.setOnAction(loginNumBtnHandler);
		btnLogin5.setOnAction(loginNumBtnHandler);
		btnLogin6.setOnAction(loginNumBtnHandler);
		btnLogin7.setOnAction(loginNumBtnHandler);
		btnLogin8.setOnAction(loginNumBtnHandler);
		btnLogin9.setOnAction(loginNumBtnHandler);

		showLoginPage();
	}

	private void showLoginPage() {
		loginPage.setVisible(true);
		loginPage.toFront();
		loginPage.setDisable(false);

		adminPage.setVisible(false);
		adminPage.setDisable(true);

		jobDBPage.setVisible(false);
		jobDBPage.setDisable(true);

		employeeDBPage.setVisible(false);
		employeeDBPage.setDisable(true);
	}

	public void logout() {
		loginTextField.clear();
		showLoginPage();
	}

	public void showAdminPage() {
		adminPage.setVisible(true);
		adminPage.toFront();
		adminPage.setDisable(false);

		loginPage.setVisible(false);
		loginPage.setDisable(true);

		jobDBPage.setVisible(false);
		jobDBPage.setDisable(true);

		employeeDBPage.setVisible(false);
		employeeDBPage.setDisable(true);
	}

	private void showJobDBPage() {
		jobDBPage.setVisible(true);
		jobDBPage.toFront();
		jobDBPage.setDisable(false);

		loginPage.setVisible(false);
		loginPage.setDisable(true);

		adminPage.setVisible(false);
		adminPage.setDisable(true);

		employeeDBPage.setVisible(false);
		employeeDBPage.setDisable(true);
	}

	private void showEmployeeDBPage() {
		employeeDBPage.setVisible(true);
		employeeDBPage.toFront();
		employeeDBPage.setDisable(false);

		loginPage.setVisible(false);
		loginPage.setDisable(true);

		adminPage.setVisible(false);
		adminPage.setDisable(true);

		jobDBPage.setVisible(false);
		jobDBPage.setDisable(true);
	}

	public void editEmployeeDB() {
		showEmployeeDBPage();

		employeeSearchResults.setText(Database.Read.searchEmployee());
	}

	public void editJobDB() {
		jobSearchResults.setText(null);
		jobSearchTerm.setText(null);
		;
		showJobDBPage();
	}

	public void login() {
		int idNum = 0;
		try {
			idNum = Integer.parseInt(loginTextField.getText());
		} catch (NumberFormatException e) {
		}

		if (Database.Validate.validateUserId(idNum)) {
			if (Database.Validate.validateAdminId(idNum)) {
				admin = Database.Read.readAdmin(idNum);
				showAdminPage();
			} else if (Database.Validate.validateEmployeeId(idNum)) {
				employee = Database.Read.readEmployee(idNum);
				if (employee.getStartTime() != null) {
					employeeEndShift();
				} else {
					employeeStartShift();
				}
			}
		}
	}

	public void recordShift() {
		int idNum = 0;
		int startTimeHour = 0;
		int startTimeMin = 0;
		int endTimeHour = 0;
		int endTimeMin = 0;
		try {
			idNum = Integer.parseInt(adminEmployeeID.getText());
			startTimeHour = Integer.parseInt(adminStartHour.getText());
			startTimeMin = Integer.parseInt(adminStartMin.getText());
			endTimeHour = Integer.parseInt(adminEndHour.getText());
			endTimeMin = Integer.parseInt(adminEndMin.getText());

			if (Database.Validate.validateUserId(idNum)) {
				if (Database.Validate.validateEmployeeId(idNum)) {
					employee = Database.Read.readEmployee(idNum);
					admin.recordShift(employee,
							LocalDateTime.of(LocalDate.now(), LocalTime.of(startTimeHour, startTimeMin)),
							LocalDateTime.of(LocalDate.now(), LocalTime.of(endTimeHour, endTimeMin)));

					Database.Update.updateEmployee(employee);
				}
			}

		} catch (NumberFormatException e) {
		}
	}

	public void loginBack() {
		if (loginTextField.getLength() >= 1) {
			loginTextField.deleteText(loginTextField.getLength() - 1, loginTextField.getLength());
		}
	}

	public void searchJobs() {

		if (jobSearchTerm.getText() != null) {
			jobSearchResults.setText(Database.Read.searchJob(jobSearchTerm.getText()));
			// String test = "test";
			// jobSearchResults.setText(jobSearchTerm.getText());
		} else {
			jobSearchResults.setText(Database.Read.searchJob());
		}
	}

	public void newEmployee() {
		employee = new Employee(0, employeeNewGivenName.getText(), employeeNewSurname.getText(),
				new Job(employeeNewJob.getText()));
		Database.Update.addEmployee(employee);

		employeeSearchResults.setText(Database.Read.searchEmployee());
	}

	public void editEmployee() {
		employee = new Employee(Integer.parseInt(employeeSearchID.getText()), employeeUpdateGivenName.getText(),
				employeeUpdateSurname.getText(), new Job(employeeUpdateJob.getText()));
		
		Database.Update.updateEmployee(employee);
		
		employeeSearchResults.setText(Database.Read.searchEmployee());
	}

	public void employeeEndShift() {
		employee.endShift();
		Database.Update.updateEmployee(employee);
		lblLogin.setText("Shift ended for " + employee.getGivenName() + " " + employee.getSurname() + ".");
	}

	public void employeeStartShift() {
		employee.startShift();
		Database.Update.updateEmployee(employee);
		lblLogin.setText("Shift started for " + employee.getGivenName() + " " + employee.getSurname() + " at "
				+ employee.getStartTime().getHour() + ":" + employee.getStartTime().getMinute() + ".");
		// lblLogin.setText("Started");
	}

	public class LoginNumBtnHandler implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			Button btn = (Button) event.getSource();
			loginTextField.appendText(btn.getText());
		}

	}
}
