package unsw.dungeon;

/**
 * Key entity
 * to be used to open doors
 * has a charge and deleted on use
 * @author ali
 *
 */
public class Key extends Entity {
	private int keyID;
	private int charges;
	
	/**
	 * creates a ey at an x and y coordinate with a certain id to be used for a certain door
	 * @param x
	 * @param y
	 * @param keyID
	 */
    public Key(int x, int y, int keyID) {
        super(x, y);
        this.keyID = keyID;
        charges = 1;
    }

    /**
     * 
     * @return key id - of the class
     */
	public int getKeyID() {
		return keyID;
	}
	
	/**
	 * uses the key and sets chargest to be 0
	 */
	public void useKey() {
		this.charges = 0;
	}
	
	/**
	 * 
	 * @return boolean - if key has been used or not used
	 */
	public boolean keyUsed() {
		return (this.charges == 0);
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
	    
	    if (!(o instanceof Key)) {
	        return false;
	    }

	    Key that = (Key) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}

	public int getCharges() {
		return charges;
	}
}