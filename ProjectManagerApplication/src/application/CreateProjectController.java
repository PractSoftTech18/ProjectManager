package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import project.Priority;
import project.Status;

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
    
    @FXML
    void addNewTab(ActionEvent event) {
    }

    /**
     * initialize view of creating a project
     * 
     * @author Lydia Grillenberger
     * @author Julia Hofer
     */
    @FXML
    void initialize() {
        assert NewTab != null : "fx:id=\"NewTab\" was not injected: check your FXML file 'CreateProject.fxml'.";
        ObservableList<String> items = choiceBoxPriority.getItems();
		items.addAll(Priority.HIGH.toString(), Priority.NORMAL.toString(), Priority.LOW.toString());
		choiceBoxPriority.setItems(items);
		choiceBoxPriority.setValue(Priority.NORMAL.toString());
        ArrayList<Status> statusChoice = ourData.projectStatus;
        if(statusChoice != null) {
        	items = choiceBoxStatus.getItems();
        	for(Iterator<Status> it = statusChoice.iterator(); it.hasNext();) {
        		Status status = it.next();
        		items.add(status.getStatus());
        	}
        	choiceBoxStatus.setItems(items);
        }
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
     * @author ?
     * @param event select Button save
     */
    public void save(ActionEvent event) {
    	// read the given information
    	// FileHandler.add(project)
    	// close this tab and go to dashboard
    }
}
