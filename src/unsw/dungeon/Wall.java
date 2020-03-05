package unsw.dungeon;

/**
 * wall entity so tht player cannot go through any wall and fly through the dungeon
 * @author ali
 *
 */
public class Wall extends Entity {

	/**
	 * creats a wall entity with the x and y coords given 
	 * @param x
	 * @param y
	 */
    public Wall(int x, int y) {
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
	    
	    if (!(o instanceof Wall)) {
	        return false;
	    }

	    Wall that = (Wall) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}
}
