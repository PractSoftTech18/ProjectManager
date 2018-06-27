package application;

import java.util.Date;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

import application.TableTask;
import customer.Customer;
import customer.Person;
import files.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import project.Priority;
import project.Project;
import project.Task;
import project.Status;

/**
 * controller for EditProject
 * 
 * @author Julia Hofer
 * @version 1.00, June 28th 2018
 */
public class EditProjectController {

	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	/**
	 * FileHandler
	 */
	private FileHandler ourFileHandler = FileHandler.getFileHandler();

	@FXML
	private AnchorPane apEditProject;

	@FXML
	private TextField tfProjectName;

	@FXML
	private TextField tfTask, tfTaskRemark;

	@FXML
	private TextField tfPersonName, tfPersonAd, tfPersonRelation, tfPersonPhone, tfPersonMail;

	@FXML
	private TextArea taDescription, taNotes;

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
	private DatePicker datePTaskDate, datePDeadline, datePEvent;

	@FXML
	private Button btnAddPerson, btnDeleteTask, btnAddTask, btnSave, btnDeletePerson, btnDeleteProject;

	@FXML
	private ColorPicker colorPProject;

	private ObservableList<Person> person;
	private ObservableList<String> contactP;
	private ObservableList<TableTask> tableTask;
	private ArrayList<Task> task = new ArrayList<>();
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	private Person chosenPerson;
	private TableTask chosenTask;

	private Project p = ourData.projects.get(ourData.selected);

