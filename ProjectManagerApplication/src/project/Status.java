package project;

import javafx.scene.paint.Color;

/**
 * @author Lydia Grillenberger
 * @version 1.00, June 26th 2018
 */
public class Status {
	/**
	 * how the status is called
	 */
	private String status;
	
	/**
	 * the corresponding color of the status
	 */
	private Color color;
	
	/**
	 * constructor
	 * 
	 * @param status how the status is called
	 * @param color the corresponding color of the status
	 */
	public Status(String status, Color color) {
		this.status = status;
		this.color = color;
	}
	
	/**
	 * default constructor
	 */
	public Status() {
		// everything has to be set via setters
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
}
