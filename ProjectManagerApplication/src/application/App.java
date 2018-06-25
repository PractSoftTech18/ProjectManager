package application;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
/*
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
*/
import java.util.ResourceBundle;

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
import javafx.scene.layout.GridPane;

/**
 * controller for MyScene
 * 
 * @author Lydia Grillenberger
 * @author Julia Hofer
 * @author Lukas Schiefermueller
 * @version 1.00, June 26th 2018
 */
public class App implements Initializable {
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
	private TableView<String[]> tblProjects;
	
	@FXML
	private TableColumn<String, String> tblColProjects;

	private static int buttonclicked;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//updateTblProjects;
		
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
	public void updateTblProjects(ActionEvent event) {
		ObservableList<String[]> tblItems = tblProjects.getItems();
		//tblColProjects = new TableColumn<>();
		//ObservableList<TableColumn<String, ?>> tblItems = tblColProjects.getColumns();
		//for(int i = 0; i < 5; i++) {//ourData.projects.size()
			//String[] arr = {Integer.toString(i),Integer.toString(i),Integer.toString(i),Integer.toString(i)};
			String[] arr = {"Hello", "out", "there", "programming"};
			tblItems.add(arr);
			tblItems.add(arr);
			tblItems.add(arr);
		//}
		tblProjects.setItems(tblItems);
	}
}