	/**
	 * initialize view of changing a project
	 * 
	 * @author Julia Hofer
	 */
	@FXML
	void initialize() {

		tfProjectName.setText(p.getTitle());
		colorPProject.setValue(p.getColor());

		datePEvent.setValue(p.getEventDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		datePDeadline.setValue(p.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

		// Project
		ObservableList<String> items = cBoxPriority.getItems();
		items.addAll(Priority.HIGH.toString(), Priority.NORMAL.toString(), Priority.LOW.toString());
		cBoxPriority.setItems(items);
		cBoxPriority.setValue(p.getPriority().toString());

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
		cBoxStatus.setValue(p.getStatus().getStatus());

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

		contactP = cBoxContactPerson.getItems();
		if (p.getCustomer() != null && p.getCustomer().getPersons() != null) {
			for (Iterator<Person> pIt = p.getCustomer().getPersons().iterator(); pIt.hasNext();) {
				person.add(pIt.next());
				contactP.add(person.get(person.size() - 1).getName());
			}
		}
		tblPersons.setItems(person);

		cBoxContactPerson.setItems(contactP);
		if (!cBoxContactPerson.getItems().isEmpty()) {

			cBoxContactPerson.setValue(p.getCustomer().getContactPerson());
		}

		taDescription.setText(p.getDescription());
		taNotes.setText(p.getNotes());

		// adding data to table tasks
		tblColTask.setCellValueFactory(new PropertyValueFactory<TableTask, String>("name"));
		tblColRemark.setCellValueFactory(new PropertyValueFactory<TableTask, String>("remark"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TableTask, String>("status"));
		tblColPriority.setCellValueFactory(new PropertyValueFactory<TableTask, String>("priority"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TableTask, String>("date"));
		tableTask = FXCollections.observableArrayList();

		String dateString = "";
		if (p.getTasks() != null) {
			for (Iterator<Task> t = p.getTasks().iterator(); t.hasNext();) {
				Task pTask = t.next();
				if (pTask.getDate() != null) {
					dateString = dateFormatter.format(pTask.getDate());
				}
				tableTask.add(new TableTask(pTask.getName(), "", pTask.getRemark(), pTask.getStatus().getStatus(),
						pTask.getPriority().toString(), dateString));
				task.add(new Task(pTask.getName(), pTask.getRemark(), pTask.getStatus(), pTask.getPriority(),
						pTask.getDate()));
			}
		}
		tblTasks.setItems(tableTask);

	}

	@FXML
	/**
	 * save project
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select button save
	 */
	void btnSave(ActionEvent event) {
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

			ourFileHandler.change(newProject, p.getTitle());

			clear();
			AnchorPane pane;
			try {
				pane = FXMLLoader.load(getClass().getResource("/application/ProjectView.fxml"));
				apEditProject.getChildren().setAll(pane);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Kein Projektname eingegeben!");
			alert.setContentText("Bitte Projektnamen eingeben und erneut speichern.");
			alert.showAndWait();
		}
	}

	/**
	 * clear the scene
	 * 
	 * @author Julia Hofer
	 */
	private void clear() {
		tfProjectName.clear();
		colorPProject.setValue(null);
		cBoxPriority.setValue(Priority.NORMAL.toString());
		cBoxStatus.setValue(Status.PREPRODUCTION.getStatus());
		datePDeadline.setValue(null);
		datePEvent.setValue(null);
		cBoxContactPerson.getItems().clear();

		tblPersons.getItems().clear();
		tblTasks.getItems().clear();
		taDescription.clear();
		taNotes.clear();
	}

	@FXML
	/**
	 * add a person
	 * 
	 * @param event
	 *            select add person
	 */
	void btnAddPerson(ActionEvent event) {
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

	@FXML
	/**
	 * edit a person
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select edit a person
	 */
	public void btnEditPerson(ActionEvent event) {
		chosenPerson = tblPersons.getSelectionModel().getSelectedItem();
		if (chosenPerson != null) {
			tfPersonName.setText(chosenPerson.getName());
			tfPersonPhone.setText(chosenPerson.getPhoneNumber());
			tfPersonMail.setText(chosenPerson.getEmail());
			tfPersonAd.setText(chosenPerson.getAddress());
			tfPersonRelation.setText(chosenPerson.getRelation());

			int i = person.indexOf(chosenPerson);
			person.remove(i);

			contactP.remove(i);
			if (cBoxContactPerson.getItems().indexOf(chosenPerson.getName()) == p.getCustomer()
					.getContactPersonIndex()) {
				cBoxContactPerson.setValue(null);
			}
			cBoxContactPerson.setItems(contactP);
		}
	}

	/**
	 * delete a person
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select delete a person
	 */
	public void btnDeletePerson(ActionEvent event) {
		chosenPerson = tblPersons.getSelectionModel().getSelectedItem();
		if (chosenPerson != null) {
			if (alert(true)) {
				int i = person.indexOf(chosenPerson);
				person.remove(i);

				contactP.remove(i);
				if (cBoxContactPerson.getItems().indexOf(chosenPerson.getName()) == p.getCustomer()
						.getContactPersonIndex()) {
					cBoxContactPerson.setValue(null);
				}
				cBoxContactPerson.setItems(contactP);
			}
		} else {
			alert(false);
		}
	}

	@FXML
	/**
	 * add task to project
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select add task
	 */
	public void btnAddTask(ActionEvent event) {
		String dateString = "";
		Date date;
		LocalDate localDate;

		if (datePTaskDate.getValue() != null) {
			localDate = datePTaskDate.getValue();
			date = Date.from(Instant.from(localDate.atStartOfDay(ZoneId.systemDefault())));
			dateString = dateFormatter.format(date);
		} else {
			date = new Date();
			dateString = dateFormatter.format(date);
		}

		task.add(
				new Task(tfTask.getText(), tfTaskRemark.getText(), Status.returnStatus(cBoxTaskStatus.getValue(), true),
						Priority.returnPriority(cBoxTaskPriority.getValue()), date));

		tableTask.add(new TableTask(tfTask.getText(), "", tfTaskRemark.getText(), cBoxTaskStatus.getValue(),
				cBoxTaskPriority.getValue(), dateString));
		tblTasks.setItems(tableTask);

		tfTask.clear();
		datePTaskDate.setValue(null);
		tfTaskRemark.clear();
		cBoxTaskStatus.setValue(Status.OPEN.getStatus());
		cBoxTaskPriority.setValue(Priority.NORMAL.toString());
	}

	/**
	 * edit a task
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select edit a task
	 */
	public void btnEditTask(ActionEvent event) {

		chosenTask = tblTasks.getSelectionModel().getSelectedItem();
		if (chosenTask != null) {
			tfTask.setText(chosenTask.getName());
			DateFormat dateFormat = dateFormatter;
			Date date = new Date();
			try {
				date = dateFormat.parse(chosenTask.getDate());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			datePTaskDate.setValue(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			tfTaskRemark.setText(chosenTask.getRemark());
			cBoxTaskStatus.setValue(chosenTask.getStatus());
			cBoxTaskPriority.setValue(chosenTask.getPriority());

			int i = tableTask.indexOf(chosenTask);
			task.remove(i);
			tableTask.remove(i);
		}
	}

	/**
	 * delete a task
	 * 
	 * @author Julia Hofer
	 * @param select
	 *            delete a task
	 */
	public void btnDeleteTask(ActionEvent event) {
		chosenTask = tblTasks.getSelectionModel().getSelectedItem();
		if (chosenTask != null) {
			if (alert(true)) {
				int i = tableTask.indexOf(chosenTask);
				tableTask.remove(i);
				task.remove(i);
			}
		} else {
			alert(false);
		}
	}

	/**
	 * delete the selected project in the edit tab
	 * 
	 * @author Lukas Schiefermueller
	 * @param event
	 *            select delete the project
	 */
	public void btnDeleteProject(ActionEvent event) {
		if (alert(true)) {
			ourFileHandler.delete(p);
			clear();
		} else {
			alert(false);
		}
	}

	/**
	 * print alert window
	 * 
	 * @author Lukas Schiefermueller
	 * @param delete
	 *            true if delete project, false otherwise
	 * @return true if ok is pressed
	 */
	private boolean alert(boolean delete) {
		// http://code.makery.ch/blog/javafx-dialogs-official/
		Alert alert;
		if (delete) {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Projekt löschen");
			alert.setHeaderText("Achtung");
			alert.setContentText("Wirklich löschen?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				return true;
			}
			return false;
		}
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Auswahl");
		alert.setContentText("Es ist nichts ausgewählt!");
		alert.showAndWait();
		return false;
	}
}
