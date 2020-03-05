package unsw.dungeon;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class MainMenuController {

	@FXML
	private Button playButton;
	
	@FXML 
	private Button controlsButton;
	
	private LevelsScreen levels;
	private ControlsScreen controls;
	
	
	@FXML
	public void handlePlayButton(ActionEvent event) {
		levels.start();
	}		

	public void handleControlsButton(ActionEvent event) {
		controls.start();
	}
}
