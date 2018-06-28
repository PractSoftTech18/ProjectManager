package project;

// Java imports
import java.util.Date;

/**
 * In this class all details of a task are stored.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 27th 2018
 */
public class Task {
	/**
	 * name of the task
	 */
	private String name;

	/**
	 * remark of the task
	 */
	private String remark;

	/**
	 * status of the task
	 */
	private Status status;

	/**
	 * priority of the task
	 */
	private Priority priority;

	/**
	 * date of the task
	 */
	private Date date;

	/**
	 * constructor
	 * 
	 * @param name
	 *            the name to set
	 * @param remark
	 *            the remark to set
	 * @param status
	 *            the status to set
	 * @param priority
	 *            the priority to set
	 * @param date
	 *            the date to set
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
	 * getter for name
	 * 
	 * @return the name of the task
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter for name
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * setter for remark
	 * 
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * getter for status
	 * 
	 * @return the status of the task
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * setter for status
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * getter for priority
	 * 
	 * @return the priority of the task
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * setter for priority
	 * 
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * getter for date
	 * 
	 * @return the date of the task
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * setter for date
	 * 
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * compute a String for saving into file
	 * 
	 * @return String representation for saving into file
	 */
	public String toFile() {
		String ret = "";
		if (name != null)
			ret += name;
		ret += ";";
		if (remark != null)
			ret += remark;
		ret += ";";
		if (status != null)
			ret += status.getStatus();
		ret += ";";
		if (priority != null)
			ret += priority.toString();
		ret += ";";
		if (date != null)
			ret += date.getTime();
		ret += ";";
		return ret;
	}
}
