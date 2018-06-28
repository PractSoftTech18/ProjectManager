package application;

// JavaFX imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * class to start the application
 * 
 * @author Lukas Schiefermueller
 * @version 1.00, June 28th 2018
 */
public class Main extends Application {

	/**
	 * start the application
	 * 
	 * @param primaryStage
	 *            stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/App.fxml"));
			Scene scene = new Scene(root, 950, 650);
			primaryStage.setMinHeight(650);
			primaryStage.setMinWidth(950);
			primaryStage.setMaximized(true);
			primaryStage.setTitle("Project Manager");
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * main method for launching
	 * 
	 * @param args
	 *            are launched
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
