package files;

import project.Project;

/**
 * @author Lydia Grillenberger
 * @version 1.00, June 26th 2018
 */
public interface FileHandlerInterface {
	/**
	 * This includes creating the files/data structure that are needed for storing a project such that it can
	 * be reloaded after closing the application.
	 * 
	 * @param project project to be added
	 */
	public void add(Project project);
	
	/**
	 * This method deletes the project internally and in the file structure.
	 * 
	 * @param project project to be deleted
	 */
	public void delete(Project project);
	
	/**
	 * Here existing files need to be changed.
	 *  (comment: The project name must not be changed! -> file structure)
	 * 
	 * @param project project to be changed with the changed values inside
	 * @param oldTitle old title of the project
	 */
	public void change(Project project, String oldTitle);
	
	/**
	 * There should be a possibility for getting all existing projects and tasks to initialize them after
	 * starting the application.
	 */
	public void read();
}
