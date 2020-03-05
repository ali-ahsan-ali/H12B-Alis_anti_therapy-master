package unsw.dungeon;


public class Potion extends Entity{
	private int initialTime;
	private int finalTime;
	
	/**
	 *  creates a potion at a certain spot within the dungeon
	 * @param player
	 * @param x
	 * @param y
	 */
    public Potion(int x, int y) {
        super(x, y);
        this.initialTime = 0;
        this.finalTime = this.initialTime + 11;
    }
    
    
    /** 
     * debugging function to print potion timer left
     */
    public void print() {
    	
    	System.out.println(((this.finalTime - this.initialTime) == 0));
    }
    
    /**
     * 
     * @return boolean - check to see if potion hs finsihed or not
     */
    public boolean checkPotionFinished() {
    	return ((this.finalTime - this.initialTime) <= 0);
    }
    
    /**
     * tiks the potion by 1 use every time payer moves
     */
    public void tick() {
    	this.initialTime = this.initialTime + 1;
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
	    
	    if (!(o instanceof Potion)) {
	        return false;
	    }

	    Potion that = (Potion) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}

	





	
}