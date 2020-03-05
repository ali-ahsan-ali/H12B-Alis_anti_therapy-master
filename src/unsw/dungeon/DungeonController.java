package unsw.dungeon;

import java.awt.Label;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class DungeonController {

    @FXML
    private GridPane squares;
    
    @FXML
    private Button resetButton;
    
    @FXML
    private Button levelsButton;
    
    @FXML
    private Label key1;
    
    @FXML
    private Label key2;
    
    @FXML
    private Label sword1;
    
    @FXML
    private Label sword2;
    
    @FXML
    private Label potion1;
    
    @FXML
    private Label potion2;
    
    private DungeonScreen dungeonScreen;
    
    private LevelsScreen levelScreen;
    
    private GameOverScreen gameOver;
    
    private DungeonScreen gameComplete;

    private List<ImageView> initialEntities;

    private Player player;
    private Player player2;

    private Dungeon dungeon;

    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        this.player = dungeon.getPlayer();
    	this.player2 = dungeon.getPlayer2();
        this.initialEntities = new ArrayList<>(initialEntities);
    }

    @FXML
    public void initialize() {
        Image ground = new Image("/dirt_0_new.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight(); y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }

        for (ImageView entity : initialEntities)
            squares.getChildren().add(entity);

    }
    
    

    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            break;
        case DOWN:
            player.moveDown();
            break;
        case LEFT:
            player.moveLeft();
            break;
        case RIGHT:
            player.moveRight();
            break;
        case SPACE:
        	player.useSword();
        default:
            break;
        }
        
        if (player2 != null) {
	        switch (event.getCode()) {
	        case W:
	            player2.moveUp();
	            break;
	        case S:
	            player2.moveDown();
	            break;
	        case A:
	            player2.moveLeft();
	            break;
	        case D:
	            player2.moveRight();
	            break;
	        case F:
	        	player2.useSword();
	       
	        default:
	            break;
	        }
	        
        }
        //run game in accordance to the user stories
        dungeon.game();
        
        if (dungeon.getPlayer2() == null) {
        	player2 = null;
        }
        
        if (dungeon.GameComplete()) {
        	gameComplete.start();
        } else if (dungeon.game() == false) {
        	gameOver.start();
        }

    }
    

    
    @FXML
    public void handleResetButton(ActionEvent event) throws IOException {
    	DungeonScreen newDungeon = new DungeonScreen(dungeonScreen.getStage(), dungeonScreen.getTitle(), dungeonScreen.getLevelPath());
		newDungeon.getController().setDungeonScreen(newDungeon);
		newDungeon.getController().setLevelScreen(levelScreen);		
		newDungeon.start();
    }
    
    @FXML
    public void handleLevelsButton(ActionEvent event) throws IOException {
    	this.levelScreen.start();
    }
    
    public void setDungeonScreen(DungeonScreen dungeonScreen) {
    	this.dungeonScreen = dungeonScreen;
    }
    
    public void setLevelScreen(LevelsScreen levelScreen) {
    	this.levelScreen = levelScreen;
    }
}

