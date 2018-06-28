package project;

// JavaFX imports
import javafx.scene.paint.Color;

/**
 * enumeration of status types
 * 
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * 
 * @author Lydia Grillenberger
 * @author Lukas Schiefermueller
 * @version 1.00, June 27th 2018
 */
public enum Status {
	// Project status
	PREPRODUCTION("Vorproduktion", new Color(1, 215. / 255, 0, 1), false), PRODUCTION("Produktion",
			new Color(1, 0, 0, 1), false), POSTPRODUCTION("Post-Produktion", new Color(1, 99. / 255, 71. / 255, 1),
					false), REVISION("Revision", new Color(154. / 255, 50. / 255, 205. / 255, 1),
							false), INCOME("Geldeingang", new Color(50. / 255, 205. / 255, 50. / 255, 1), false),

	// Task status
	OPEN("Offen", new Color(1, 0, 0, 1), true), INPROGRESS("In Arbeit", new Color(1, 215. / 255, 0, 1),
			true), CLOSED("Abgeschlossen", new Color(50. / 255, 205. / 255, 50. / 255, 1), true);

	/**
	 * name of status
	 */
	private final String status;

	/**
	 * color of status
	 */
	private final Color color;

	/**
	 * true if status belongs to task, false otherwise
	 */
	private final boolean task;

	/**
	 * constructor
	 * 
	 * @param status
	 *            the name of the status
	 * @param color
	 *            the color of the status
	 * @param task
	 *            true if status belongs to task, false otherwise
	 */
	private Status(String status, Color color, boolean task) {
		this.status = status;
		this.color = color;
		this.task = task;
	}

	/**
	 * return the status of the String representation
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param status
	 *            the name of the status
	 * @param task
	 *            true if status belongs to task, false otherwise
	 * @return the status regarding to the input
	 */
	public static Status returnStatus(String status, boolean task) {
		if (task) {
			switch (status) {
			case "Offen":
				return OPEN;
			case "In Arbeit":
				return INPROGRESS;
			case "Abgeschlossen":
				return CLOSED;
			default:
				return null;
			}
		} else {
			switch (status) {
			case "Vorproduktion":
				return PREPRODUCTION;
			case "Produktion":
				return PRODUCTION;
			case "Post-Produktion":
				return POSTPRODUCTION;
			case "Revision":
				return REVISION;
			case "Geldeingang":
				return INCOME;
			default:
				return null;
			}
		}
	}

	/**
	 * getter for status
	 * 
	 * @return the name of the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * getter for color
	 * 
	 * @return the color of the status
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * getter for task
	 * 
	 * @return true if status belongs to task, false otherwise
	 */
	public boolean getTask() {
		return task;
	}

	/**
	 * increase the status
	 * 
	 * @author Lydia Grillenberger
	 * @return the next status
	 */
	public Status increaseStatus() {
		if (task) {
			switch (status) {
			case "Offen":
				return INPROGRESS;
			case "In Arbeit":
				return CLOSED;
			case "Abgeschlossen":
				return OPEN;
			default:
				return null;
			}
		} else {
			switch (status) {
			case "Vorproduktion":
				return PRODUCTION;
			case "Produktion":
				return POSTPRODUCTION;
			case "Post-Produktion":
				return REVISION;
			case "Revision":
				return INCOME;
			case "Geldeingang":
				return PREPRODUCTION;
			default:
				return null;
			}
		}
	}
}
