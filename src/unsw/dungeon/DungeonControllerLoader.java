package unsw.dungeon;

import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import unsw.dungeon.Door;

/**
 * A DungeonLoader that also creates the necessary ImageViews for the UI,
 * connects them via listeners to the model, and creates a controller.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonControllerLoader extends DungeonLoader {

    private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image exitImage;
    private Image treasureImage;
	private Image boulderImage;
	private Image FloorSwitchImage;
	private Image enemyImage;
	private Image potionImage;
	private Image swordImage;
	private Image portalImage;
	private Image keyImage;
	private Image closedDoorImage;
	private Image openDoorImage;

    public DungeonControllerLoader(String filename)
            throws FileNotFoundException {
        super(filename);
        entities = new ArrayList<>();
        playerImage = new Image("/human_new.png");
        wallImage = new Image("/brick_brown_0.png");
        exitImage = new Image("/exit.png");
        treasureImage = new Image("/gold_pile.png");
        closedDoorImage = new Image("/closed_door.png");
        openDoorImage = new Image("/open_door.png");
        openDoorImage = new Image("/open_door.png");
        boulderImage = new Image("/boulder.png");
        FloorSwitchImage = new Image("/pressure_plate.png");
        enemyImage = new Image("/deep_elf_master_archer.png");
        potionImage = new Image("/brilliant_blue_new.png");
        swordImage = new Image("/greatsword_1_new.png");
        portalImage = new Image("/portal.png");
        keyImage = new Image("/key.png");
    }

    @Override
    public void onLoad(Entity player) {
        ImageView view = new ImageView(playerImage);
        addEntity(player, view);
    }

    @Override
    public void onLoad(Wall wall) {
        ImageView view = new ImageView(wallImage);
        addEntity(wall, view);
    }
    @Override
    public void onLoad(Exit exit) {
        ImageView view = new ImageView(exitImage);
        addEntity(exit, view);
    }
    @Override
    public void onLoad(Treasure treasure) {
        ImageView view = new ImageView(treasureImage);
        addEntity(treasure, view);
    }
    
    @Override
    public void onLoad(Door door) {
        ImageView view = new ImageView(closedDoorImage);
        addEntity(door, view);
    }
    
    @Override
    public void onLoad(Portal portal) {
        ImageView view = new ImageView(portalImage);
        addEntity(portal, view);
    }
    
    @Override
    public void onLoad(Sword sword) {
        ImageView view = new ImageView(swordImage);
        addEntity(sword, view);
    }
    
    @Override
    public void onLoad(Boulder boulder) {
        ImageView view = new ImageView(boulderImage);
        addEntity(boulder, view);
    }
    @Override
    public void onLoad(FloorSwitch floorswitch) {
        ImageView view = new ImageView(FloorSwitchImage);
        addEntity(floorswitch, view);
    }
    
    @Override
    public void onLoad(Enemy enemy) {
        ImageView view = new ImageView(enemyImage);
        addEntity(enemy, view);
    }
    
    @Override
	public void onLoad(Potion potion) {
    	ImageView view = new ImageView(potionImage);
        addEntity(potion, view);
		
	}
    
    @Override
	public void onLoad(Key key) {
    	ImageView view = new ImageView(keyImage);
        addEntity(key, view);
		
	}

    private void addEntity(Entity entity, ImageView view) {
        trackPosition(entity, view);
        entities.add(view);
    }
    
 

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        GridPane.setColumnIndex(node, entity.getX());
        GridPane.setRowIndex(node, entity.getY());
        entity.x().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setColumnIndex(node, newValue.intValue());
            }
        });
        entity.y().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable,
                    Number oldValue, Number newValue) {
                GridPane.setRowIndex(node, newValue.intValue());
            }
        });
        if (entity instanceof Door) {
            Door d = (Door) entity;
            d.isDoorOpen().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (newValue) {
                        ImageView image = (ImageView) node;
                        image.setImage(openDoorImage);
                    }
                }
            });
        }
        entity.visible().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                node.setVisible(newValue);
            }
        });
        	
       
       
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

	


}
