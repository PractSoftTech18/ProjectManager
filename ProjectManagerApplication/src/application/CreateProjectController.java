package application;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import customer.Customer;
import customer.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import project.Priority;
import project.Project;
import project.Status;
import files.FileHandler;
import files.FileHandlerInterface;
/**
 * controller for CreateProject
 * 
 * @author Julia Hofer
 * @version 1.00, June 26th 2018
 */
public class CreateProjectController {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button NewTab, btnSave, btnAddPerson, btnAddTask;

    @FXML
    private ChoiceBox<String> cBoxPriority, cBoxStatus, cBoxContactPerson;
    
    @FXML
    private TableColumn<Person, String> tblColName, tblColRelation, tblColPhone, tblColMail, tblColAd;
   
    @FXML
    private TableColumn<String, String> tblColTask, tblColPriority, tblColDate, tblColNote, tblColStatus;
    
    @FXML
    private TableView<Person> tblPersons;
    
    @FXML
    private TableView<?> tblTasks;
    
    @FXML
    private TextField tfProjectName;
    
    @FXML
    private TextArea tfDescription, tfNotes;
    
    @FXML
    private TextField tfTask, tfTaskDate, tfTaskStatus, tfTaskNote, tfTaskPriority;
    
    @FXML
    private TextField tfPersonName, tfPersonAd, tfPersonRelation, tfPersonPhone, tfPersonMail;
    
    @FXML
    private ColorPicker colorPProject;
    
    @FXML
    private DatePicker datePDeadline, datePEvent;
    
    @FXML
    void addNewTab(ActionEvent event) {
    }

    private ObservableList<Person> person;
    
    /**
     * initialize view of creating a project
     * 
     * @author Lydia Grillenberger
     * @author Julia Hofer
     * @author Lukas Schiefermueller
     */
    @FXML
    void initialize() {
        assert NewTab != null : "fx:id=\"NewTab\" was not injected: check your FXML file 'CreateProject.fxml'.";
        ObservableList<String> items = cBoxPriority.getItems();
		items.addAll(Priority.HIGH.toString(), Priority.NORMAL.toString(), Priority.LOW.toString());
		cBoxPriority.setItems(items);
		cBoxPriority.setValue(Priority.NORMAL.toString());
		
		items = cBoxStatus.getItems();
        for (Status s : Status.values()) {
        	items.add(s.getStatus());
        }
    	cBoxStatus.setItems(items);
    	cBoxStatus.setValue(Status.PREPRODUCTION.getStatus());
    	
    	tblColName.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
    	tblColPhone.setCellValueFactory(new PropertyValueFactory<Person,String>("phoneNumber"));
    	tblColMail.setCellValueFactory(new PropertyValueFactory<Person,String>("email"));
    	tblColAd.setCellValueFactory(new PropertyValueFactory<Person,String>("address"));
    	tblColRelation.setCellValueFactory(new PropertyValueFactory<Person,String>("relation"));
    	person = FXCollections.observableArrayList();
    }
    
    /**
     * initialize choiceBox menu of choiceBoxContactPerson
     * 
     * @author Lydia Grillenberger
     * @param event select choiceBox
     */
    public void selectContactPerson(ActionEvent event) {
    	// should be called when choiceBoxContactPerson.onShowingProperty() but I cannot find
    	// onShowingProperty in Scene Builder
    	if(tblColName != null) {
	    	ObservableList<TableColumn<Person,?>> personChoice = tblColName.getColumns();
	        if(personChoice != null) {
	        	ObservableList<String> items = cBoxContactPerson.getItems();
	        	for(Iterator<TableColumn<Person,?>> it = personChoice.iterator(); it.hasNext();) {
	        		items.add(it.next().toString());
	        	}
	        	cBoxContactPerson.setItems(items);
	        }
    	}
    }
    
    /**
     * save project
     * 
     * @author Lukas Schiefermueller
     * @param event select button save
     */
    public void btnSave(ActionEvent event) {
    	// read the given information
    	// FileHandler.add(project)
    	// close this tab and go to dashboard
    	Project newProject = new Project();
    	newProject.setTitle(tfProjectName.getText());
    	
    	newProject.setColor(colorPProject.getValue());
    	String dummy = cBoxPriority.getSelectionModel().getSelectedItem();
    	Priority p = dummy == "LOW" ? Priority.LOW : (dummy == "NORMAL" ? Priority.NORMAL : Priority.HIGH);
    	newProject.setPriority(p);
    	// https://stackoverflow.com/questions/20446026/get-value-from-date-picker
    	LocalDate localDate = datePDeadline.getValue();
    	Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    	newProject.setDeadline(Date.from(instant));
    	
    	localDate = datePDeadline.getValue();
    	instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    	newProject.setEventDate(Date.from(instant));
    	
    	ArrayList<Person> persons = new ArrayList<>();
    	persons.add(new Person());
    	persons.add(new Person());
    	Customer customer = new Customer(persons, 1);
    	
    	newProject.setCustomer(customer);
    	
    	FileHandlerInterface fi = new FileHandler();
    	fi.add(newProject);
    	
    	/*
    	 * Add persons from person list
    	 */
    	
    	
    }
    
    /**
     * add person to project
     * 
     * @author Lukas Schiefermueller
     * @param event click button "Hinzuf�gen"
     */
   public void btnAddPerson(ActionEvent event) {
    	// tblPersons.getColumns().addAll(tblColName,tblColPhone, tblColMail, tblColAd, tblColRelation);
	   
    	person.add(new Person(tfPersonName.getText(), tfPersonPhone.getText(), tfPersonMail.getText(), tfPersonAd.getText(), tfPersonRelation.getText()));
    	tblPersons.setItems(person);
    	//tblPersons.addAll(5, tfPersonName.getText(),tfPersonName.getText(),tfPersonName.getText(),tfPersonName.getText(),tfPersonName.getText());
    
    	// add this person to table view
    }
}
