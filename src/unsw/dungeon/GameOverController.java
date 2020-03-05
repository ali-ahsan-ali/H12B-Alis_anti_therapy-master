package unsw.dungeon;

import java.io.IOException;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameOverController {

	@FXML
    private Button tryAgain;
    
    @FXML
    private Button mainMenu;
    
    private MainMenuScreen mainMenuScreen;
    private DungeonScreen dungeonScreen;
    
    @FXML
    public void handleTryAgain(ActionEvent event) throws IOException {
    	dungeonScreen.start();
    }
    
    @FXML
    public void handleMainMenu(ActionEvent event) throws IOException {
    	mainMenuScreen.start();
    }
    
}