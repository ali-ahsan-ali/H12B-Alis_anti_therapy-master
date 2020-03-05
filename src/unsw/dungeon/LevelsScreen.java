package unsw.dungeon;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class LevelsScreen {

	private Stage stage;
	private String title;
	private LevelController controller;
	private Scene scene;
	
	public LevelsScreen(Stage stage) throws IOException {
		this.stage = stage;
		this.title = "Level Select";
		
		controller = new LevelController();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Level.fxml"));
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
	public LevelController getController() {
		return controller;
	}
}