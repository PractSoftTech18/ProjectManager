package application;

// JavaFX imports
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

// Java imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// ProjectManager imports
import project.Project;
import project.Status;
import project.Task;

/**
 * controller for App
 * 
 * @author Lydia Grillenberger
 * @author Julia Hofer
 * @author Lukas Schiefermueller
 * @version 1.00, June 28th 2018
 */
public class AppController extends Controller {
	@FXML
	private TabPane MyTabPane;

	@FXML
	private Tab MyMainTab, createProject;

	@FXML
	private GridPane MyGridPane;

	@FXML
	private ListView<String> MyListView;

	@FXML
	private Button btnProjectView, btnActualize, btnCreateProject, btnEdit, btnTasks, btnAllTasks, btnDelete,
			btnIncreaseStatus;

	@FXML
	private TableView<TableProject> tblProjects;

	@FXML
	private TableColumn<TableProject, String> tblColProjects, tblColStatus, tblColDateEvent, tblColDateDeadline;

	@FXML
	private TableColumn<TableTask, String> tblColTaskT, tblColRemarkT, tblColStatusT, tblColPriorityT, tblColDateT;

	@FXML
	private TableView<TableTask> tblTasks;

	@FXML
	private ObservableList<TableProject> projects;

	@FXML
	private ObservableList<TableTask> taskList;

	@FXML
	/**
	 * initialize MyScene
	 */
	public void initialize() {
		ourFileHandler.read();

		// adding data to table projects
		tblColProjects.setCellValueFactory(new PropertyValueFactory<TableProject, String>("title"));
		tblColDateEvent.setCellValueFactory(new PropertyValueFactory<TableProject, String>("event"));
		tblColDateDeadline.setCellValueFactory(new PropertyValueFactory<TableProject, String>("deadline"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TableProject, String>("status"));
		projects = FXCollections.observableArrayList();

		// adding data to table tasks
		tblColTaskT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("name"));
		tblColRemarkT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("remark"));
		tblColStatusT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("status"));
		tblColPriorityT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("priority"));
		tblColDateT.setCellValueFactory(new PropertyValueFactory<TableTask, String>("date"));
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
		TableProject tblProject = tblProjects.getSelectionModel().getSelectedItem();
		if (tblProject != null) {
			ourData.selected = Data.getIndex(tblProject.getTitle());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/EditProject.fxml"));
			Tab tabEditProject = new Tab(tblProject.getTitle());
			tabEditProject.setContent(loader.load());
			MyTabPane.getTabs().add(tabEditProject);
			MyTabPane.getSelectionModel().select(tabEditProject);
		} else {
			alert(AlertType.ERROR);
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
		TableProject tblProject = tblProjects.getSelectionModel().getSelectedItem();
		if (tblProject != null) {
			taskList.clear();
			ourData.selected = Data.getIndex(tblProject.getTitle());
			updateTblTasks();
			updateTblProjects();
		} else
			alert(AlertType.ERROR);
	}

	/**
	 * delete the selected project
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 *            select button delete
	 */
	public void delete(ActionEvent event) {
		TableProject tblProject = tblProjects.getSelectionModel().getSelectedItem();
		if (tblProject != null) {
			if (alert(AlertType.CONFIRMATION)) {
				Project project = Data.getProject(tblProject.getTitle());
				ourFileHandler.delete(project);
				updateTblProjects();
			}
		} else
			alert(AlertType.ERROR);
	}

	/**
	 * increase status of selected project or task
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 *            select button increase status
	 */
	public void increaseStatus(ActionEvent event) {
		TableProject tblProject = tblProjects.getSelectionModel().getSelectedItem();
		TableTask tableTask = tblTasks.getSelectionModel().getSelectedItem();
		Project project = new Project();
		Task task = new Task();
		int selected = ourData.selected;
		if (selected >= 0 && selected < ourData.projects.size())
			project = ourData.projects.get(selected);
		if (tableTask != null) {
			for (int i = 0; i < project.getTasks().size(); i++) {
				if (project.getTasks().get(i).getName().equals(tableTask.getName())) {
					task = project.getTasks().get(i);
					break;
				}
			}
			if (task.getStatus() != null)
				task.setStatus(task.getStatus().increaseStatus());
			ourFileHandler.change(project, project.getTitle());
			updateTblTasks();
		} else if (tblProject != null) {
			project = Data.getProject(tblProject.getTitle());
			if (project.getStatus() != null)
				project.setStatus(project.getStatus().increaseStatus());
			ourFileHandler.change(project, project.getTitle());
			updateTblProjects();
		} else
			alert(AlertType.ERROR);
	}

	@FXML
	/**
	 * change to project view
	 * 
	 * @author Julia Hofer
	 * @param event
	 *            select button project view
	 */
	public void btnProjectView(ActionEvent event) {
		TableProject tblProject = tblProjects.getSelectionModel().getSelectedItem();
		if (tblProject != null) {
			ourData.selected = Data.getIndex(tblProject.getTitle());
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/ProjectView.fxml"));
			Tab tabEditProject = new Tab(tblProject.getTitle());
			try {
				tabEditProject.setContent(loader.load());
			} catch (IOException e) {
				e.printStackTrace();
			}
			MyTabPane.getTabs().add(tabEditProject);
			MyTabPane.getSelectionModel().select(tabEditProject);
		} else {
			alert(AlertType.ERROR);
		}
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
			taskList.add(new TableTask(task.getName(), "", task.getRemark(), task.getStatus(), task.getPriority(),
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
			projects.add(new TableProject(project.getTitle(), project.getStatus(), project.getEventDate(),
					project.getDeadline()));

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
			return new TableCell<TableProject, String>() {
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
			return new TableCell<TableProject, String>() {
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
