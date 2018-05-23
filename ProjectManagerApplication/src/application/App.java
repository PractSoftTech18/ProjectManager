package application;

import java.net.URL;
/*
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
*/
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;

public class App implements Initializable {

	@FXML
	private TabPane MyTabPane;
	
	@FXML
	private Tab MyMainTab, SecondTab;
	
	@FXML
	private GridPane MyGridPane;
	
	
	@FXML
	private ListView<String> MyListView;
	
	@FXML
	private Button MyButton;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// TODO (don't really need to do anything here).

	}

	// When user click on myButton
	// this method will be called.
	public void addFieldtoVBox(ActionEvent event) {
		ObservableList<String> items =FXCollections.observableArrayList (
			    "Single", "Double", "Suite", "Family App","Single", "Double", "Suite", "Family App");
		MyListView.setItems(items);

	}

}
