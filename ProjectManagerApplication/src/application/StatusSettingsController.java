package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import project.Status;

/**
 * @author Lydia Grillenberger
 * @version 1.00, June 26th 2018
 */
public class StatusSettingsController {
	
	@FXML
	private TableView<Status> tblProjectStatus;
	
	@FXML
	private TableView<Status> tblTaskStatus;
	
	@FXML
    void initialize() {
        //loadTables();
		tblProjectStatus.setEditable(true);
		tblTaskStatus.setEditable(true);
		
    }
	
	
}
