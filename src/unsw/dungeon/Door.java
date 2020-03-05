package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * 
 * @author ali
 * Door entity which is open or closed which then allows or dissalows certain entities to mover through
 */
public class Door extends Entity {

	int doorID;
	BooleanProperty doorOpen;
	
	/**
	 * Consturctor which creates a door with a certain ID and only a certain key can open and
	 * is located at a certain x and y coorsinate
	 * @param x
	 * @param y
	 * @param doorID
	 */
    public Door(int x, int y, int doorID) {
        super(x, y);
        this.doorID = doorID;
        doorOpen = new SimpleBooleanProperty(false);
    }
    
    /**
     *  Function to see if entities can pass through or not
     * @return passable - ability to pass through the door or not
     */
   public boolean passable() {
	   return doorOpen.get();
   }
   
   /**
    * This sets the door to be open if the korrect key ID is given by the player
    * @param keyId
    */
   public void setOpen(int keyId) {
	   if (keyId == this.doorID) {
		   doorOpen.set(true);
	   }
   }
   
   /**
    * a simple getter for the door ID
    * @return doorId
    */
    public int getDoorID() {
		return doorID;
	}
    /**
     *  return the boolean peroperty of the door 
     * @return doorOpen
     */
    
	public BooleanProperty isDoorOpen() {
		return doorOpen;
	}


	@Override
	/**
     * @return nothing but Overides the equals method for list manipulation in dungeon
     * @param Object o  that this current instance is being compared with 
     */
	public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    
	    if (!(o instanceof Door)) {
	        return false;
	    }

	    Door that = (Door) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}
}

