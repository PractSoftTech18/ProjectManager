package application;

import java.text.SimpleDateFormat;
import java.util.Date;

import project.Priority;
import project.Status;

/**
 * Class similar to Task but only relies on String parameters Used for
 * creating/representation of tasks in tblTasks
 * 
 * @author Julia Hofer
 * @version 1.00, June 28th 2018
 */
public class TableTask {
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
	 * @author Julia Hofer
	 * @param name
	 *            the name of the task
	 * @param project the project of the task
	 * @param remark
	 *            the remark of the task
	 * @param status
	 *            the status of the task
	 * @param priority
	 *            the priority of the task
	 * @param date
	 *            the date of the task
	 */
	public TableTask(String name, String project, String remark, String status, String piority, String date) {
		this.name = name;
		this.project = project;
		this.remark = remark;
		this.status = status;
		this.priority = piority;
		this.date = date;
	}

	/**
	 * constructor with regarding types
	 * 
	 * @author Lukas Schiefermueller
	 * @param name
	 *            the name of the task
	 * @param remark
	 *            the remark of the task
	 * @param status
	 *            the status of the task
	 * @param priority
	 *            the priority of the task
	 * @param date
	 *            the date of the task
	 */
	public TableTask(String name, String project, String remark, Status status, Priority priority, Date date) {
		this(name, project, remark, "", "", "");
		String dateString = "";
		Date dateDate;
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		if ((dateDate = date) != null) {
			dateString = dateFormatter.format(dateDate);
		}
		String priorityString = "";
		Priority priorityPriority;
		if ((priorityPriority = priority) != null)
			priorityString = priorityPriority.toString();
		String statusString = "";
		Status statusStatus;
		if ((statusStatus = status) != null)
			statusString = statusStatus.getStatus();
		this.status = statusString;
		this.priority = priorityString;
		this.date = dateString;
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