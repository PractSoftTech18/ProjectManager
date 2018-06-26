package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
/*
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
*/
import java.util.ResourceBundle;

import customer.Person;
import javafx.collections.FXCollections;

//import javax.security.auth.login.Configuration;

//import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import project.Project;
import project.Status;

/**
 * controller for MyScene
 * 
 * @author Lydia Grillenberger
 * @author Julia Hofer
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class App /*implements Initializable*/ {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();
	
	@FXML
	private TabPane MyTabPane;
	
	@FXML
	private Tab MyMainTab, createProject;
	
	@FXML
	private GridPane MyGridPane;
	
	@FXML
	private ListView<String> MyListView;
	
	@FXML
	private Button MyButton, btnCreateProject, btnStatusSettings, btnTasks;
	
	@FXML
	private TableView<TableStruct> tblProjects;
	
	@FXML
	private TableColumn<TableStruct, String> tblColProjects;

	@FXML
	private TableColumn<TableStruct, String> tblColStatus;
	
	@FXML
	private TableColumn<TableStruct, String> tblColDate;
	
	private static int buttonclicked;
	
	private ObservableList<TableStruct> projects;
	
	private ArrayList<TableStruct> list;
	
	public class TableStruct {
		private String title, date, status;
		
		public TableStruct (String s, String st, String d) {
			title = s; 
			status = st;
			date = d;
		}
		
		public String getTitle () { return title; }
		public String getDate () { return date; }
		public String getStatus () { return status; }
	}
	
	@FXML
	public void initialize() {
		list = new ArrayList<>();
		for (int i  = 0; i < 5; i++) {
			list.add(new TableStruct("Titel " + i, "Status " + i, "Datum " + i));
		}
		System.out.println(list.size());
		tblColProjects.setCellValueFactory(new PropertyValueFactory<TableStruct, String>("title"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TableStruct, String>("date"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TableStruct, String>("status"));
    	
		projects = FXCollections.observableArrayList();
    	updateTblProjects();
    	
		
	}

	// When user click on myButton
	// this method will be called.
	public void addFieldtoVBox(ActionEvent event) {
		ObservableList<String> items = MyListView.getItems();
		items.add(Integer.toString(++buttonclicked));
		MyListView.setItems(items);

	}
	
	
	/**
	 * change to new project tab
	 * 
	 * @author Julia Hofer
	 * @param event
	 * @throws IOException
	 */
	public void createNewProject(ActionEvent event) throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/CreateProject.fxml"));
		Tab tabNewProject = new Tab("Neues Projekt");
		tabNewProject.setContent(loader.load());
		MyTabPane.getTabs().add(tabNewProject);
		MyTabPane.getSelectionModel().select(tabNewProject);
	}
	
	/**
	 * change to status settings tab 
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 * @throws IOException
	 */
	public void changeStatusSettings(ActionEvent event) throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/StatusSettings.fxml"));
		Tab tabStatusSettings = new Tab("Statuseinstellungen");
		tabStatusSettings.setContent(loader.load());
		MyTabPane.getTabs().add(tabStatusSettings);
		MyTabPane.getSelectionModel().select(tabStatusSettings);
	}
	
	/**
	 * change to tasks tab
	 * 
	 * @author Lydia Grillenberger
	 * @param event
	 * @throws IOException
	 */
	public void viewTasks(ActionEvent event) throws IOException {		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Tasks.fxml"));
		Tab tabStatusSettings = new Tab("Tasks");
		tabStatusSettings.setContent(loader.load());
		MyTabPane.getTabs().add(tabStatusSettings);
		MyTabPane.getSelectionModel().select(tabStatusSettings);
	}
	
	/**
	 * refresh the table of projects
	 * 
	 * @author Lydia Grillenberger
	 * @version 1.00, June 26th 2018
	 * @param event
	 */
	public void updateTblProjects() {
		/*
		for (Iterator<Project> pro = ourData.projects.iterator(); pro.hasNext();) {
			Project p = pro.next();
			projects.add(new TableStruct(p.getTitle(), p.getStatus().toString(), p.getEventDate().toString()));
		}
		*/
		for (int i = 0; i < list.size(); i++) {
			projects.add(list.get(i));
			
		}
		tblProjects.setItems(projects);
	}

	/*
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	*/
}
