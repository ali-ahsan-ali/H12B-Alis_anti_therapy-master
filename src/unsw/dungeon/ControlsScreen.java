package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class ControlsScreen {

	private Stage stage;
	private String title;
	private ControlsController controller;
	private Scene scene;
	
	public ControlsScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Controls";
		
		controller = new ControlsController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Controls.fxml"));
        loader.setController(controller);

        Parent root = loader.load();
        scene = new Scene(root);
	}
	
	public void start() {
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * @return the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * @return the controller
	 */
	public ControlsController getController() {
		return controller;
	}
}
