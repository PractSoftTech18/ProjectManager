package application;

import java.util.Optional;

import files.FileHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * abstract class which contains the data and important functions
 * 
 * @author Lydia Grillenberger
 * @author Lukas Schiefermueller
 * @version 1.00, June 28th 2018
 */
public abstract class Controller {
	/**
	 * available data for this run of the application
	 */
	protected Data ourData = Data.getData();

	/**
	 * FileHandler
	 */
	protected FileHandler ourFileHandler = FileHandler.getFileHandler();
	
	/**
	 * print alert window
	 * 
	 * @author Lukas Schiefermueller
	 * @param delete
	 *            true if delete project, false otherwise
	 * @return true if ok is pressed
	 */
	protected boolean alert(boolean delete) {
		// http://code.makery.ch/blog/javafx-dialogs-official/
		Alert alert;
		if (delete) {
			alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Projekt l\u00F6schen");
			alert.setHeaderText("Achtung");
			alert.setContentText("Wirklich l\u00F6schen?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				return true;
			}
			return false;
		}
		alert = new Alert(AlertType.ERROR);
		alert.setTitle("Fehler");
		alert.setHeaderText("Auswahl");
		alert.setContentText("Es ist nichts ausgew\u00E4hlt!");
		alert.showAndWait();
		return false;
	}

}
