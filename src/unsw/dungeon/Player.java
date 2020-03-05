package unsw.dungeon;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * The player entity
 * @author Robert Clifton-Everest
 * 
 * It has an inventory of a single slot
 * for sword, key, trasure and potion
 *
 */
public class Player extends Entity implements Subject{

    private Dungeon dungeon;
    private boolean invulnerability;
	private ArrayList<Observer> observers;
	private Key keyInv;
	private Sword swordInv;
	private Treasure treasureInv;
	private Potion potionInv;
	


    /**
     * Create a player positioned in square (x,y)
     * sets inventory to be null for everything
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
		this.observers = new ArrayList<Observer>();
        this.dungeon = dungeon;
        this.invulnerability = false;
        this.keyInv = null;
        this.swordInv = null;
        this.treasureInv = null;
        this.potionInv = null;

        
    }
    
    /**
     * 
     * @return boolean - whether or not the player is invulnerable
     */
    public boolean checkInvulnerability() {
    	return this.invulnerability;
    }
    
    
    /**
     * sets the player to be at certain coordinates - used in teleporting 
     * @param x
     * @param y
     */
    public void teleport(int x, int y) {
    	//
    	this.x().setValue(x);
    	this.y().setValue(y);
    	//System.out.println("AFTER SETTING VALUES" +this.getX() + " " + this.getY());
    }

    /**
     * moves the player up one 
     * if there is a wall cant move up
     * if there is a boulder it moves the boulder up one (unless the boulder cant be moved up one)
     *  and then player moves up one
     *  if there is a door checks to see if it is open, if not then try oepn it using ur key , if wrong key
     *  u cant move
     */
    public void moveUp() {
    	int x = getX();
    	int y = getY();
    	
        if (y > 0) {
        	if (dungeon.checkWall(x, y - 1) == false 
        			&& dungeon.checkBoulder(x, y - 1) == false
        			&& dungeon.checkDoor(x, y - 1) == false) {
                y().set(y - 1);
        	}else if (dungeon.checkDoor(x, y - 1) == true) {
        		System.out.println("DOOR");
        		Door door = dungeon.getDoor(x, y - 1);
        		if (this.getInvKey()  != null) {
        			Key key = this.getInvKey();

        			door.setOpen(key.getKeyID());
            		System.out.println(door.isDoorOpen());
            		if (door.isDoorOpen().get()) {
                		System.out.println("OPEN DOOR DELETED KEY AND MOVE DOWN");
                		key.useKey();
            			this.delInvKey();
            			y().set(y - 1);
            		}
        		}else if (door.isDoorOpen().get()) {
            		System.out.println("OPEN DOOR MOVE DOWN");
        			y().set(y - 1);
        		}
        		
        	}else if (dungeon.checkWall(x, y - 1) == false 
    			&& dungeon.checkBoulder(x, y - 1) == true) {
        		Boulder next = dungeon.getBoulder(x, y - 1);
        		next.moveUp();
        		//if sucessfully moved
        		if (dungeon.checkWall(x, y - 1) == false 
        			&& dungeon.checkBoulder(x, y - 1) == false) {
                	y().set(y - 1);
            	}else {
//            		System.out.println("Wall/Boulder - Can't go that way\n");
            	}
        	}else {
//        		System.out.println("Wall - Can't go that way\n");
        	}
        }
    }
    /**
     * moves the player down one 
     * if there is a wall cant move down
     * if there is a boulder it moves the boulder down one (unless the boulder cant be moved down one)
     *  and then player moves down one
     *  if there is a door checks to see if it is open, if not then try oepn it using ur key , if wrong key
     *  u cant move
     */
    public void moveDown() {
    	int x = getX();
    	int y = getY();
    	
        if (getY() < dungeon.getHeight() - 1) {
        	if (dungeon.checkWall(x, y + 1) == false 
        			&& dungeon.checkBoulder(x, y + 1) == false 
        			&& dungeon.checkDoor(x, y + 1) == false) {
        		System.out.println("NOTHING");
                y().set(y + 1);
        	}else if (dungeon.checkDoor(x, y + 1) == true) {
        		System.out.println("DOOR");
        		Door door = dungeon.getDoor(x, y + 1);
        		if (this.getInvKey()  != null) {
        			Key key = this.getInvKey();

        			door.setOpen(key.getKeyID());
            		System.out.println(door.isDoorOpen());
            		if (door.isDoorOpen().get()) {
                		System.out.println("OPEN DOOR DELETED KEY AND MOVE DOWN");
                		key.useKey();
            			this.delInvKey();
            			y().set(y + 1);
            		}
        		}else if (door.isDoorOpen().get()) {
            		System.out.println("OPEN DOOR MOVE DOWN");
        			y().set(y + 1);
        		}
        		
        	}else if (dungeon.checkWall(x, y + 1) == false 
    			&& dungeon.checkBoulder(x, y + 1) == true) {
        		System.out.println("MOVE BOULDER");
        		Boulder next = dungeon.getBoulder(x, y + 1);
        		next.moveDown();
        		//if sucessfully moved
        		if (dungeon.checkWall(x, y + 1) == false 
        			&& dungeon.checkBoulder(x, y + 1) == false) {
                	y().set(y + 1);
            	}else {
//            		System.out.println("Wall/Boulder - Can't go that way\n");
            	}
        	}else {
//        		System.out.println("Wall - Can't go that way\n");
        	}
        }
    }

