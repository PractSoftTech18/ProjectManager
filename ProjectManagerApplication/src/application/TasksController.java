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
 * @version 1.00, June 27th 2018
 */
public class TasksController {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	@FXML
	private TableView<TblTask> tblTasks;

	@FXML
	private TableColumn<TblTask, String> tblColTask, tblColProject, tblColRemark, tblColStatus, tblColPriority,
			tblColDate;

	@FXML
	private ObservableList<TblTask> listTask;

	/**
	 * initialize view of tasks
	 * 
	 * @author Lydia Grillenberger
	 */
	@FXML
	public void initialize() {
		tblColTask.setCellValueFactory(new PropertyValueFactory<TblTask, String>("name"));
		tblColProject.setCellValueFactory(new PropertyValueFactory<TblTask, String>("project"));
		tblColRemark.setCellValueFactory(new PropertyValueFactory<TblTask, String>("remark"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TblTask, String>("status"));
		tblColPriority.setCellValueFactory(new PropertyValueFactory<TblTask, String>("priority"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TblTask, String>("date"));
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
				listTask.add(new TblTask(task.getName(), project.getTitle(), task.getRemark(), statusString,
						priorityString, dateString));
			}
		}
		tblTasks.setItems(listTask);

		// project color
		tblColProject.setCellFactory(column -> {
			return new TableCell<TblTask, String>() {
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
			return new TableCell<TblTask, String>() {
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
	 * class for filling the table with tasks
	 * 
	 * @author Lydia Grillenberger
	 */
	public class TblTask {
		/**
		 * the name of the task
		 */
		private String name;

		/**
		 * the project of the task
		 */
		private String project;

		/**
		 * the remark of the task
		 */
		private String remark;

		/**
		 * the status of the task
		 */
		private String status;

		/**
		 * the priority of the task
		 */
		private String priority;

		/**
		 * the date of the task
		 */
		private String date;

		/**
		 * constructor
		 * 
		 * @param name
		 *            the name of the task
		 * @param project
		 *            the project of the task
		 * @param remark
		 *            the remark of the task
		 * @param status
		 *            the status of the task
		 * @param priority
		 *            the priority of the task
		 * @param date
		 *            the date of the task
		 */
		public TblTask(String name, String project, String remark, String status, String priority, String date) {
			this.name = name;
			this.project = project;
			this.remark = remark;
			this.status = status;
			this.priority = priority;
			this.date = date;
		}

		/**
		 * getter for name
		 * 
		 * @return the name of the task
		 */
		public String getName() {
			return name;
		}

		/**
		 * getter for project
		 * 
		 * @return the project of the task
		 */
		public String getProject() {
			return project;
		}

		/**
		 * getter for remark
		 * 
		 * @return the remark of the task
		 */
		public String getRemark() {
			return remark;
		}

		/**
		 * getter for status
		 * 
		 * @return the status of the task
		 */
		public String getStatus() {
			return status;
		}

		/**
		 * getter for priority
		 * 
		 * @return the priority of the task
		 */
		public String getPriority() {
			return priority;
		}

		/**
		 * getter for date
		 * 
		 * @return the date of the task
		 */
		public String getDate() {
			return date;
		}
	}
}
