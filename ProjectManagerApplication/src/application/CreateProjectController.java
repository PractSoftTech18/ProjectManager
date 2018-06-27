package application;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import customer.Customer;
import customer.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import project.Priority;
import project.Project;
import project.Status;
import project.Task;
import files.FileHandler;
import files.FileHandlerInterface;

/**
 * controller for CreateProject
 * 
 * @author Julia Hofer
 * @version 1.00, June 26th 2018
 */
public class CreateProjectController {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	/**
	 * FileHandler
	 */
	private FileHandler ourFileHandler = FileHandler.getFileHandler();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Button NewTab, btnSave, btnAddPerson, btnAddTask;

	@FXML
	private ChoiceBox<String> cBoxPriority, cBoxStatus, cBoxContactPerson, cBoxTaskPriority, cBoxTaskStatus;

	@FXML
	private TableColumn<Person, String> tblColName, tblColRelation, tblColPhone, tblColMail, tblColAd;

	@FXML
	private TableColumn<TableTask, String> tblColTask, tblColRemark, tblColStatus, tblColPriority, tblColDate;

	@FXML
	private TableView<Person> tblPersons;

	@FXML
	private TableView<TableTask> tblTasks;

	@FXML
	private TextField tfProjectName;

	@FXML
	private TextArea taDescription, taNotes;

	@FXML
	private TextField tfTask, tfTaskRemark;

	@FXML
	private TextField tfPersonName, tfPersonAd, tfPersonRelation, tfPersonPhone, tfPersonMail;

	@FXML
	private ColorPicker colorPProject;

	@FXML
	private DatePicker datePDeadline, datePEvent, datePTaskDate;

	@FXML
	void addNewTab(ActionEvent event) {
	}

	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	private ObservableList<Person> person;
	private ObservableList<String> contactP;
	private ObservableList<TableTask> tableTask;
	private ArrayList<Task> task = new ArrayList<>();

