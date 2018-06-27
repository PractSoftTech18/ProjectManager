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
	private Date date;

	/**
	 * constructor
	 * 
	 * @param name the name of this task
	 * @param remark the remark of this task
	 * @param status the status of this task
	 * @param priority the priority of this task
	 * @param date the date of this task
	 */
	public Task(String name, String remark, Status status, Priority priority, Date date) {
		this.name = name;
		this.remark = remark;
		this.status = status;
		this.priority = priority;
		this.date = date;
	}

	/**
	 * default constructor
	 */
	public Task() {
		// everything has to be set via setters
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return String representation for saving into file
	 */
	public String toFile() {
		String ret = "";
		if(name != null)
			ret += name;
		ret += ";";
		if(remark != null)
			ret += remark;
		ret += ";";
		if(status != null)
			ret += status.getStatus();
		ret += ";";
		if(priority != null)
			ret += priority.toString();
		ret += ";";
		if(date != null)
			ret += date.getTime();
		ret += ";";
		return ret;
	}
}
