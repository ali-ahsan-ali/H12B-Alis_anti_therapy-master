package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ModeController {

    @FXML
    private Button singleMode;

    @FXML
    private Button multiMode;

    @FXML
    private Button back;
    
    private MainMenuScreen mainMenu;
    private LevelsScreen levelScreen;

    @FXML
    void handleBack(ActionEvent event) {
    	mainMenu.start();
    }

    @FXML
    void handleMultiMode(ActionEvent event) {
    	levelScreen.start();
    }

    @FXML
    void handleSingleMode(ActionEvent event) {
    	levelScreen.start();
    }

}
