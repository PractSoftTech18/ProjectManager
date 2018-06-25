package project;

import javafx.scene.paint.Color;

/**
 * https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
 * @author Lydia Grillenberger, Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public enum Status {
	// Project status
	PREPRODUCTION ("Vorproduktion", new Color(1, 215./255, 0, 1), false),
    PRODUCTION   ("Produktion", new Color(1, 0, 0, 1), false),
    POSTPRODUCTION   ("Post-Produktion", new Color(1, 99./255, 71./255, 1), false),
    REVISION    ("Revision", new Color(154./255, 50./255, 205./255, 1), false),
    INCOME ("Geldeingang",   new Color(50./255, 205./255, 50./255, 1), false),
	
    // Task status
	OPEN ("Offen", new Color(1, 0, 0, 1), true),
	INPROGRESS ("In Arbeit", new Color(1, 215./255, 0, 1), true),
	CLOSED ("Abgeschlossen", new Color(50./255, 205./255, 50./255, 1), true);
	
	private final String status;
    private final Color color;
    private final boolean task; // Decides, whether it is a task status or a project status.
    
    private Status (String s, Color c, boolean b) {
    	status = s; 
    	color = c; 
    	task = b;
    }
    
    public static Status returnStatus (String status, boolean task) {
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
			default: return null;
	    	}
    	}
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
