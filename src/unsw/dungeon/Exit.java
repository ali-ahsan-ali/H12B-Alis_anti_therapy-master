package unsw.dungeon;

/**
 * The exit entity
 * @author Robert Clifton-Everest
 *
 */
public class Exit extends Entity {

    public Exit(int x, int y) {
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
	    
	    if (!(o instanceof Exit)) {
	        return false;
	    }

	    Exit that = (Exit) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}
    
    
}