package unsw.dungeon;

import javafx.beans.property.BooleanProperty;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

    // IntegerProperty is used so that changes to the entities position can be
    // externally observed.
    private IntegerProperty x, y;
    BooleanProperty visible;
    /**
     * Create an entity positioned in square (x,y)
     * @param x
     * @param y
     */
    public Entity(int x, int y) {
        this.x = new SimpleIntegerProperty(x);
        this.y = new SimpleIntegerProperty(y);
        visible = new SimpleBooleanProperty(true);
    }

    /**
     * 
     * @return integerproptery of x
     */
    public IntegerProperty x() {
        return x;
    }

    /**
     * 
     * @return integer property of y
     */
    public IntegerProperty y() {
        return y;
    }

    /**
     * 
     * @return y coordinate
     */
    public int getY() {
        return y().get();
    }
    
    /**
     * 
     * @return xoordinate
     */
    public int getX() {
        return x().get();
    }
    
    /**
     * 
     * @return visibility for the front end
     */
    public BooleanProperty visible() {
        return visible;
    }
}
