import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;

import project.*;
import project.Priority;
import customer.*;

import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

import customer.Customer;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.GridLayout;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * @author Lydia Grillenberger, Julia Hofer and Lukas Schiefermueller
 * @version 1.0
 */
public class ProjectManager {

	private JFrame frame;
	private JTable table;

	private ArrayList<Project> projects;

	private Path active, archive;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() { try {
		 * ProjectManager window = new ProjectManager(); window.frame.setVisible(true);
		 * } catch (Exception e) { e.printStackTrace(); } } });
		 */
		ProjectManager pm = new ProjectManager();
		pm.frame.setVisible(true);
		int i = 1;
		Scanner sc = new Scanner(System.in);
		while (i != 0) {
			System.out.println("1\tNeues Projekt!");
			System.out.println("2\tProjekte anzeigen!");
			System.out.println("0\tBeenden!");
			i = sc.nextInt();
			switch (i) {
			case 1:
				try {
					pm.newProject();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
				pm.showProjects();
				break;
			}
		}
		sc.close();
	}

	/**
	 * Create the application.
	 */
	public ProjectManager() {
		/*
		 * int n = 10; projects = new ArrayList<>(n); ArrayList<Person> pers = new
		 * ArrayList<>(); pers.add(new Person("Name", "Nummer", "EMail", "Adresse",
		 * "Zeuge")); for (int i = 0; i < n; i++) projects.add(new Project("Titel" + i,
		 * "Beschreibung" + i, "Wos was i" + i, Priority.LOW, new Color(0, 155, 255),
		 * true, new Date(2), new Date(3), new Customer(pers, 1), null)); //
		 * initialize();
		 * 
		 */
		projects = new ArrayList<>();
		active = Paths.get(System.getProperty("user.dir").toString() + "//Projects//Active");
		archive = Paths.get(System.getProperty("user.dir").toString() + "//Projects//Archive");
		System.out.println(active);
		System.out.println(archive);
		initialize();
		// setUp();
	}

	/**
	 * 
	 */
	private void newProject() throws IOException {
		int countPersons;
		System.out.println("Wieviele Personen: "); // In GUI durch Add-Button ersetzen.
		Scanner sc = new Scanner(System.in);
		countPersons = sc.nextInt();
		// sc.close();
		ArrayList<Person> pers = new ArrayList<>(countPersons);
		for (int i = 0; i < countPersons; i++) {
			Person p = new Person();
			pers.add(p);
		}
		System.out.println("Ansprechpartner: "); // In GUI durch Add-Button ersetzen.

		int a = sc.nextInt();
		Project P = new Project();
		P.setCustomer(new Customer(pers, a < 0 ? 0 : a >= countPersons ? 0 : a));
		projects.add(P);
		new File(active + "//" + P.getTitle()).mkdirs();
		System.out
				.println(active.toString() + "//" + P.getTitle() + "//" + P.getCustomer().getContactPerson() + ".txt");
		File F = new File(active.toString() + "/" + P.getTitle() + "/" + P.getCustomer().getContactPerson() + ".txt");
		boolean ex = false;
		try {
			ex = F.createNewFile();
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(ex);

	}

	/**
	 * 
	 */
	private void showProjects() {
		File[] directories = new File(active.toString()).listFiles(File::isDirectory);
		for (int i = 0; i < directories.length; i++)
			System.out.println(directories[i].getName());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		tabbedPane.setBounds(0, 0, frame.getWidth(), frame.getHeight());

		JPanel panel = new JPanel();
		tabbedPane.addTab("New tab", null, panel, null);
		/*
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0 };
		gbl_panel.columnWeights = new double[] { 10.0, 10.0};//Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 10.0, 10.0};//Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		*/
		// JLabel lblOurLabel = new JLabel("l");
		// scrollPane.add(lblOurLabel);

		JScrollPane scrollPane = new JScrollPane();
		/*
		 * GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		 * gbc_scrollPane.fill = GridBagConstraints.BOTH; gbc_scrollPane.gridx = 0;
		 * gbc_scrollPane.gridy = 0; panel.add(scrollPane, gbc_scrollPane);
		 */
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new LineBorder(Color.RED));
		// scrollPane.getViewport().add(jButton1, null);

		ArrayList<JTextField> fields = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			JTextField p = new JTextField();
			p.setText("Test" + Integer.toString(i));
			fields.add(p);
scrollPane.add(p);
		}


		scrollPane.getViewport();
		
		
		// scrollPane.setBounds(10, 10, frame.WIDTH-10, frame.HEIGHT-10);
		scrollPane.setVisible(true);
		tabbedPane.add(scrollPane, BorderLayout.CENTER);
		
		// frame.setSize(400, 150);
		// frame.setVisible(true);
	}

}
