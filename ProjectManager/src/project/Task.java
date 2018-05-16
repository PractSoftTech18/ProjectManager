/**
 * 
 */
package project;

import java.util.Date;

/**
 * @author Lukas Schiefermüller
 *
 */
public class Task {
	private String taskName;
	private String taskRemark;
	private Priority taskPriority;
	private boolean taskOpen;
	private Date taskDate;

	/**
	 * @param taskName
	 * @param taskRemark
	 * @param taskPriority
	 * @param taskOpen
	 * @param taskDate
	 */
	public Task(String taskName, String taskRemark, Priority taskPriority, boolean taskOpen, Date taskDate) {
		// super();
		this.taskName = taskName;
		this.taskRemark = taskRemark;
		this.taskPriority = taskPriority;
		this.taskOpen = taskOpen;
		this.taskDate = taskDate;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName
	 *            the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * @return the taskRemark
	 */
	public String getTaskRemark() {
		return taskRemark;
	}

	/**
	 * @param taskRemark
	 *            the taskRemark to set
	 */
	public void setTaskRemark(String taskRemark) {
		this.taskRemark = taskRemark;
	}

	/**
	 * @return the taskPriority
	 */
	public Priority getTaskPriority() {
		return taskPriority;
	}

	/**
	 * @param taskPriority
	 *            the taskPriority to set
	 */
	public void setTaskPriority(Priority taskPriority) {
		this.taskPriority = taskPriority;
	}

	/**
	 * @return the taskOpen
	 */
	public boolean isTaskOpen() {
		return taskOpen;
	}

	/**
	 * @param taskOpen
	 *            the taskOpen to set
	 */
	public void setTaskOpen(boolean taskOpen) {
		this.taskOpen = taskOpen;
	}

	/**
	 * @return the taskDate
	 */
	public Date getTaskDate() {
		return taskDate;
	}

	/**
	 * @param taskDate
	 *            the taskDate to set
	 */
	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

}
