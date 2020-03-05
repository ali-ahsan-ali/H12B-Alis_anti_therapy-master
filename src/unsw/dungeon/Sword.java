package unsw.dungeon;


/**
 * sword entity to be picke dup and swing by plafer
 * @author ali
 *
 */
public class Sword extends Entity {
	private int swings;
	/**
	 * creates a sword at certain x and y coords with 5 swings
	 * @param x
	 * @param y
	 */
    public Sword(int x, int y) {
        super(x, y);
        swings = 5;
    }
    
    /** 
     * each swing removed 1 charge from the 5 u strt with
     */
    public void swing () {
    	this.swings = this.swings - 1;
    }
    
    /** 
     * returns the amount of swings u have left
     * @return swings
     */
    public int getSwingCount() {
    	return this.swings;
    }
    
    /**
     * 
     * @return boolean - check to see if sword has any more sinwgs or not
     */
    public boolean check_sword() {
    	System.out.println((this.swings == 0));
    	return (this.swings == 0);
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
	    
	    if (!(o instanceof Sword)) {
	        return false;
	    }

	    Sword that = (Sword) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY() &&
	    		that.getSwingCount() == this.swings) {
	    	return true;
	    }
	    return false;
	}
    
}
