package project;

import customer.*;
import java.awt.Color;
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
	private Color color; // https://docs.oracle.com/javase/7/docs/api/java/awt/Color.html
	//private boolean open;
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
		// super();
		this.title = title;
		this.description = description;
		this.notes = notes;
		this.priority = priority;
		this.color = color;
		//this.open = open;
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
		/*Scanner sc = new Scanner(System.in);
		System.out.print("Titel: ");
		this.setTitle(sc.nextLine());
		System.out.print("Beschreibung: ");
		this.setDescription(sc.nextLine());
		System.out.print("Anmerkungen: ");
		this.setDescription(sc.nextLine());
		System.out.print("Priority: 0=LOW\t1=NORMAL\t2=HIGH: ");
		int i = sc.nextInt();
		Priority p = i == 2 ? Priority.HIGH : (i == 1 ? Priority.NORMAL : Priority.LOW);
		this.setPriority(p);
		this.setColor(new Color(0,255,0));
		this.setOpen(true);
		this.setDeadline(new Date(1234));
		this.setEventDate(new Date(2234));
		tasks = new ArrayList<Task>();
		// sc.close();*/
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
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/*
	 * @return the open
	 */
	/*public boolean isOpen() {
		return open;
	}*/

	/*
	 * @param open
	 *            the open to set
	 */
	/*public void setOpen(boolean open) {
		this.open = open;
	}*/

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

	public String toString() {
		return title + "\t" + description + "\t" + priority;
	}
}
