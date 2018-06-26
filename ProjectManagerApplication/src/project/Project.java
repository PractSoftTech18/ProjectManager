package project;

import customer.*;
import javafx.scene.paint.Color;
import java.util.Date;
//import java.util.Scanner;
import java.util.ArrayList;

/**
 * In this class all details of a project are stored.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class Project {
	private String title;
	private String description;
	private String notes;
	private Status status;
	private Priority priority;
	private Color color;
	private Date deadline;
	private Date eventDate;
	private Customer customer;
	private ArrayList<Task> tasks;

	/**
	 * @param title
	 * @param description
	 * @param notes
	 * @param priority
	 * @param color
	 * @param open
	 * @param deadline
	 * @param eventDate
	 * @param customer
	 * @param tasks
	 */
	public Project(String title, String description, String notes, Priority priority, Color color, 
			Date deadline, Date eventDate, Customer customer, ArrayList<Task> tasks) {
		if(title.equals(""))
			this.title = "Projektname";
		else
			this.title = title;
		this.description = description;
		this.notes = notes;
		this.priority = priority;
		this.color = color;
		this.deadline = deadline;
		this.eventDate = eventDate;
		this.customer = customer;
		this.tasks = tasks;
	}

	/**
	 * 
	 */
	public Project() {
		// everything has to be set via setters
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if(title.equals(""))
			this.title = "Projektname";
		else
			this.title = title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
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
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param p
	 *            the color to set
	 */
	public void setColor(Color p) {
		this.color = p;
	}

	/**
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * @param deadline
	 *            the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * @return the tasks
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks
	 *            the tasks to set
	 */
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return a String representation of this project
	 */
	public String toString() {
		return title + "\t" + description + "\t" + priority;
	}
	
	/**
	 * @return String representation for saving into file
	 */
	public String toFile() {
		String ret = "";
		if(title != null)
			ret += title;
		ret += ";";
		if(description != null)
			ret += description;
		ret += ";";
		if(notes != null)
			ret += notes;
		ret += ";";
		if(status != null)
			ret += status.getStatus();
		ret += ";";
		if(priority != null)
			ret += priority.name();
		ret += ";";
		if(color != null) {
			ret += color.getRed() + ";";
			ret += color.getGreen() + ";";
			ret += color.getBlue();
		}
		ret += ";";
		if(deadline != null)
			ret += deadline.getTime();
		ret += ";";
		if(eventDate != null)
			ret += eventDate.getTime();
		ret += ";";
		return ret;
	}
}
