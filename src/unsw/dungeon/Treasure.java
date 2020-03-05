package unsw.dungeon;

/**
 * treasure entity to be pciked up by player to complete goals
 * @author ali
 *
 */

public class Treasure extends Entity {

	/**
	 * create a treasure entity with x and y coords to be picke dup by player
	 * @param x
	 * @param y
	 */
    public Treasure(int x, int y) {
        super(x, y);
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
	    
	    if (!(o instanceof Treasure)) {
	        return false;
	    }

	    Treasure that = (Treasure) o;
	    
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}
    
}