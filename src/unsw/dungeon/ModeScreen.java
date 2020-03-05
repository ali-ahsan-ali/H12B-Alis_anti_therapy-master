package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModeScreen {

	private Stage stage;
	private String title;
	private ModeController controller;
	private Scene scene;
	
	public ModeScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Pick your mode";
		
		controller = new ModeController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Mode.fxml"));
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
	public ModeController getController() {
		return controller;
	}
}