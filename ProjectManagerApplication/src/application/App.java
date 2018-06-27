package application;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

import files.FileHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
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
 * @version 1.00, June 27th 2018
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
	private Button btnActualize, btnCreateProject, btnEdit, btnTasks, btnAllTasks, btnDelete, btnIncreaseStatus;

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

	@FXML
	private ObservableList<TableStruct> projects;

	@FXML
	private ObservableList<TableTask> taskList;

	/**
	 * Class similar to Task but only relies on String parameters Used for
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
		 * constructor
		 * 
		 * @author Julia Hofer
		 * @param tname
		 *            the name of the task
		 * @param tremark
		 *            the remark of the task
		 * @param tstatus
		 *            the status of the task
		 * @param tpriority
		 *            the priority of the task
		 * @param tdate
		 *            the date of the task
		 */
		public TableTask(String tname, String tremark, String tstatus, String tpiority, String tdate) {
			this.tname = tname;
			this.tremark = tremark;
			this.tstatus = tstatus;
			this.tpriority = tpiority;
			this.tdate = tdate;
		}

		/**
		 * constructor with regarding types
		 * 
		 * @author Lukas Schiefermueller
		 * @param tname
		 *            the name of the task
		 * @param tremark
		 *            the remark of the task
		 * @param tstatus
		 *            the status of the task
		 * @param tpriority
		 *            the priority of the task
		 * @param tdate
		 *            the date of the task
		 */
		public TableTask(String tname, String tremark, Status tstatus, Priority tpriority, Date tdate) {
			this(tname, tremark, "", "", "");
			String dateString = "";
			Date date;
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
			if ((date = tdate) != null) {
				dateString = dateFormatter.format(date);
			}
			String priorityString = "";
			Priority priority;
			if ((priority = tpriority) != null)
				priorityString = priority.toString();
			String statusString = "";
			Status status;
			if ((status = tstatus) != null)
				statusString = status.getStatus();
			this.tstatus = statusString;
			this.tpriority = priorityString;
			this.tdate = dateString;
		}

		/**
		 * getter for tname
		 * 
		 * @return the name of the task
		 */
		public String getTname() {
			return tname;
		}

		/**
		 * getter for tremark
		 * 
		 * @return the remark of the task
		 */
		public String getTremark() {
			return tremark;
		}

		/**
		 * getter for tstatus
		 * 
		 * @return the status of the task
		 */
		public String getTstatus() {
			return tstatus;
		}

		/**
		 * getter for tpriority
		 * 
		 * @return the priority of the task
		 */
		public String getTpriority() {
			return tpriority;
		}

		/**
		 * getter for tdate
		 * 
		 * @return the date of the task
		 */
		public String getTdate() {
			return tdate;
		}
	}

	/**
	 * used for filling tblProjects
	 * 
	 * @author Lukas Schiefermueller
	 */
	public class TableStruct {
		/**
		 * the title of the project
		 */
		private String title;

		/**
		 * the status of the project
		 */
		private String status;

		/**
		 * the date of the project
		 */
		private String date;

		/**
		 * constructor
		 * 
		 * @param title
		 *            the title to set
		 * @param status
		 *            the status to set
		 * @param date
		 *            the date to set
		 */
		public TableStruct(String title, String status, String date) {
			this.title = title;
			this.status = status;
			this.date = date;
		}

		/**
		 * getter for title
		 * 
		 * @return the title of the project
		 */
		public String getTitle() {
			return title;
		}

		/**
		 * getter for status
		 * 
		 * @return the status of the project
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * getter for date
		 * 
		 * @return the date of the project
		 */
		public String getDate() {
			return date;
		}
	}

	/**
	 * initialize MyScene
	 */
	@FXML
	public void initialize() {
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
		taskList = FXCollections.observableArrayList();

		updateTblProjects();

	}

	/**
	 * actualize projects
	 * 
	 * @author Lukas Schiefermueller
	 * @param event
	 *            select button actualize
	 */
	public void actualize(ActionEvent event) {
		updateTblProjects();
	}

	/**
	 * change to new project tab
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select create project
	 * @throws IOException
	 */
	public void createNewProject(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CreateProject.fxml"));
		Tab tabNewProject = new Tab("Neues Projekt");
		tabNewProject.setContent(loader.load());
		MyTabPane.getTabs().add(tabNewProject);
		MyTabPane.getSelectionModel().select(tabNewProject);
	}

	/**
	 * change to edit project tab
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select edit
	 * @throws IOException
	 */
	public void btnEdit(ActionEvent event) throws IOException {
		TableStruct tblStruct = tblProjects.getSelectionModel().getSelectedItem();
		if (tblStruct != null) {
			Data.getProject(tblStruct.getTitle()); // set selectedInternally
			ourData.selected = ourData.selectedInternally;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditProject.fxml"));
			Tab tabEditProject = new Tab("Bearbeiten");
			tabEditProject.setContent(loader.load());
			MyTabPane.getTabs().add(tabEditProject);
			MyTabPane.getSelectionModel().select(tabEditProject);
		} else {
			alert(false);
		}
	}

	/**
	 * change to all tasks tab
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 *            select all tasks
	 * @throws IOException
	 */
	public void viewAllTasks(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Tasks.fxml"));
		Tab tabTasks = new Tab("Tasks");
		tabTasks.setContent(loader.load());
		MyTabPane.getTabs().add(tabTasks);
		MyTabPane.getSelectionModel().select(tabTasks);
	}

	/**
	 * view all tasks of the selected project
	 * 
	 * @author Lukas Schiefermueller
	 * @param event
	 *            select project tasks
	 * @throws IOException
	 */
	public void viewTasks(ActionEvent event) throws IOException {
		TableStruct tblStruct = tblProjects.getSelectionModel().getSelectedItem();
		if (tblStruct != null) {
			taskList.clear();
			Data.getProject(tblStruct.getTitle()); // set selectedInternally
			ourData.selected = ourData.selectedInternally;
			updateTblTasks();
			updateTblProjects();
		} else
			alert(false);
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
			alert.setTitle("Projekt l�schen");
			alert.setHeaderText("Achtung");
			alert.setContentText("Projekt wirklich l�schen?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				return true;
			}
			return false;
		}
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Auswahl");
		alert.setContentText("Kein Projekt ausgew�hlt!");
		alert.showAndWait();
		return false;
	}

	/**
	 * delete the selected project
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 *            select button delete
	 */
	public void delete(ActionEvent event) {
		TableStruct tblStruct = tblProjects.getSelectionModel().getSelectedItem();
		if (tblStruct != null) {
			if (alert(true)) {
				Project project = Data.getProject(tblStruct.getTitle());
				ourFileHandler.delete(project);
				updateTblProjects();
			}
		} else
			alert(false);
	}

	/**
	 * increase status of selected project or task
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 *            select button increase status
	 */
	public void increaseStatus(ActionEvent event) {
		TableStruct tblStruct = tblProjects.getSelectionModel().getSelectedItem();
		TableTask tableTask = tblTasks.getSelectionModel().getSelectedItem();
		Project project = new Project();
		Task task = new Task();
		int selected = ourData.selected;
		if (selected >= 0 && selected < ourData.projects.size())
			project = ourData.projects.get(selected);
		if (tableTask != null) {
			for (int i = 0; i < project.getTasks().size(); i++) {
				if (project.getTasks().get(i).getName().equals(tableTask.getTname())) {
					task = project.getTasks().get(i);
					break;
				}
			}
			if (task.getStatus() != null)
				task.setStatus(task.getStatus().increaseStatus());
			ourFileHandler.change(project, project.getTitle());
			updateTblTasks();
		} else if (tblStruct != null) {
			project = Data.getProject(tblStruct.getTitle());
			if (project.getStatus() != null)
				project.setStatus(project.getStatus().increaseStatus());
			ourFileHandler.change(project, project.getTitle());
			updateTblProjects();
		} else
			alert(false);
	}

	/**
	 * refresh the table of tasks
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 */
	public void updateTblTasks() {
		taskList.clear();
		Project project = ourData.projects.get(ourData.selected);
		ArrayList<Task> allTasks = project.getTasks();
		for (Task task : allTasks) {
			taskList.add(new TableTask(task.getName(), task.getRemark(), task.getStatus(), task.getPriority(),
					task.getDate()));

		}
		tblTasks.setItems(taskList);

		// status color
		tblColStatusT.setCellFactory(column -> {
			return new TableCell<TableTask, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty || item.equals("")) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						Status status = Status.returnStatus(item, true);
						String style = "-fx-background-color: rgb(" + (status.getColor().getRed() * 255) + ", "
								+ (status.getColor().getGreen() * 255) + ", " + (status.getColor().getBlue() * 255)
								+ ")";
						setStyle(style);
					}
				}
			};
		});
	}

	/**
	 * refresh the table of projects
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 */
	public void updateTblProjects() {
		projects.clear();
		for (Iterator<Project> it = ourData.projects.iterator(); it.hasNext();) {
			Project project = it.next();
			Date date;
			SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
			String dateString = "";
			if ((date = project.getEventDate()) != null) {
				dateString = dateFormatter.format(date);
			}
			projects.add(new TableStruct(project.getTitle(), project.getStatus().getStatus(), dateString));

		}
		tblProjects.setItems(projects);
		updateTblColor();
	}

	/**
	 * update background colors
	 * 
	 * @author Lydia Grillenberger
	 */
	public void updateTblColor() {
		// http://code.makery.ch/blog/javafx-8-tableview-cell-renderer/

		// status
		tblColStatus.setCellFactory(column -> {
			return new TableCell<TableStruct, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty || item.equals("")) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						Status status = Status.returnStatus(item, false);
						String style = "-fx-background-color: rgb(" + (status.getColor().getRed() * 255) + ", "
								+ (status.getColor().getGreen() * 255) + ", " + (status.getColor().getBlue() * 255)
								+ ")";
						setStyle(style);
					}
				}
			};
		});

		// project color
		tblColProjects.setCellFactory(column -> {
			return new TableCell<TableStruct, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty || item.equals("")) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						Project project = Data.getProject(item);
						if (project.getColor() != null) {
							String style = "-fx-background-color: rgb(" + (project.getColor().getRed() * 255) + ", "
									+ (project.getColor().getGreen() * 255) + ", "
									+ (project.getColor().getBlue() * 255) + ")";
							setStyle(style);
						}
					}
				}
			};
		});
	}
}
