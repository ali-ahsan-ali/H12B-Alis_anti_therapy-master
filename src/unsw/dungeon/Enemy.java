package unsw.dungeon;


/**
 * 
 * @author ali
 *Enemy class which can move towards player or away from player based on player invulnerability
 *also kills player on impact unless invulnerable
 */
public class Enemy extends Entity implements Observer{
	private Dungeon dungeon;
	private EnemyAction action;

	/**
	 * Creates an enemy with acess to the dungeon to check to see if there are walls players or boulders
	 * created enemy at starrting x and y coordinate
	 * @param dungeon
	 * @param x
	 * @param y
	 */
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.action = new MoveToPlayer(dungeon, this);
        dungeon.getPlayer().registerObserver(this);
        if (dungeon.getPlayer2() != null) {
        	dungeon.getPlayer2().registerObserver(this);
        }
    }
    
    @Override
    /**
     * This is the observer implementation to move to player if vulnerable or 
     * away from player if invulnerable
     */
	public void update(boolean potion) {
		if (potion == true) {
			this.action = new MoveAwayFromPlayer(this.dungeon, this);
		}else {
			//System.out.println("player is " + potion);
			this.action = new MoveToPlayer(this.dungeon, this);
		}
		
	}
    /**
     * sets the enemy movement to be away from player
     */
    public void MoveAwayFromPlayer() {
    	this.action = new MoveAwayFromPlayer(this.dungeon, this);
    }
    
    /**
     * sets the enemy movement to be to the player
     */
    public void MoveToPlayer() {
        this.action = new MoveToPlayer(this.dungeon, this);
    }
    
    /**
     * moved the player in a directtion based on the action logic
     */
    public void move() {
    	this.action.Move();
    }
    
    /**
     * moved a player down unless thre is a wall ro a boulder or a door
     * checks if door is open before going
     */
	public void moveDown() {
		int x = getX();
		int y = getY();
		if (getY() < dungeon.getHeight() - 1) {
        	if (dungeon.checkWall(x, y + 1) == false 
    			&& dungeon.checkBoulder(x, y + 1) == false
    			&& dungeon.checkDoor(x, y + 1) == false) {
                y().set(y + 1);
        	}else if (dungeon.checkDoor(x, y + 1) == true) {
        		Door door = dungeon.getDoor(x, y + 1);
        		if (door.isDoorOpen().get()) {
                    y().set(y + 1);
        		}
        	}else {
        	}
        }
    	       
    }

	/**
     * moved a player up unless thre is a wall ro a boulder or a door
     * checks if door is open before going
     */
	public void moveUp() {
		int x = getX();
		int y = getY();
        if (y > 0) {
        	if (dungeon.checkWall(x, y - 1) == false 
    			&& dungeon.checkBoulder(x, y - 1) == false
    			&& dungeon.checkDoor(x, y - 1) == false) {
//        		System.out.println("Enemy moved up\n");
                y().set(y - 1);
        	}else if (dungeon.checkDoor(x, y - 1) == true) {
        		Door door = dungeon.getDoor(x, y - 1);
        		if (door.isDoorOpen().get()) {
                    y().set(y - 1);
        		}
        	}else {
//        		System.out.println("Wall - Can't go that way\n");
        	}
        }		
	}

	/**
     * moved a player left unless thre is a wall ro a boulder or a door
     * checks if door is open before going
     */
	public void moveLeft() {
		int x = getX();
		int y = getY();
        if (x > 0) {
        	if (dungeon.checkWall(x - 1, y) == false 
    			&& dungeon.checkBoulder(x - 1, y) == false
    			&& dungeon.checkDoor(x - 1, y) == false) {
                x().set(x - 1);
        	}else if (dungeon.checkDoor(x - 1, y) == true) {
        		Door door = dungeon.getDoor(x - 1, y);
        		if (door.isDoorOpen().get()) {
                    x().set(x - 1);
        		}
        	}else {
        	}
        }		
	}
	/**
     * moved a player right unless thre is a wall ro a boulder or a door
     * checks if door is open before going
     */
	public void moveRight() {
		int x = getX();
		int y = getY();
        if (x < dungeon.getWidth() - 1) {
        	System.out.println("HUH");
        	if (dungeon.checkWall(x + 1, y) == false 
    			&& dungeon.checkBoulder(x + 1, y) == false
    			&& dungeon.checkDoor(x + 1, y) == false) {
            	System.out.println("HUH1");
            	System.out.println(x);
                x().set(x + 1);
            	System.out.println(getX());
        	}else if (dungeon.checkDoor(x + 1, y) == true) {
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
	 * @param Object o - object comparion this instance with
	 * @return boolean - if the same object or not
	 */
	public boolean equals(Object o) {
	    if (this == o) {
	        return true;
	    }
	    
	    if (!(o instanceof Enemy)) {
	        return false;
	    }

	    Enemy that = (Enemy) o;
	    if (that.getX() == this.getX() &&
	    		that.getY() == this.getY()) {
	    	return true;
	    }
	    return false;
	}


	
}