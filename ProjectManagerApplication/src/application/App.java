package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import application.CreateProjectController.TableTask;
import files.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import project.Priority;
import project.Project;
import project.Status;
import project.Task;

/**
 * controller for MyScene
 * 
 * @author Lydia Grillenberger
 * @author Julia Hofer
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class App {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	/**
	 * FileHandler
	 */
	private FileHandler ourFileHandler = FileHandler.getFileHandler();

	@FXML
	private TabPane MyTabPane;

	@FXML
	private Tab MyMainTab, createProject;

	@FXML
	private GridPane MyGridPane;

	@FXML
	private ListView<String> MyListView;

	@FXML
	private Button MyButton, btnCreateProject, btnEdit, btnTasks;

	@FXML
	private TableView<TableStruct> tblProjects;

	@FXML
	private TableColumn<TableStruct, String> tblColProjects;

	@FXML
	private TableColumn<TableStruct, String> tblColStatus;

	@FXML
	private TableColumn<TableStruct, String> tblColDate;

	@FXML
	private TableColumn<TableTask, String> tblColTaskT, tblColRemarkT, tblColStatusT, tblColPriorityT, tblColDateT;

	@FXML
	private TableView<TableTask> tblTasks;

	private static int buttonclicked;

	private ObservableList<TableStruct> projects;

	private ObservableList<TableTask> task;

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

	public class TableStruct {
		private String title, date, status;

		public TableStruct(String s, String st, String d) {
			title = s;
			status = st;
			date = d;
		}

		public String getTitle() {
			return title;
		}

		public String getDate() {
			return date;
		}

		public String getStatus() {
			return status;
		}
	}

	@FXML
	public void initialize() {
		/*
		 * list = new ArrayList<>(); for (int i = 0; i < 5; i++) { list.add(new
		 * TableStruct("Titel " + i, "Status " + i, "Datum " + i)); }
		 * System.out.println(list.size());
		 */
		ourFileHandler.read();

		tblColProjects.setCellValueFactory(new PropertyValueFactory<TableStruct, String>("title"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TableStruct, String>("date"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TableStruct, String>("status"));

		projects = FXCollections.observableArrayList();

		// adding data to table tasks
		tblColTaskT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tname"));
		tblColRemarkT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tremark"));
		tblColStatusT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tstatus"));
		tblColPriorityT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tpriority"));
		tblColDateT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("tdate"));
		task = FXCollections.observableArrayList();

		updateTblProjects();

	}

	// When user click on myButton
	// this method will be called.
	public void addFieldtoVBox(ActionEvent event) {
		/*
		 * ObservableList<String> items = MyListView.getItems();
		 * items.add(Integer.toString(++buttonclicked)); MyListView.setItems(items);
		 */
		updateTblProjects();
	}

	/**
	 * change to new project tab
	 * 
	 * @author Julia Hofer
	 * @param event
	 * @throws IOException
	 */
	public void createNewProject(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CreateProject.fxml"));
		Tab tabNewProject = new Tab("Neues Projekt");
		tabNewProject.setContent(loader.load());
		MyTabPane.getTabs().add(tabNewProject);
		MyTabPane.getSelectionModel().select(tabNewProject);
		// updateTblProjects();
	}

	/**
	 * change to status settings tab
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 * @throws IOException
	 */
	public void btnEdit(ActionEvent event) throws IOException {
		TableStruct tblStruct = tblProjects.getSelectionModel().getSelectedItem();
		if (tblStruct != null) {
			for (int i = 0; i < ourData.projects.size(); i++) {
				if (ourData.projects.get(i).getTitle().equals(tblStruct.getTitle())) {
					ourData.selected = i;
					break;
				}
			}
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditProject.fxml"));
			Tab tabEditProject = new Tab("Bearbeiten");
			tabEditProject.setContent(loader.load());
			MyTabPane.getTabs().add(tabEditProject);
			MyTabPane.getSelectionModel().select(tabEditProject);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Auswahl");
			alert.setContentText("Kein Projekt ausgewählt!");
			alert.showAndWait();
		}
	}

	/**
	 * change to tasks tab
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 * @throws IOException
	 */
	public void viewTasks(ActionEvent event) throws IOException {
		/*
		 * FXMLLoader loader = new
		 * FXMLLoader(getClass().getResource("/application/Tasks.fxml")); Tab
		 * tabStatusSettings = new Tab("Tasks");
		 * tabStatusSettings.setContent(loader.load());
		 * MyTabPane.getTabs().add(tabStatusSettings);
		 * MyTabPane.getSelectionModel().select(tabStatusSettings);
		 */
		TableStruct tblStruct = tblProjects.getSelectionModel().getSelectedItem();
		Project p = new Project();
		if (tblStruct != null) {
			task.clear();
			for (int i = 0; i < ourData.projects.size(); i++) {
				if (ourData.projects.get(i).getTitle().equals(tblStruct.getTitle())) {
					p = ourData.projects.get(i);
					break;
				}
			}

			ArrayList<Task> Tt = p.getTasks();
			for (Task t : Tt) {
				String dateString = "";
				Date d;
				SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
				if ((d = t.getDate()) != null) {
					dateString = dateFormatter.format(d);
				}
				String prio = "";
				Priority P;
				if ((P = t.getPriority()) != null)
					prio = P.toString();
				String stat = "";
				Status S;
				if ((S = t.getStatus()) != null)
					stat = S.getStatus();
				task.add(new TableTask(t.getName(), t.getRemark(), stat,
						prio, dateString));

			}
			tblTasks.setItems(task);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Auswahl");
			alert.setContentText("Kein Projekt ausgewählt!");
			alert.showAndWait();
		}
	}

	/**
	 * refresh the table of projects
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 */
	public void updateTblProjects() {
		for (Iterator<Project> pro = ourData.projects.iterator(); pro.hasNext();) {
			Project p = pro.next();
			Date d;
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
			String dateString = "";
			if ((d = p.getEventDate()) != null) {
				dateString = dateFormatter.format(d);
			}
			projects.add(new TableStruct(p.getTitle(), p.getStatus().getStatus(), dateString));

		}
		tblProjects.setItems(projects);

	}
}
