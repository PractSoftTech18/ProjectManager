package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import project.Project;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Optional;

import application.TableTask;
import customer.Person;
import files.FileHandler;
import project.Task;

/**
 * 
 * @author Julia Hofer
 * @version 1.00
 */
public class ProjectViewController {
	/**
	 * available data for this run of the application
	 */
	private Data ourData = Data.getData();

	/**
	 * FileHandler
	 */
	
	private FileHandler ourFileHandler = FileHandler.getFileHandler();
	
	@FXML
	private AnchorPane apProjectView;
	
    @FXML
    private TableView<Person> tblPersons;

    @FXML
    private Button btnAddPerson, btnAddTask;

    @FXML
    private TableColumn<Person, String> tblColPhone,tblColMail,tblColName,tblColAd,tblColRelation;

    @FXML
    private Text textProjectName, textDeadline, textEvent, textPriority, textStatus, textContactPerson, textDescription, textNotes;

    @FXML
    private TableColumn<TableTask, String> tblColStatus,tblColTask,tblColPriority,tblColDate,tblColRemark;

    @FXML
    private TableView<TableTask> tblTasks;


    
	private Project p = ourData.projects.get(ourData.selected);

	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	private ObservableList<Person> person;
	private ObservableList<TableTask> tableTask;
	
    @FXML
    void initialize(){
    	textProjectName.setText(p.getTitle());
    	textDeadline.setText(dateFormatter.format(p.getDeadline()));
    	textEvent.setText(dateFormatter.format(p.getEventDate()));
    	textPriority.setText(p.getPriority().toString());
    	textStatus.setText(p.getStatus().getStatus());
    	//textContactPerson.setText(p.getCustomer().getContactPerson());
    	textDescription.setText(p.getDescription());
    	textNotes.setText(p.getNotes());
    	
		tblColName.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
		tblColPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("phoneNumber"));
		tblColMail.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
		tblColAd.setCellValueFactory(new PropertyValueFactory<Person, String>("address"));
		tblColRelation.setCellValueFactory(new PropertyValueFactory<Person, String>("relation"));
		person = FXCollections.observableArrayList();

		if (p.getCustomer() != null && p.getCustomer().getPersons() != null) {
			for (Iterator<Person> pIt = p.getCustomer().getPersons().iterator(); pIt.hasNext();) {
				person.add(pIt.next());
			}
		}
		tblPersons.setItems(person);
		
		tblColTask.setCellValueFactory(new PropertyValueFactory<TableTask, String>("name"));
		tblColRemark.setCellValueFactory(new PropertyValueFactory<TableTask, String>("remark"));
		tblColStatus.setCellValueFactory(new PropertyValueFactory<TableTask, String>("status"));
		tblColPriority.setCellValueFactory(new PropertyValueFactory<TableTask, String>("priority"));
		tblColDate.setCellValueFactory(new PropertyValueFactory<TableTask, String>("date"));
		tableTask = FXCollections.observableArrayList();

		String dateString = "";
		if (p.getTasks() != null) {
			for (Iterator<Task> t = p.getTasks().iterator(); t.hasNext();) {
				Task pTask = t.next();
				if (pTask.getDate() != null) {
					dateString = dateFormatter.format(pTask.getDate());
				}
				tableTask.add(new TableTask(pTask.getName(), "", pTask.getRemark(), pTask.getStatus().getStatus(),
						pTask.getPriority().toString(), dateString));
			}
		}
		tblTasks.setItems(tableTask);
    }
    
	/**
	 * delete the selected project in the edit tab
	 * 
	 * @author Lukas Schiefermueller
	 * @param event
	 *            select delete the project
	 */
	public void btnDeleteProject(ActionEvent event) {
		if (alert(true)) {
			ourFileHandler.delete(p);
		}
	}

    @FXML
    void btnEditProject(ActionEvent event) {
		AnchorPane pane;
		try {
			pane = FXMLLoader.load(getClass().getResource("/application/EditProject.fxml"));
			apProjectView.getChildren().setAll(pane);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * print alert window
	 * 
	 * @author Lukas Schiefermueller
	 * @param delete
	 *            true if delete project, false otherwise
	 * @return true if ok is pressed
	 */
	private boolean alert(boolean delete) {
		// http://code.makery.ch/blog/javafx-dialogs-official/
		Alert alert;

			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Projekt löschen");
			alert.setHeaderText("Achtung");
			alert.setContentText("Wirklich löschen?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				return true;
			}
			return false;

	}

}
