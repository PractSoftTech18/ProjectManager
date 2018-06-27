package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import application.Data;
import customer.Customer;
import customer.Person;
import javafx.scene.paint.Color;
import project.Priority;
import project.Project;
import project.Task;
import project.Status;

/**
 * @author Lydia Grillenberger
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class FileHandler implements FileHandlerInterface {

	/**
	 * only instance of FileHandler
	 */
	private static FileHandler ourFileHandler = new FileHandler();

	/**
	 * forbid creating of new FileHandler
	 */
	private FileHandler() {
	}

	/**
	 * @return the only instance of FileHandler
	 */
	public static FileHandler getFileHandler() {
		return ourFileHandler;
	}

	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	/**
	 * path to active working directory
	 */
	private final Path active = Paths.get(System.getProperty("user.home").toString() + "//ProjectManager//Projects");

	/**
	 * This includes creating the files/data structure that are needed for storing a
	 * project such that it can be reloaded after closing the application.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param project project to be added
	 */
	public void add(Project project) {
		new File(active + "//" + project.getTitle()).mkdirs();

		// Project.txt
		String addToFile = "";
		addToFile += project.toFile();
		writeToFile(new File(active.toString() + "/" + project.getTitle() + "/Project.txt"), addToFile);

		// Customer.txt
		addToFile = "";
		if (project.getCustomer() != null && project.getCustomer().getPersons() != null) {
			for (Iterator<Person> p = project.getCustomer().getPersons().iterator(); p.hasNext();) {
				addToFile += p.next().toFile() + "\n";
			}
			addToFile += project.getCustomer().getContactPersonIndex();
		}
		writeToFile(new File(active.toString() + "/" + project.getTitle() + "/Customer.txt"), addToFile);

		// Tasks.txt
		addToFile = ";;;;;";
		if (project.getTasks() != null) {
			addToFile = "";
			for (Iterator<Task> t = project.getTasks().iterator(); t.hasNext();) {
				addToFile += t.next().toFile() + "\n";
			}
			if (addToFile.length() > 0)
				addToFile.substring(0, addToFile.length() - 1);
		}
		writeToFile(new File(active.toString() + "/" + project.getTitle() + "/Tasks.txt"), addToFile);
		
		boolean found = false;
		for (int i = 0; i < ourData.projects.size() && !found; i++) {
			if (ourData.projects.get(i).getTitle().equals(project.getTitle()))
				found = true;
		}
		if(!found) {
			ourData.projects.add(project);
		}
		
	}

	/**
	 * This method deletes the project internally and in the file structure.
	 * 
	 * @author Lydia Grillenberger
	 * @param project project to be deleted
	 */
	public void delete(Project project) {
		delete(project, project.getTitle());
	}

	/**
	 * This method deletes the project internally and in the file structure.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param project project to be deleted
	 * @param title title of the project
	 */
	private void delete(Project project, String title) {
		for (Path dir : active) {
			if (dir.toString().equals(active.toString() + "//" + title)) {
				try {
					Files.delete(dir);
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			}
		}

		for (int i = 0; i < ourData.projects.size(); i++) {
			if (ourData.projects.get(i).getTitle().equals(project.getTitle())) {
				ourData.projects.remove(i);
				break;
			}
		}
	}

	/**
	 * Here existing files need to be changed.
	 * 
	 * @author Lydia Grillenberger
	 * @param project project to be changed with the changed values inside
	 * @param oldTitle old title of the project
	 */
	public void change(Project project, String oldTitle) {
		delete(project, oldTitle);
		add(project);
		ourData.projects.add(project);
	}

	/**
	 * There should be a possibility for getting all existing projects to initialize
	 * them after starting the application.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 */
	public void read() {
		// https://stackoverflow.com/questions/5125242/java-list-only-subdirectories-from-a-directory-not-files
		File file = new File(active.toString());
		String[] directories = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File current, String name) {
				return new File(current, name).isDirectory();
			}
		});
		if(directories != null && directories.length > 0) {
			for (String dir : directories) {
				Project pro = new Project();
				File title, customer, tasks;
	
				// Information of the Project
				title = new File(active.toString() + "/" + dir + "/Project.txt");
				ArrayList<String> s = readFile(title);
				String[] val = s.get(0).split(";", -1); 
				pro.setTitle(val[0]);
				pro.setDescription(val[1]);
				pro.setNotes(val[2]);
				pro.setStatus(Status.returnStatus(val[3], false));
				pro.setPriority(Priority.returnPriority(val[4]));
				if (val[5].equals("") || val[6].equals("") || val[7].equals(""))
					pro.setColor(null);
				else
					pro.setColor(new Color(Double.parseDouble(val[5]), Double.parseDouble(val[6]),
							Double.parseDouble(val[7]), 1));
				if (val[8].equals(""))
					pro.setDeadline(null);
				else
					pro.setDeadline(new Date(Long.parseLong(val[8])));
				if (val[9].equals(""))
					pro.setEventDate(null);
				else
					pro.setEventDate(new Date(Long.parseLong(val[9])));
	
				// Customers of the Projects
				customer = new File(active.toString() + "/" + dir + "/Customer.txt");
				s = readFile(customer);
				Customer cust = new Customer();
				for (Iterator<String> it = s.iterator(); it.hasNext();) {
					val = it.next().split(";", -1);
					if (it.hasNext()) {
						cust.add(new Person(val[0], val[1], val[2], val[3], val[4]));
					} else {
						cust.setContactPerson(Integer.parseInt(val[0])); // contact person
					}
				}
				pro.setCustomer(cust);
	
				// Tasks of the Projects
				tasks = new File(active.toString() + "/" + dir + "/Tasks.txt");
				s = readFile(tasks);
				ArrayList<Task> task = new ArrayList<>();
				Date date;
				for (Iterator<String> it = s.iterator(); it.hasNext();) {
					val = it.next().split(";", -1);
					if (val[4].equals(""))
						date = null;
					else
						date = new Date(Long.parseLong(val[4]));
					task.add(new Task(val[0], val[1], Status.returnStatus(val[2], true), Priority.returnPriority(val[3]),
							date));
				}
				pro.setTasks(task);
				ourData.projects.add(pro);
			}
		}
	}

	/**
	 * This method is used internally for reading Strings from a file.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param file file to be read from
	 * @return the content of the file as a String ArrayList
	 */
	private ArrayList<String> readFile(File file) {
		FileReader fr = null;
		ArrayList<String> lines = new ArrayList<>();
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s;
			try {
				while ((s = br.readLine()) != null) {
					lines.add(s);
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		return lines;
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		file.setWritable(false); // writeToFile is the only one to write to this file
	}
}
