package unsw.dungeon;


import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControlsController {

    @FXML
    private Button mainMenu;

    private MainMenuScreen main;
    
    @FXML
    void handleMainMenu(ActionEvent event) {
    	main.start();
    }

}

