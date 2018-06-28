package project;

// JavaFX imports
import javafx.scene.paint.Color;
import java.util.Date;

// Java imports
import java.util.ArrayList;

// ProjectManager imports
import customer.Customer;

/**
 * In this class all details of a project are stored.
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 27th 2018
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
	 * constructor
	 * 
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
	public Project(String title, String description, String notes, Priority priority, Color color, Date deadline,
			Date eventDate, Customer customer, ArrayList<Task> tasks) {
		if (title.equals(""))
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
	 * default constructor
	 */
	public Project() {
		// everything has to be set via setters
	}

	/**
	 * getter of title
	 * 
	 * @return the title of the project
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * setter of title
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		if (title.equals(""))
			this.title = "Projektname";
		else
			this.title = title;
	}

	/**
	 * getter of description
	 * 
	 * @return the description of the project
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * setter of description
	 * 
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * getter of notes
	 * 
	 * @return the notes of the project
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * setter of notes
	 * 
	 * @param notes
	 *            the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * getter of status
	 * 
	 * @return the status of the project
	 */
	public Status getStatus() {
		return status;
	}

	/**
	 * setter of status
	 * 
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	/**
	 * getter of priority
	 * 
	 * @return the priority of the project
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * setter of priority
	 * 
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * getter of color
	 * 
	 * @return the color of the project
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * setter of color
	 * 
	 * @param p
	 *            the color to set
	 */
	public void setColor(Color p) {
		this.color = p;
	}

	/**
	 * getter of deadline
	 * 
	 * @return the deadline of the project
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * setter of deadline
	 * 
	 * @param deadline
	 *            the deadline to set
	 */
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	/**
	 * getter of event date
	 * 
	 * @return the eventDate of the project
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * setter of event date
	 * 
	 * @param eventDate
	 *            the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * getter of customer
	 * 
	 * @return the customer of the project
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * setter of customer
	 * 
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * getter of tasks
	 * 
	 * @return the tasks of the project
	 */
	public ArrayList<Task> getTasks() {
		return tasks;
	}

	/**
	 * setter of tasks
	 * 
	 * @param tasks
	 *            the tasks to set
	 */
	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	/**
	 * compute a String representation
	 * 
	 * @return a String representation of the project
	 */
	public String toString() {
		return title + "\t" + description + "\t" + priority;
	}

	/**
	 * compute a String for saving into file
	 * 
	 * @return String representation for saving into file
	 */
	public String toFile() {
		String ret = "";
		if (title != null)
			ret += title;
		ret += ";";
		if (description != null)
			ret += description;
		ret += ";";
		if (notes != null)
			ret += notes;
		ret += ";";
		if (status != null)
			ret += status.getStatus();
		ret += ";";
		if (priority != null)
			ret += priority.toString();
		ret += ";";
		if (color != null) {
			ret += color.getRed() + ";";
			ret += color.getGreen() + ";";
			ret += color.getBlue();
		}
		ret += ";";
		if (deadline != null)
			ret += deadline.getTime();
		ret += ";";
		if (eventDate != null)
			ret += eventDate.getTime();
		ret += ";";
		return ret;
	}
}