    /**
     * moves the player left one 
     * if there is a wall cant moved left
     * if there is a boulder it moves the boulder left one (unless the boulder cant be moved left one)
     *  and then player moves left one
     *  if there is a door checks to see if it is open, if not then try oepn it using ur key , if wrong key
     *  u cant move
     */
    public void moveLeft() {
    	int x = getX();
    	int y = getY();
    	
        if (x > 0) {
        	if (dungeon.checkWall(x - 1, y ) == false 
        			&& dungeon.checkBoulder(x - 1, y) == false
        			&& dungeon.checkDoor(x - 1, y) == false) {
                x().set(x - 1);
        	}else if (dungeon.checkDoor(x - 1, y) == true) {
        		System.out.println("DOOR");
        		Door door = dungeon.getDoor(x - 1, y);
        		if (this.getInvKey()  != null) {
        			Key key = this.getInvKey();
        			
        			door.setOpen(key.getKeyID());
            		System.out.println(door.isDoorOpen());
            		if (door.isDoorOpen().get()) {
                		System.out.println("OPEN DOOR DELETED KEY AND MOVE DOWN");
                		key.useKey();
            			this.delInvKey();
            			x().set(x - 1);
            		}
        		}else if (door.isDoorOpen().get()) {
            		System.out.println("OPEN DOOR MOVE DOWN");
        			x().set(x - 1);
        		}
        		
        	}else if (dungeon.checkWall(x - 1, y) == false 
    			&& dungeon.checkBoulder(x - 1, y) == true) {
        		Boulder next = dungeon.getBoulder(x - 1, y);
        		next.moveLeft();
        		//if sucessfully moved
        		System.out.println(next.getX() + " " + next.getY());
        		System.out.println(this.getX() + " " + this.getY());
        		if (dungeon.checkWall(x - 1, y) == false 
        			&& dungeon.checkBoulder(x - 1, y) == false) {
                	x().set(x - 1);
            	}else {
//            		System.out.println("Wall/Boulder - Can't go that way\n");
            	}
        	}else {
//        		System.out.println("Wall - Can't go that way\n");
        	}
        }
    }

