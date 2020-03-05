package unsw.dungeon;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LevelController  {

    @FXML
    private Button exitGoal;

    @FXML
    private Button enemyGoal;

    @FXML
    private Button treasureGoal;

    @FXML
    private Button boulderGoal;
    
    @FXML 
    private Button firstComplex;
    
    @FXML
    private Button secondComplex;

    @FXML
    private Button mainMenuButton;
    
    private DungeonScreen dungeonScreen;
    private MainMenuScreen mainMenu;
    

    @FXML
    void handleBoulderGoal(ActionEvent event) {
    	dungeonScreen.setLevelPath("boulders.json");
    	dungeonScreen.start();
    }

    @FXML
    void handleEnemyGoal(ActionEvent event) {
    	dungeonScreen.setLevelPath("advanced.json");
    	dungeonScreen.start();
    }

    @FXML
    void handleExitGoal(ActionEvent event) {
    	dungeonScreen.setLevelPath("maze.json");
    	dungeonScreen.start();
    }

    @FXML
    void handleTreasureGoal(ActionEvent event) {
    	dungeonScreen.setLevelPath("advanced.json");
    	dungeonScreen.start();
    }

    @FXML
    void handleMainMenuButton(ActionEvent event) {
    	mainMenu.start();
    }
    	
    @FXML
    void handleFirstComplex(ActionEvent event) {
    	dungeonScreen.setLevelPath("advanced.json");
    	dungeonScreen.start();
    }

    @FXML
    void handleSecondComplex(ActionEvent event) {
    	dungeonScreen.setLevelPath("advanced.json");
    	dungeonScreen.start();
    }
}

