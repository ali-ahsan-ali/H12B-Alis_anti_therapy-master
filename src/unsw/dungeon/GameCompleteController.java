package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameCompleteController {

    @FXML
    private Button mainMenu;

    @FXML
    private Button levelMenu;

    private LevelsScreen levelScreen;
    private MainMenuScreen mainScreen;
    
    @FXML
    void handleLevelMenu(ActionEvent event) {
    	levelScreen.start();
    }

    @FXML
    void handleMainMenu(ActionEvent event) {
    	mainScreen.start();
    }
    
    public void setMainMenuScreen(MainMenuScreen mainScreen) {
        this.mainScreen = mainScreen;
    }
    
    public void setLevelScreen(LevelsScreen levelScreen) {
        this.levelScreen = levelScreen;
    }


}