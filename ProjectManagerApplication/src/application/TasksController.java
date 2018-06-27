package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.Priority;
import project.Project;
import project.Status;
import project.Task;

/**
 * controller for Tasks
 * 
 * @author Lydia Grillenberger
 * @version 1.00, June 28th 2018
 */
public class TasksController {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	@FXML
	private TableView<TableTask> tblTasks;

	@FXML
	private TableColumn<TableTask, String> tblColTask, tblColProject, tblColRemark, tblColStatus, tblColPriority,
			tblColDate;

	@FXML
	private ObservableList<TableTask> listTask;

	/**
	 * initialize view of tasks
	 * 
	 * @author Lydia Grillenberger
	 */
	@FXML
	public void initialize() {
		tblColTask.setCellValueFactory(new PropertyValueFactory<TableTask, String>("name"));
		tblColProject.setCellValueFactory(new PropertyValueFactory<TableTask, String>("project"));
		tblColRemark.setCellValueFactory(new PropertyValueFactory<TableTask, String>("remark"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TableTask, String>("status"));
		tblColPriority.setCellValueFactory(new PropertyValueFactory<TableTask, String>("priority"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TableTask, String>("date"));
		listTask = FXCollections.observableArrayList();
		fillTable();
	}

	/**
	 * fill the table with all tasks
	 */
	public void fillTable() {
		Project project;
		Task task;
		Status status;
		String statusString;
		Priority priority;
		String priorityString;
		Date date;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		String dateString;
		for (int i = 0; i < ourData.projects.size(); i++) {
			project = ourData.projects.get(i);
			for (int j = 0; j < project.getTasks().size(); j++) {
				task = project.getTasks().get(j);
				statusString = "";
				priorityString = "";
				dateString = "";
				if ((status = task.getStatus()) != null)
					statusString = status.getStatus();
				if ((priority = task.getPriority()) != null)
					priorityString = priority.toString();
				if ((date = task.getDate()) != null)
					dateString = dateFormatter.format(date);
				listTask.add(new TableTask(task.getName(), project.getTitle(), task.getRemark(), statusString,
						priorityString, dateString));
			}
		}
		tblTasks.setItems(listTask);

		// project color
		tblColProject.setCellFactory(column -> {
			return new TableCell<TableTask, String>() {
				@Override
				protected void updateItem(String item, boolean empty) {
					super.updateItem(item, empty);
					if (item == null || empty || item.equals("")) {
						setText(null);
						setStyle("");
					} else {
						setText(item);
						Project project = new Project();
						for (int i = 0; i < ourData.projects.size(); i++) {
							if ((project = ourData.projects.get(i)).getTitle().equals(item)) {
								break;
							}
						}
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

		// status color
		tblColStatus.setCellFactory(column -> {
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
}
