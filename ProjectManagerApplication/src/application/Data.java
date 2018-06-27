package application;

import java.util.ArrayList;

import project.Project;

/**
 * This is a singleton for collecting the data which is needed for the GUI.
 * 
 * @author Lydia Grillenberger
 * @version 1.00, June 27th 2018
 */
public class Data {
	/**
	 * all projects
	 */
	public ArrayList<Project> projects;

	/**
	 * store the position of the selected project
	 */
	public int selected;
	
	/**
	 * store the position of the selected project in getProject
	 */
	public int selectedInternally;

	/**
	 * only instance of Data
	 */
	private static Data ourData = new Data();

	/**
	 * forbid creating of new Data
	 */
	private Data() {
		projects = new ArrayList<>();
	}

	/**
	 * getter for ourData
	 * 
	 * @return the only instance of Data
	 */
	public static Data getData() {
		return ourData;
	}

	/**
	 * getter for specific project
	 * 
	 * @param title
	 *            the title of the project
	 * @return the project with the given title
	 */
	public static Project getProject(String title) {
		for (int i = 0; i < ourData.projects.size(); i++) {
			if (ourData.projects.get(i).getTitle().equals(title)) {
				ourData.selectedInternally = i;
				return ourData.projects.get(i);
			}
		}
		return null;
	}
}
