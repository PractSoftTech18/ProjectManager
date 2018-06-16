package application;

import java.util.ArrayList;

import project.Project;
import project.Status;

/**
 * This is a singleton for collecting the data which is needed for the GUI.
 * 
 * @author Lydia Grillenberger
 * @version 1.00, June 26th 2018
 */
public class Data {
	public ArrayList<Project> projects;
	public ArrayList<Status> projectStatus;
	public ArrayList<Status> taskStatus;
	
	private static Data ourData = new Data();
	
	private Data(){}
	
	/**
	 * @return the only instance of Data
	 */
	public static Data getData(){
		return ourData;
		}
}
