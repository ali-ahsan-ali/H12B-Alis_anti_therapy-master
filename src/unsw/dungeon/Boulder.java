package unsw.dungeon;


/**
 * 
 * @author ali
 *
 *This class is the boulder entity which can be moved around depending on user 
 *stories outlined prio
 *Boulder can move anywhere unless there is an enemy, wall or player in the way
 */
public class Boulder extends Entity {
	Dungeon dungeon;

	/**
	 * This is the constructor for boulder which takes in a dungeon and its set x and y 
	 * coordinates. It can then move depending on user rules
	 * @param dungeon
	 * @param x
	 * @param y
	 * @return Nothing but creates the class
	 */
    public Boulder(Dungeon dungeon,int x, int y) {
        super(x, y);
    	this.dungeon = dungeon;
    }
    
    /**
     * This is a function which moved the Boulder entity down a single coordinate
     * @return nothing but moved the entity
     * 
     */
    
    public void moveDown() {
		int x = getX();
		int y = getY();
		if (getY() < dungeon.getHeight() - 1) {
        	if (dungeon.checkWall(x, y + 1) == false 
    			&& dungeon.checkBoulder(x, y + 1) == false
    			&& dungeon.checkDoor(x, y + 1) == false) {
                y().set(y + 1);
        	}else if (dungeon.checkDoor(x, y + 1) == true){
        		Door door = dungeon.getDoor(x, y + 1);
        		if (door.isDoorOpen().get()) {
                    y().set(y + 1);
        		}
        	}else {
        	}
        }
    	       
    }

    /**
     * This is a function which moved the Boulder entity up a single coordinate
     * @return nothing but moved the entity
     * 
     */
	public void moveUp() {
		int x = getX();
		int y = getY();
        if (y > 0) {
        	if (dungeon.checkWall(x, y - 1) == false 
    			&& dungeon.checkBoulder(x, y - 1) == false
    			&& dungeon.checkDoor(x, y + 1) == false) {
                y().set(y - 1);
        	}else if (dungeon.checkDoor(x, y - 1) == true){
        		Door door = dungeon.getDoor(x, y - 1);
        		if (door.isDoorOpen().get()) {
                    y().set(y - 1);
        		}
        	}else {
        	}
        }		
	}

	 /**
     * This is a function which moved the Boulder entity left a single coordinate
     * @return nothing but moved the entity
     * 
     */
	public void moveLeft() {
		int x = getX();
		int y = getY();
        if (x > 0) {
        	if (dungeon.checkWall(x - 1, y) == false 
    			&& dungeon.checkBoulder(x - 1, y) == false
    			&& dungeon.checkDoor(x - 1, y) == false) {
                x().set(x - 1);
        	}else if (dungeon.checkDoor(x - 1, y) == true){
        		Door door = dungeon.getDoor(x - 1, y);
        		if (door.isDoorOpen().get()) {
                    x().set(x - 1);
        		}
        	}else {
        	}
        }		
	}
	 /**
     * This is a function which moved the Boulder entity right a single coordinate
     * @return nothing but moved the entity
     * 
     */
	public void moveRight() {
		int x = getX();
		int y = getY();
        if (x < dungeon.getWidth() - 1) {
        	if (dungeon.checkWall(x + 1, y) == false 
    			&& dungeon.checkBoulder(x + 1, y) == false
    			&& dungeon.checkDoor(x + 1, y) == false) {
                x().set(x + 1);
        	}else if (dungeon.checkDoor(x + 1, y) == true){
        		Door door = dungeon.getDoor(x + 1, y);
        		if (door.isDoorOpen().get()) {
                    x().set(x + 1);
        		}
        	}else {
        	}
        }		
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
	    
	    if (!(o instanceof Boulder)) {
	        return false;
	    }

	    Boulder that = (Boulder) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}
}