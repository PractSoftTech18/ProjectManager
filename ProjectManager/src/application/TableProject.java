package application;

// Java imports
import java.text.SimpleDateFormat;
import java.util.Date;

// ProjectManager imports
import project.Status;

/**
 * used for filling tblProjects
 * 
 * @author Lukas Schiefermueller
 */
public class TableProject {
	/**
	 * the title of the project
	 */
	private String title;

	/**
	 * the status of the project
	 */
	private String status;

	/**
	 * the Event date of the project
	 */
	private String event;

	/**
	 * the deadline of the project
	 */
	private String deadline;

	/**
	 * constructor
	 * 
	 * @param title
	 *            the title to set
	 * @param status
	 *            the status to set
	 * @param dateEvent
	 *            the date to set
	 */
	public TableProject(String title, String status, String dateEvent, String dateDeadline) {
		this.title = title;
		this.status = status;
		this.event = dateEvent;
		this.deadline = dateDeadline;
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
	public TableProject(String title, Status status, Date dateEvent, Date dateDeadline) {
		this.title = title;
		this.status = status.getStatus();
		String dateString = "";
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		if (dateEvent != null) {
			dateString = dateFormatter.format(dateEvent);
		} else {
			dateString = dateFormatter.format(new Date());
		}
		this.event = dateString;
		if (dateDeadline != null) {
			dateString = dateFormatter.format(dateDeadline);
		} else {
			dateString = dateFormatter.format(new Date());
		}
		this.deadline = dateString;
	}

	/**
	 * getter for title
	 * 
	 * @return the title of the project
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * getter for status
	 * 
	 * @return the status of the project
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * getter for Event date
	 * 
	 * @return the event date of the project
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * getter for deadline date
	 * 
	 * @return the deadline date of the project
	 */
	public String getDeadline() {
		return deadline;
	}
}
