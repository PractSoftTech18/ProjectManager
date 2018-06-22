package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * controller for CreateProject
 * 
 * @author Julia Hofer
 * @version 1.00, June 26th 2018
 */
public class CreateProjectController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button NewTab;


    @FXML
    void addNewTab(ActionEvent event) {
    }

    @FXML
    void initialize() {
        assert NewTab != null : "fx:id=\"NewTab\" was not injected: check your FXML file 'CreateProject.fxml'.";


    }

}
