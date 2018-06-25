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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
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
    private Button NewTab;

    @FXML
    private ChoiceBox<String> choiceBoxPriority, choiceBoxStatus, choiceBoxContactPerson;
    
    @FXML
    private TableColumn<String, String> name;
    
    // Lukas Schiefermüller, 24.06.2018
    @FXML
    private TextField TextField_ProjectName;
    
    // Lukas Schiefermüller, 24.06.2018
    @FXML
    private ColorPicker Color_Project;
    
    @FXML
    private DatePicker Date_Deadline, Date_Event;
    
    @FXML
    void addNewTab(ActionEvent event) {
    }

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
        ObservableList<String> items = choiceBoxPriority.getItems();
		items.addAll(Priority.HIGH.toString(), Priority.NORMAL.toString(), Priority.LOW.toString());
		choiceBoxPriority.setItems(items);
		choiceBoxPriority.setValue(Priority.NORMAL.toString());
		
		items = choiceBoxStatus.getItems();
        for (Status s : Status.values()) {
        	items.add(s.getStatus());
        }
    	choiceBoxStatus.setItems(items);
    	choiceBoxStatus.setValue(Status.PREPRODUCTION.getStatus());
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
    	if(name != null) {
	    	ObservableList<TableColumn<String,?>> personChoice = name.getColumns();
	        if(personChoice != null) {
	        	ObservableList<String> items = choiceBoxContactPerson.getItems();
	        	for(Iterator<TableColumn<String,?>> it = personChoice.iterator(); it.hasNext();) {
	        		items.add(it.next().toString());
	        	}
	        	choiceBoxContactPerson.setItems(items);
	        }
    	}
    }
    
    /**
     * save project
     * 
     * @author Lukas Schiefermueller
     * @param event select button save
     */
    public void save(ActionEvent event) {
    	// read the given information
    	// FileHandler.add(project)
    	// close this tab and go to dashboard
    	Project newProject = new Project();
    	newProject.setTitle(TextField_ProjectName.getText());
    	
    	newProject.setColor(Color_Project.getValue());
    	String dummy = choiceBoxPriority.getSelectionModel().getSelectedItem();
    	Priority p = dummy == "LOW" ? Priority.LOW : (dummy == "NORMAL" ? Priority.NORMAL : Priority.HIGH);
    	newProject.setPriority(p);
    	// https://stackoverflow.com/questions/20446026/get-value-from-date-picker
    	LocalDate localDate = Date_Deadline.getValue();
    	Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    	newProject.setDeadline(Date.from(instant));
    	
    	localDate = Date_Deadline.getValue();
    	instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
    	newProject.setEventDate(Date.from(instant));
    	
    	ArrayList<Person> persons = new ArrayList<>();
    	persons.add(new Person());
    	persons.add(new Person());
    	Customer customer = new Customer(persons, 1);
    	
    	newProject.setCustomer(customer);
    	
    	FileHandlerInterface fi = new FileHandler();
    	fi.add(newProject);
    }
}
