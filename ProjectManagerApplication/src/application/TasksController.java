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
 * @version 1.00, June 26th 2018
 */
public class TasksController {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();
	
	@FXML
	private TableView<TblTask> tblTask;
	
	@FXML
	private TableColumn<TblTask, String> tblColTask, tblColProject, tblColRemark, tblColStatus,
		tblColPriority, tblColDate;
	
	@FXML
	private ObservableList<TblTask> listTask;
	
	/**
	 * initialize view of tasks
	 * 
	 * @author Lydia Grillenberger
	 */
	@FXML
	void initialize() {
		tblColTask.setCellValueFactory(new PropertyValueFactory<TblTask, String>("name"));
		tblColProject.setCellValueFactory(new PropertyValueFactory<TblTask, String>("project"));
		tblColRemark.setCellValueFactory(new PropertyValueFactory<TblTask, String>("remark"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TblTask, String>("status"));
		tblColPriority.setCellValueFactory(new PropertyValueFactory<TblTask, String>("priority"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TblTask, String>("date"));
		listTask = FXCollections.observableArrayList();
		
		Project project;
		Task task;
		Status status;
		String statusString;
		Priority priority;
		String priorityString;
		Date date;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		String dateString;
		for(int i = 0; i < ourData.projects.size(); i++) {
			project = ourData.projects.get(i);
			for(int j = 0; j < project.getTasks().size(); j++) {
				task = project.getTasks().get(j);
				statusString = "";
				priorityString = "";
				dateString = "";
				if((status = task.getStatus()) != null)
					 statusString = status.getStatus();
				if((priority = task.getPriority()) != null)
					 priorityString = priority.toString();
				if ((date = task.getDate()) != null)
					dateString = dateFormatter.format(date);
				listTask.add(new TblTask(task.getName(), project.getTitle(), task.getRemark(), statusString, 
						priorityString, dateString));
			}
		}
		tblTask.setItems(listTask);
	}
	
	/**
	 * class for filling the table with tasks
	 * 
	 * @author Lydia Grillenberger
	 */
	public class TblTask {
		private String name;
		private String project;
		private String remark;
		private String status;
		private String priority;
		private String date;

		/**
		 * @param name the name of this task
		 * @param project the project of this task
		 * @param remark the remark of this task
		 * @param status the status of this task
		 * @param priority the priority of this task
		 * @param date the date of this task
		 */
		public TblTask(String name, String project, String remark, String status, String priority, String date) {
			this.name = name;
			this.project = project;
			this.remark = remark;
			this.status = status;
			this.priority = priority;
			this.date = date;
		}

		/*public String toFile() {
			String s = tname + " " + tremark + " " + tstatus + " " + tpriority + " " + tdate;
			return s;
		}*/

		public String getName() {
			return name;
		}
		
		public String getProject() {
			return project;
		}

		public String getRemark() {
			return remark;
		}

		public String getStatus() {
			return status;
		}

		public String getPriority() {
			return priority;
		}

		public String getDate() {
			return date;
		}
	}
}
