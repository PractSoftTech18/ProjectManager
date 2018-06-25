package project;

import java.util.Date;

/**
 * In this class all details of a task are stored.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class Task {
	private String name;
	private String remark;
	private Status status;
	private Priority priority;
	// private boolean taskOpen;
	private Date date;

	/**
	 * @param name
	 * @param remark
	 * @param priority
	 * @param taskOpen
	 * @param date
	 */
	public Task(String taskName, String taskRemark, Status taskStatus, Priority taskPriority, Date taskDate) {
		// super();
		this.name = taskName;
		this.remark = taskRemark;
		this.status = taskStatus;
		this.priority = taskPriority;
		//this.taskOpen = taskOpen;
		this.date = taskDate;
	}

	/**
	 * 
	 */
	public Task() {
		// everything has to be set via setters
	}
	
	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return name;
	}

	/**
	 * @param taskName
	 *            the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.name = taskName;
	}

	/**
	 * @return the taskRemark
	 */
	public String getTaskRemark() {
		return remark;
	}

	/**
	 * @param taskRemark
	 *            the taskRemark to set
	 */
	public void setTaskRemark(String taskRemark) {
		this.remark = taskRemark;
	}

	/**
	 * @return the taskStatus
	 */
	public Status getTaskStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the taskStatus to set
	 */
	public void setTaskStatus(Status taskStatus) {
		this.status = taskStatus;
	}
	
	/**
	 * @return the taskPriority
	 */
	public Priority getTaskPriority() {
		return priority;
	}

	/**
	 * @param taskPriority
	 *            the taskPriority to set
	 */
	public void setTaskPriority(Priority taskPriority) {
		this.priority = taskPriority;
	}

	/*
	 * @return the taskOpen
	 */
	/*public boolean isTaskOpen() {
		return taskOpen;
	}*/

	/*
	 * @param taskOpen
	 *            the taskOpen to set
	 */
	/*public void setTaskOpen(boolean taskOpen) {
		this.taskOpen = taskOpen;
	}*/

	/**
	 * @return the taskDate
	 */
	public Date getTaskDate() {
		return date;
	}

	/**
	 * @param taskDate
	 *            the taskDate to set
	 */
	public void setTaskDate(Date taskDate) {
		this.date = taskDate;
	}

	public String toFile() {
		return name + ";" + remark + ";" + status.getStatus() + ";" + priority.name() + ";" +  date.getTime();
	}

}