	/**
	 * initialize view of creating a project
	 * 
	 * @author Lydia Grillenberger
	 * @author Julia Hofer
	 * @author Lukas Schiefermueller
	 */
	@FXML
	void initialize() {
		assert NewTab != null : "fx:id=\"NewTab\" was not injected: check your FXML file 'CreateProject.fxml'.";

		// Project
		ObservableList<String> items = cBoxPriority.getItems();
		items.addAll(Priority.HIGH.toString(), Priority.NORMAL.toString(), Priority.LOW.toString());
		cBoxPriority.setItems(items);
		cBoxPriority.setValue(Priority.NORMAL.toString());

		// Task
		cBoxTaskPriority.setItems(items);
		cBoxTaskPriority.setValue(Priority.NORMAL.toString());

		// Project
		items = cBoxStatus.getItems();
		for (Status s : Status.values()) {
			if (!(s.getTask())) {
				items.add(s.getStatus());
			}
		}
		cBoxStatus.setItems(items);
		cBoxStatus.setValue(Status.PREPRODUCTION.getStatus());

		// Task
		items = cBoxTaskStatus.getItems();
		for (Status s : Status.values()) {
			if (s.getTask()) {
				items.add(s.getStatus());
			}
		}
		cBoxTaskStatus.setItems(items);
		cBoxTaskStatus.setValue(Status.OPEN.getStatus());

		// https://docs.oracle.com/javafx/2/ui_controls/table-view.htm
		// adding data to table persons
		tblColName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		tblColPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
		tblColMail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		tblColAd.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
		tblColRelation.setCellValueFactory(new PropertyValueFactory<Person, String>("relation"));
		person = FXCollections.observableArrayList();

		// adding data to table tasks
		tblColTask.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tname"));
		tblColRemark.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tremark"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tstatus"));
		tblColPriority.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tpriority"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tdate"));
		tableTask = FXCollections.observableArrayList();

	}
	/*
	 * /** initialize choiceBox menu of choiceBoxContactPerson
	 * 
	 * @author Lydia Grillenberger
	 * 
	 * @param event select choiceBox
	 * 
	 * public void selectContactPerson(ActionEvent event) { // should be called when
	 * cBoxContactPerson.onShowingProperty() but I cannot // find //
	 * onShowingProperty in Scene Builder if (tblColName != null) {
	 * ObservableList<TableColumn<Person, ?>> personChoice =
	 * tblColName.getColumns(); if (personChoice != null) { ObservableList<String>
	 * items = cBoxContactPerson.getItems(); for (Iterator<TableColumn<Person, ?>>
	 * it = personChoice.iterator(); it.hasNext();) {
	 * items.add(it.next().toString()); } cBoxContactPerson.setItems(items); } } }
	 */

	/**
	 * save project
	 * 
	 * @author Lukas Schiefermueller
	 * @author Julia Hofer
	 * @param event
	 *            select button save
	 */
	public void btnSave(ActionEvent event) {

		if (!tfProjectName.getText().equals("")) {
			Project newProject = new Project();
			newProject.setTitle(tfProjectName.getText());
			newProject.setColor(colorPProject.getValue());
			newProject.setPriority(Priority.returnPriority(cBoxPriority.getSelectionModel().getSelectedItem()));
			newProject.setStatus(Status.returnStatus(cBoxStatus.getSelectionModel().getSelectedItem(), false));

			// https://stackoverflow.com/questions/20446026/get-value-from-date-picker
			LocalDate localDate;
			Instant instant;
			if ((localDate = datePDeadline.getValue()) != null) {
				instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				newProject.setDeadline(Date.from(instant));
			} else {
				newProject.setDeadline(new Date(1));
			}
			if ((localDate = datePEvent.getValue()) != null) {
				instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
				newProject.setEventDate(Date.from(instant));
			} else {
				newProject.setEventDate(new Date(1));
			}
			ArrayList<Person> persons = new ArrayList<>();
			persons = new ArrayList<Person>(person);
			String contactPerson;
			int cont = 0;
			if ((contactPerson = cBoxContactPerson.getValue()) != null) {
				cont = contactP.indexOf(contactPerson);
			}
			newProject.setCustomer(new Customer(persons, cont));
			newProject.setTasks(task);
			newProject.setDescription(taDescription.getText());
			newProject.setNotes(taNotes.getText());

			ourFileHandler.add(newProject);
			tfProjectName.clear();
			colorPProject.setValue(null);
			cBoxPriority.setValue(Priority.NORMAL.toString());
			cBoxStatus.setValue(Status.PREPRODUCTION.getStatus());
			datePDeadline.setValue(null);
			datePEvent.setValue(null);
			cBoxContactPerson.getItems().clear();
			;
			tblPersons.getItems().clear();
			tblTasks.getItems().clear();
			taDescription.clear();
			taNotes.clear();

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Kein Projektname eingegeben!");
			alert.setContentText("Bitte Projektnamen eingeben und erneut speichern.");
			alert.showAndWait();
		}
	}

	@FXML
	/**
	 * add person to project
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            click button "Hinzufuegen"
	 */
	public void btnAddPerson(ActionEvent event) {
		person.add(new Person(tfPersonName.getText(), tfPersonPhone.getText(), tfPersonMail.getText(),
				tfPersonAd.getText(), tfPersonRelation.getText()));
		tblPersons.setItems(person);

		contactP = cBoxContactPerson.getItems();
		contactP.add(tfPersonName.getText());
		cBoxContactPerson.setItems(contactP);

		tfPersonName.clear();
		tfPersonPhone.clear();
		tfPersonMail.clear();
		tfPersonAd.clear();
		tfPersonRelation.clear();
	}

	/**
	 * Class similar to Task.java but only relies on String parameters Used for
	 * creating/representation of tasks in tblTasks
	 * 
	 * @author Julia Hofer
	 */
	public class TableTask {
		private String tname;
		private String tremark;
		private String tstatus;
		private String tpriority;
		private String tdate;

		/**
		 * @param name
		 * @param remark
		 * @param priority
		 * @param taskOpen
		 * @param date
		 */
		public TableTask(String taskName, String taskRemark, String taskStatus, String taskPriority, String taskDate) {
			this.tname = taskName;
			this.tremark = taskRemark;
			this.tstatus = taskStatus;
			this.tpriority = taskPriority;
			this.tdate = taskDate;
		}

		public String toFile() {
			String s = tname + " " + tremark + " " + tstatus + " " + tpriority + " " + tdate;
			return s;
		}

		public String getTname() {
			return tname;
		}

		public String getTremark() {
			return tremark;
		}

		public String getTstatus() {
			return tstatus;
		}

		public String getTpriority() {
			return tpriority;
		}

		public String getTdate() {
			return tdate;
		}
	}

	@FXML
	/**
	 * add task to project
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            click button "Hinzufuegen"
	 */
	public void btnAddTask(ActionEvent event) {

		String dateString = "";
		Date date;
		LocalDate localDate;

		if (datePTaskDate.getValue() != null) {
			localDate = datePTaskDate.getValue();
			date = Date.from(Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())));
			dateString = localDate.toString();
		} else {
			date = new Date(1);
			dateString = dateFormatter.format(date);
		}

	
		
		task.add(new Task(tfTask.getText(), tfTaskRemark.getText(),
				Status.returnStatus(cBoxTaskStatus.getValue(), true),
				Priority.returnPriority(cBoxTaskPriority.getValue()), date));
		
		tableTask.add(new TableTask(tfTask.getText(), tfTaskRemark.getText(), cBoxTaskStatus.getValue(),
				cBoxTaskPriority.getValue(), dateString));
		tblTasks.setItems(tableTask);

		tfTask.clear();
		datePTaskDate.setValue(null);
		tfTaskRemark.clear();
		cBoxTaskStatus.setValue(Status.OPEN.getStatus());
		cBoxTaskPriority.setValue(Priority.NORMAL.toString());
	}
}
