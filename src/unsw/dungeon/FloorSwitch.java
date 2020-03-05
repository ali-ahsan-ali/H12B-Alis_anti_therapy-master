package unsw.dungeon;


/**
 * Floor switch entity
 * True if a boulder if placed on it or false otherwise
 * if all boulders in a dungeon are active then goal is compelte
 * @author ali
 *
 */
public class FloorSwitch extends Entity {
	private boolean active;
	private Dungeon dungeon;
	
	/**
	 * Creates a floor switch inside a dungeon and places it on x and y
	 * @param dungeon
	 * @param x
	 * @param y
	 */
    public FloorSwitch(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.active = false;
        this.dungeon = dungeon;
        
    }
    /**
     * 
     * @return boolean - if the switch is activated or not
     */
    public boolean checkSwitch() {
    	return this.active;
    }
    /**
     * 
     * @return - checks to see if the switch is active or not 
     * places switch as active if there is a boulder ontop of it
     */
    
    public boolean checkBoulderSwitch() {
    	if (dungeon.checkBoulder(getX(), getY())) {
    		this.active = true;
        	return this.active;
    	}else {
    		this.active = false;
        	return this.active;
    	}
    }
    
    @Override
    /**
	 * @param Object o - object comparion this instance with
	 * @return boolean - if the same object or not
	 */
	public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    
	    if (!(o instanceof FloorSwitch)) {
	        return false;
	    }

	    FloorSwitch that = (FloorSwitch) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()
	    		&& that.active == this.active) {
	    	return true;
	    }
	    return false;
	}
}