    /**
     * moves the player right one 
     * if there is a wall cant move right
     * if there is a boulder it moves the boulder right one (unless the boulder cant be moved right one)
     *  and then player moves right one
     *  if there is a door checks to see if it is open, if not then try oepn it using ur key , if wrong key
     *  u cant move
     */
    public void moveRight() {
    	int x = getX();
    	int y = getY();
    	
    	if (x < dungeon.getWidth() - 1) {
        	if (dungeon.checkWall(x + 1, y) == false 
        			&& dungeon.checkBoulder(x + 1, y) == false
        			&& dungeon.checkDoor(x + 1, y) == false) {
                x().set(x + 1);
        	}else if (dungeon.checkDoor(x + 1, y) == true) {
        		System.out.println("DOOR");
        		Door door = dungeon.getDoor(x + 1, y);
        		if (this.getInvKey()  != null) {
        			Key key = this.getInvKey();
        			door.setOpen(key.getKeyID());
            		System.out.println(door.isDoorOpen());
            		if (door.isDoorOpen().get()) {
                		key.useKey();
            			this.delInvKey();
            			x().set(x + 1);
            		}
        		}else if (door.isDoorOpen().get()) {
            		System.out.println("OPEN DOOR MOVE DOWN");
        			x().set(x+ 1);
        		}
        		
        		
        	}else if (dungeon.checkWall(x + 1, y) == false 
    			&& dungeon.checkBoulder(x + 1, y) == true) {
        		Boulder next = dungeon.getBoulder(x + 1, y );
        		next.moveRight();
        		//if sucessfully moved
        		if (dungeon.checkWall(x + 1, y) == false 
        			&& dungeon.checkBoulder(x + 1, y) == false) {
                	x().set(x + 1);
            	}else {
            	}
        	}else {
        	}
        }    
    }
    
    /**
     * intercts with environments based on user rules
     * picks up any item it can off the floor every tick 
     * then ticks the potion if it has one
     */
    public void interact() {
    	//the floor you land on - pick up anything
    	int x = getX();
		int y = getY();
		
    	if (this.dungeon.checkTreasure(x, y)) {
    		this.pickupTreasure();
    	}if (this.dungeon.checkKey(x, y)) {
    		this.pickupKey();
    	}if (this.dungeon.checkSword(x, y)) {
    		this.pickupSword();
    	}if (this.dungeon.checkPotion(x, y)) {
    		this.pickupPotion();
    	}
    	
    	
    	
    	//tick potion if you have one
    	// delete from inv if no ticks left
    	if (this.potionInv != null) {
	    	this.potionInv.tick();
	    	if (this.potionInv.checkPotionFinished() == true) {
	    		this.potionInv = null;
	    		this.invulnerabilityChange(false);
	    	}
    	}
    	
    }
    
    /**
     * if there is a treasure on the same spot as you then pick it up and add it to inve
     */
    public void pickupTreasure() {
    	//can pick up multiple treasures 
    	this.treasureInv = dungeon.getTreasure(getX(), getY());
    	this.dungeon.delTreasure(getX(), getY());
    	System.out.println("added treasure to inv");
    }
    /**
     * if there is a sword on the same spot as you then pick it up and add it to inve
     */
    public void pickupSword() {
    	//if sword already in inv dont pick up
    	if (this.swordInv == null) {
    		this.swordInv = dungeon.getSword(getX(), getY());
	    	this.dungeon.delSword(getX(), getY());
	    	System.out.println("added sword to inv");
    	}
    }
    /**
     * if there is a key on the same spot as you then pick it up and add it to inve
     * if already have a key then dont pick it up
     */
    public void pickupKey() {
    	if (this.keyInv != null) return;
    	
    	if (dungeon.getKey(getX(), getY()) != null){
    		this.keyInv = dungeon.getKey(getX(), getY());
        	System.out.println("added key to inv");
    	}
    	this.dungeon.delKey(getX(), getY());
    }
    /**
     * if there is a potion on the same spot as you then pick it up and add it to inve
     * if already have a potion then pick it up and replace with the old one
     */
    public void pickupPotion() {
    	
    	this.potionInv =  dungeon.getPotion(getX(), getY());
    	this.dungeon.delPotion(getX(), getY());
    	this.invulnerabilityChange(true);
    }
    
