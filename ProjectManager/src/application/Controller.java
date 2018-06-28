package application;

// JavaFX imports
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

import java.text.SimpleDateFormat;
// Java imports
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

// ProjectManager imports
import files.FileHandler;

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
	 * our file handler
	 */
	protected FileHandler ourFileHandler = FileHandler.getFileHandler();

	/**
	 * our date formatter
	 */
	SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");

	/**
	 * print alert window
	 * 
	 * @author Lukas Schiefermueller
	 * @param delete
	 *            true if delete project, false otherwise
	 * @return true if ok is pressed
	 */
	protected boolean alert(AlertType type) {
		// http://code.makery.ch/blog/javafx-dialogs-official/
		Alert alert;
		switch (type) {
		case CONFIRMATION:
			alert = new Alert(type);
			alert.setTitle("Projekt l\u00F6schen");
			alert.setHeaderText("Achtung");
			alert.setContentText("Wirklich l\u00F6schen?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				return true;
			}
			return false;
		case ERROR:
		default:
			alert = new Alert(type);
			alert.setTitle("Fehler");
			alert.setHeaderText("Auswahl");
			alert.setContentText("Es ist nichts ausgew\u00E4hlt!");
			alert.showAndWait();
			return false;
		case INFORMATION:
			alert = new Alert(type);
			alert.setTitle("Information");
			alert.setHeaderText("Kein Projektname eingegeben!");
			alert.setContentText("Bitte Projektnamen eingeben und erneut speichern.");
			alert.showAndWait();
			return false;
		}
	}

	/**
	 * https://stackoverflow.com/questions/20446026/get-value-from-date-picker
	 * convert a LocalDate to a Date
	 * 
	 * @author Julia Hofer
	 * @param localDate
	 *            the LocalDate format from the DatePicker
	 */
	protected Date localDateToDate(LocalDate localDate) {
		Instant instant;
		if (localDate != null) {
			instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
			return Date.from(instant);
		} else {
			return new Date();
		}
	}

	/**
	 * convert a Date to a LocalDate
	 * 
	 * @author Julia Hofer
	 * @param date
	 *            the Date format from the project for the DatePicker
	 */
	protected LocalDate dateToLocalDate(Date date) {
		if (date != null) {
			return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} else {
			return (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
	}
}
