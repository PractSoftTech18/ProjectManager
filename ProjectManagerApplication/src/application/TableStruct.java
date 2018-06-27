package application;

/**
 * used for filling tblProjects
 * 
 * @author Lukas Schiefermueller
 */
public class TableStruct {
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
	public TableStruct(String title, String status, String dateEvent, String dateDeadline) {
		this.title = title;
		this.status = status;
		this.event = dateEvent;
		this.deadline = dateDeadline;
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