    /**
     * 
     * @return potion - the potion in ur inventory
     */
    public Potion getPotion() {
    	return this.potionInv;
    }
    
    
    /**
     * use the sword in ur inv and swing it for one charge.
     * then kill one enmy in ur immediate surroudning if there is one
     */
    public void useSword() {
    	int x = getX();
		int y = getY();
		
		//if an enemy near by and u swing
		// kill the enmy after using a swing charge
		// if no charges are left delete the sword
		if (this.playerSwing() == false) return;
		
    	if (dungeon.checkEnemy(x + 1, y)) {
    		dungeon.delEnemy(x + 1, y);
    	}else if (dungeon.checkEnemy(x, y + 1)) {
    		dungeon.delEnemy(x, y + 1);
    	}else if (dungeon.checkEnemy(x - 1, y)) {
        	dungeon.delEnemy(x - 1, y);
    	}else if (dungeon.checkEnemy(x, y - 1)) {
        	dungeon.delEnemy(x, y - 1);
    	}else if (dungeon.checkEnemy(x + 1, y + 1)) {
    		dungeon.delEnemy(x + 1, y + 1);
    	}else if (dungeon.checkEnemy(x + 1, y - 1)) {
    		dungeon.delEnemy(x + 1, y - 1);
    	}else if (dungeon.checkEnemy(x - 1, y + 1)) {
    		dungeon.delEnemy(x - 1, y + 1);
    	}else if (dungeon.checkEnemy(x - 1, y - 1)) {
    		dungeon.delEnemy(x - 1, y - 1);    		
    	}	
    	
    }
    
    /**
     * 
     * @return boolean - check to see if there is s sword in ur inventory
     */
    public boolean checkSword() {
    	if (this.swordInv == null) return false;
    	return true;
    }
    
    /**
     * 
     * @param id
     * @return boolean - check to see if there is a key in your inv of a certain id
     */
    public boolean checkInvKey(int id) {
    	if (this.keyInv == null) return false;
    	else if (this.keyInv.getKeyID() == id) {
    		return true;
    	}
    	return false;
    	
    }
    /**
     * 
     * @return key - the key in ur inv, null if nothing
     */
    public Key getInvKey() {
    	return this.keyInv;
    }
    
    /**
     * check to see if there is treasure in ur inv
     * @return - boolean  - 
     */
    public boolean checkInvTreasure() {
    	if (this.treasureInv == null) return false;
    	return true;
    }
    
    /**
     * delete the key in ur inv by setting it to null
     */
    public void delInvKey() {
    	this.keyInv = null;
    }
    
    /**
     * swing the sword in ur inv
     * if no sword return false
     * if there s a sword return true
     * if the sword is now out of swings then delete the sword
     * @return boolean
     */
    public boolean playerSwing() {
    	if (this.swordInv == null) return false;
    	this.swordInv.swing();
    	if(this.swordInv.check_sword() == true) {
    		this.swordInv = null;
    	}
    	return true;
			
    }
    /**
     * add an observer to observer list to make observer apttern with the observer being the enemy
     */
    public void registerObserver(Observer o) {
		observers.add(o);
	}
	
    /**
     * remove an observer from the lsit 
     */
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >= 0) {
			observers.remove(i);
		}
	}
	/**
	 * notify all observers or enemies of the change in invulnerabilty and to star trunning away
	 */
	public void notifyObservers(boolean invulnerability) {
		for (Observer obs : observers) {
			obs.update(invulnerability);
		}
	}
    
	/** 
	 * a function to change the boolean of ur invulnerabiltiy and 
	 * then call notify observer to tell them about the change
	 * @param change
	 */
	public void invulnerabilityChange(boolean change) {
		this.invulnerability = change;
		this.notifyObservers(change);
	}
	
}
