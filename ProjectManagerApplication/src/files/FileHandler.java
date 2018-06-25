package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import application.Data;
import project.Project;

/**
 * @author Lydia Grillenberger
 * @version 1.00, June 26th 2018
 */
public class FileHandler implements FileHandlerInterface{
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();
	
	// Sets the paths to the active and archive working directory.
	private final Path active = Paths.get(System.getProperty("user.dir").toString() + "//Projects//Active");
	
	/**
	 * This includes creating the files/data structure that are needed for storing a project such that it can
	 * be reloaded after closing the application.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param project project to be added
	 * @return true/false whether it worked
	 */
	public boolean add(Project project) {
		System.out.println(active);
		new File(active + "//" + project.getTitle()).mkdirs();
		File F = new File(active.toString() + "/" + project.getTitle() + "/" + project.getCustomer().getContactPerson() + ".txt");
		writeToFile(F, "Test 1 ,2 ,3 4!");
		return false;
	}
	
	/**
	 * Here existing files need to be changed.
	 * 
	 * @author ?
	 * @param project project to be changed with the changed values inside
	 * @return true/false whether it worked
	 */
	public boolean change(Project project) {
		// needs to be implemented
		// Care about renaming the project: In the file structure there is the old name.
		// (maybe forbid renaming)
		return false;
	}
	
	/**
	 * There should be a possibility for getting all existing projects to initialize them after starting the
	 * application.
	 * 
	 * @author ?
	 * @return true/false whether it worked
	 */
	public boolean read() {
		// needs to be implemented
		// call readFile(file) for each file
		// store the information in ourData
		return false;
	}
	
	/**
	 * This method is used internally for reading a String from a file.
	 * 
	 * @author Lydia Grillenberger
	 * @param file file to be read from
	 * @return the content of the file as String
	 */
	private String readFile(File file) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		}
		catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String s = "";
		try {
			s = br.readLine();
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	/**
	 * This method is used internally for saving a String to a file.
	 * 
	 * @author Lydia Grillenberger
	 * @param file file to which to save the String
	 * @param string String to save
	 */
	private void writeToFile(File file, String string) {
		file.setWritable(true);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write(string);
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		file.setWritable(false); // writeToFile is the only one to write to this file
	}
}
