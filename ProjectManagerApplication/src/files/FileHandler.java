package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	// Sets the paths to the active and archive working directory.
	private final Path active = Paths.get(System.getProperty("user.home").toString() + "//ProjectManager//Projects");

	/**
	 * This includes creating the files/data structure that are needed for storing a
	 * project such that it can be reloaded after closing the application.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param project
	 *            project to be added
	 */
	public void add(Project project) {
		System.out.println(active);
		new File(active + "//" + project.getTitle()).mkdirs();
		String addToFile = "";
		addToFile += project.getTitle() + ";" + project.getDescription() + ";" + project.getNotes() + ";"
				+ project.getStatus().getStatus() + ";" + project.getPriority().name() + ";"
				+ project.getColor().getRed() + ";" + project.getColor().getGreen() + project.getColor().getBlue() + ";"
				+ ";" + project.getDeadline().getTime() + ";" + project.getEventDate().getTime();
		writeToFile(new File(active.toString() + "/" + project.getTitle() + "/Project.txt"), addToFile);
		addToFile = "";
		for (Iterator<Person> p = project.getCustomer().getPersons().iterator(); p.hasNext();) {
			addToFile += p.next().toFile() + "\n";
		}
		addToFile += project.getCustomer().getContactPersonIndex();
		writeToFile(new File(active.toString() + "/" + project.getTitle() + "/Customer.txt"), addToFile);

		addToFile = "";
		for (Iterator<Task> t = project.getTasks().iterator(); t.hasNext();) {
			addToFile += t.next().toFile() + "\n";
		}
		addToFile.substring(0, addToFile.length() - 1);
		writeToFile(new File(active.toString() + "/" + project.getTitle() + "/Tasks.txt"), addToFile);
		ourData.projects.add(project);
	}

	/**
	 * Here existing files need to be changed.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param project
	 *            project to be changed with the changed values inside
	 * @param oldTitle
	 *            old title of the project
	 */
	public void change(Project project, String oldTitle) {
		for (Path dir : active) {
			if (dir.toString().equals(active.toString() + "//" + oldTitle)) {
				try {
					Files.delete(dir);
				} catch (Exception e) {
					System.out.println(e);
				}
				break;
			}
		}
		add(project);

		int i = 0;
		while (i < ourData.projects.size() && !ourData.projects.get(i).getTitle().equals(oldTitle)) {
			i++;
		}
		if (i < ourData.projects.size()) {
			ourData.projects.remove(i);
		}
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
		for (Path dir : active) {
			Project pro = new Project();
			File title, customer, tasks;

			// Information of the Project
			title = new File(dir + "/Project.txt");
			ArrayList<String> s = readFile(title);
			String[] val = s.get(0).split(";");
			pro.setTitle(val[0]);
			pro.setDescription(val[1]);
			pro.setNotes(val[2]);
			pro.setStatus(Status.returnStatus(val[4], false));
			pro.setPriority(Priority.returnPriority(val[5]));
			pro.setColor(
					new Color(Double.parseDouble(val[6]), Double.parseDouble(val[7]), Double.parseDouble(val[8]), 1.));
			pro.setDeadline(new Date(Long.parseLong(val[9])));
			pro.setEventDate(new Date(Long.parseLong(val[10])));

			// Customers of the Projects
			customer = new File(dir + "/Customer.txt");
			s = readFile(customer);
			Customer cust = new Customer();
			for (Iterator<String> it = s.iterator(); it.hasNext();) {
				val = it.next().split(";");
				if (it.hasNext()) {
					cust.add(new Person(val[0], val[1], val[2], val[3], val[4]));
				} else {
					cust.setContactPerson(Integer.parseInt(val[0]));
				}
			}
			pro.setCustomer(cust);

			// Tasks of the Projects
			tasks = new File(dir + "/Tasks.txt");
			s = readFile(tasks);
			ArrayList<Task> task = new ArrayList<>();
			for (Iterator<String> it = s.iterator(); it.hasNext();) {
				val = it.next().split(";");
				task.add(new Task(val[0], val[1], Status.returnStatus(val[2], true), Priority.returnPriority(val[3]),
						new Date(Long.parseLong(val[4]))));
			}
			pro.setTasks(task);
			ourData.projects.add(pro);
		}
	}

	/**
	 * This method is used internally for reading Strings from a file.
	 * 
	 * @author Lydia Grillenberger
	 * @author Lukas Schiefermueller
	 * @param file
	 *            file to be read from
	 * @return the content of the file as a String ArrayList
	 */
	private ArrayList<String> readFile(File file) {
		FileReader fr = null;
		try {
			fr = new FileReader(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		ArrayList<String> lines = new ArrayList<>();
		String s;
		try {
			while ((s = br.readLine()) != null) {
				lines.add(s);
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return lines;
	}

	/**
	 * This method is used internally for saving a String to a file.
	 * 
	 * @author Lydia Grillenberger
	 * @param file
	 *            file to which to save the String
	 * @param string
	 *            String to save
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
