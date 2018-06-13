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
	private Tab MyMainTab;
	
	@FXML
	private GridPane MyGridPane;
	
	
	@FXML
	private ListView<String> MyListView;
	
	@FXML
	private Button MyButton, NewTab;

	private static int buttonclicked;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	// When user click on myButton
	// this method will be called.
	public void addFieldtoVBox(ActionEvent event) {
		ObservableList<String> items = MyListView.getItems();
		items.add(Integer.toString(++buttonclicked));
		MyListView.setItems(items);

	}

	public void addNewTab(ActionEvent event) {
		Tab next = new Tab();
		next.setText("Name");
		next.setClosable(true);
		MyTabPane.getTabs().add(next);
	}
	
}
