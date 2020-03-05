package unsw.dungeon;


/**
 * Portal class that extends entity
 * allows player to teleport to correcposning portal of the same id
 * @author ali
 *
 */
public class Portal extends Entity {
	private int id;

	/**
	 * creates a protal with the x and y coords given and the id given
	 * @param id
	 * @param x
	 * @param y
	 */
    public Portal(int id, int x, int y) {
        super(x, y);
    	this.id = id;

    }

    /**
     * 
     * @return the portal iD
     */
	public int getId() {
		return id;
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
	    
	    if (!(o instanceof Portal)) {
	        return false;
	    }

	    Portal that = (Portal) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY() &&
	    		that.getId() == this.id) {
	    	return true;
	    }
	    return false;
	}
}