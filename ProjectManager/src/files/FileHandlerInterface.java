package files;

// ProjectManager imports
import project.Project;

/**
 * @author Lydia Grillenberger
 * @version 1.00, June 28th 2018
 */
public interface FileHandlerInterface {
	/**
	 * This method adds a project internally and in the file structure. This
	 * includes creating the files/data structure that are needed for storing a
	 * project such that it can be reloaded after closing the application.
	 * 
	 * @param project
	 *            the project to be added
	 */
	public void add(Project project);

	/**
	 * This method deletes the project internally and in the file structure.
	 * 
	 * @param project
	 *            the project to be deleted
	 */
	public void delete(Project project);

	/**
	 * This method makes changes in the given project.
	 * 
	 * @param project
	 *            the project to be changed with the changed values inside
	 * @param oldTitle
	 *            the old title of the project
	 */
	public void change(Project project, String oldTitle);

	/**
	 * This method gets all existing projects for initializing them after starting
	 * the application.
	 */
	public void read();
}
