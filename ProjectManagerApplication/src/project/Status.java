package project;

import javafx.scene.paint.Color;

/**
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * @author Lydia Grillenberger, Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public enum Status {
	PREPRODUCTION ("Vorproduktion", new Color(1, 215./255, 0, 1)),
    PRODUCTION   ("Produktion", new Color(1, 0, 0, 1)),
    POSTPRODUCTION   ("Post-Produktion", new Color(1, 99./255, 71./255, 1)),
    REVISION    ("Revision", new Color(154./255, 50./255, 205./255, 1)),
    INCOME ("Geldeingang",   new Color(50./255, 205./255, 50./255, 1));
	
	private final String status;
    private final Color color; // in meters
    
    /**
     * Constructor of Status
     * @param status, String of the Status
     * @param color, Color of the Status
     * @author Lydia Grillenberger, Lukas Schiefermueller
     */
    Status(String status, Color color) {
        this.status = status;
        this.color = color;
    }
    
    /**
     * 
     * @return Status
     */
    public String getStatus () {
    	return status;
    }
    
    /**
     * 
     * @return Color of Status
     */
    public Color getColor () {
    	return color;
    }
}
