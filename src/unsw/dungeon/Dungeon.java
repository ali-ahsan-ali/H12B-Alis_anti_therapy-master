			/**
 *
 */
package unsw.dungeon;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 * 
 * I did my best to remove getClass() in the time constrain i had with use of
 * equals method ovverides and indexOf
 * 
 * I also attempted to move certain function to other classes but remained unfeasable due 
 * to the amount of entities that needed acess to such information.
 * Additionally, 
 * 
 * The main dungeon class which holds all entites and controls movement of enemies every tick
 * teleports player every tick and ticks through the game through the this.game function
 *
 */
public class Dungeon{

	/**
	 * wish and height of the dungeon to create
	 * the entities present within the dungeon
	 * the two possible players within the dungeon
	 * the goal to complete the dungeon and end the game 
	 */
    private int width, height;
    private List<Entity> entities;
    private Player player;
    private Player player2;
    private Composite goal;

    /**
     * Constructor which creates a dungeon based on the dimensions given and
     * creates null variables to be set but the dungeon loader
     * @param width
     * @param height
     */
    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.goal = null;

    }
    
    
    /**
     * @return goal - goal of the dungeon once compelted should finish
     */
    public Composite getGoal() {
    	return this.goal;
    }
    
    /**
     * Set the goal once read in through the loader
     * @param goal - goal of the dungeon
     */
    public void setGoal(Composite goal) {
    	this.goal = goal;
    }
    
    /**
     * returns whethere or not the goal of the dungeon has sucessfully been achieves
     * @return goal completed
     */
    public boolean GameComplete() {
    	return this.goal.isComplete();
    }
    
    //true if game continue false otherwise
    /**
     * 
     * @return boolean - true if the game continues and false if otherwise
     * false - player dies
     */
    public boolean game() {
    	//System.out.println("1");
    	//kill player if they moved toward an enemy
        if (this.killPlayer() == true) {
        	return false;
        }
    	//System.out.println("2");

        //if no enemy then pick up whatever is on the floor
        player.interact();
        if (player2 != null) {
        	player2.interact();
        }
    	//System.out.println("3");

        //move the enemy 
        this.moveEnemies(); 
    	//System.out.println("4");

        //kill the player if the enemy has moved towards the player
        if (this.killPlayer() == true) {
        	return false;
        }
    	//System.out.println("5");

        
        // check if the is a boulder switch goal has been reached 
        // and then check to see if player should move through a portal
        this.interact();
    	//System.out.println("6");
    	
    	//System.out.println("GOAL COMPLETED:" + this.goal.isComplete());
        
		return true;
    }
   
    /**
     * 
     * @return width - of the dungeon
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * 
     * @return height - of the dungeon
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * 
     * @return player - the player1 of the dungeon to interact with all the entities
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * 
     * @return player2 - the player2 of the dungeon to interact with all the entities
     * can be null if multiplayer is not select
     */
    public Player getPlayer2() {
        return player2;
    }
    
    /**
     * Sets the player of the dungeon
     * @param player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    /**
     * Sets the player2 of the dungeon (if chosen as multiplayer)
     * @param player2
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

	/** 
	 * adds entities to the dungeon
	 * @param entity
	 */
    public void addEntity(Entity entity) {
        entities.add(entity);
    }
    
    /**
     * deleted an object other from the dungeon if it is of type entity
     * and if it is at the same x and y coordinate as the parameters
     * @param other
     * @param x
     * @param y
     * @return nothing
     */
    public void delEntity(Object other, int x, int y) {
    	if (other == null) {
    		return;
    	}
    	//ss
    
    	
    	int index = this.entities.indexOf(other);
    	
    	
//    	System.out.println(index + " " + other.getClass());
//    	System.out.println(this.entities.contains(other));
    	 if (index == -1) {
    		 return;
    	 }else {
    		 this.entities.remove(index);
    	 }
    	
    	
//    	for (int i = 0; i < this.entities.size(); i++) {
//    		Entity e = this.entities.get(i);
//    		if (e == null) continue;
//    		
//    		if (e.getClass() == other.getClass() 
//    				&& e.getX() == x && e.getY() == y) {
//    			this.entities.remove(i);
//    			return;
//    			
//			}
//		}
    }
    
    
    /**
     * deleted trreasure if the same x and y coordinate as the params
     * @param x
     * @param y
     */
    public void delTreasure(int x, int y) {
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Treasure.class 
    				&& e.getX() == x && e.getY() == y) {
    			this.entities.remove(i);
    			e.visible().set(false);
    			return;
			}
		}
    	
    }
    
    /**
     * deleted enemy if the same x and y coordinate as the params
     * @param x
     * @param y
     */
    public void delEnemy(int x, int y) {
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Enemy.class 
    				&& e.getX() == x && e.getY() == y) {
    			this.entities.remove(i);
    			e.visible().set(false);
    			return;
			}
		}
    }
    
    /**
     * deleted potion if the same x and y coordinate as the params
     * @param x
     * @param y
     */
    public void delPotion(int x, int y) {
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Potion.class 
    				&& e.getX() == x && e.getY() == y) {
    			this.entities.remove(i);
    			e.visible().set(false);
    			return;
			}
		}
    }
    /**
     * deleted sword if the same x and y coordinate as the params
     * @param x
     * @param y
     */
    public void delSword(int x, int y) {
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Sword.class 
    				&& e.getX() == x && e.getY() == y) {
    			this.entities.remove(i);
    			e.visible().set(false);
    			return;
			}
		}
    }
    
    /**
     * deleted sword if the same x and y coordinate as the params
     * @param x
     * @param y
     */
    public void delKey(int x, int y) {
    	//the id doesnt matter as no keys will be on the same box
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Key.class 
    				&& e.getX() == x && e.getY() == y) {
    			this.entities.remove(i);
    			e.visible().set(false);
    			return;
			}
		}
    }
    
    /**
     * moves all enemies within the entity list of the dungeon
     */
    public void moveEnemies() {
    	//System.out.println("Move all enemies:");
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Enemy.class) {
    			Enemy enemy = (Enemy) e;
    			enemy.move();
    			
			}
		}
    }
    
    /**
     * kills player if on the same tile as an enemy and not invincible
     * @return boolean - based on whether or not a player has been killed within the dungeon or not
     */
    public boolean killPlayer() {
    	boolean dead = false;
    	if (player2 != null) {
    		for (int i = 0; i < this.entities.size(); i++) {
        		Entity e = this.entities.get(i);
        		if (e == null) continue;
        		if (e.getClass() == Enemy.class) {
        			Enemy enemy = (Enemy) e;
        			if (this.getPlayer2().getX() == enemy.getX() &&
        					this.getPlayer2().getY() == enemy.getY() 
        					&& this.getPlayer2().checkInvulnerability() == false) {
        				this.setPlayer2(null);
        				System.out.println("PLAYER 2 DEAD");
        				for (int j = 0; j < this.entities.size(); j++) {
        		    		Entity f = this.entities.get(j);
        		    		if (f == null) continue;
        		    		if (f.getClass() == Player.class 
        		    				&& f.getX() == enemy.getX() && f.getY() == enemy.getY()) {
        		    			this.entities.remove(i);
        		    			f.visible().set(false);
        					}
        				}
        				dead = true;
        			}    			
    			}
    		}
    	}
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Enemy.class) {
    			Enemy enemy = (Enemy) e;
    			if (this.getPlayer().getX() == enemy.getX() &&
    					this.getPlayer().getY() == enemy.getY() 
    					&& this.getPlayer().checkInvulnerability() == false) {
    				System.out.println("DEAD PLAYER 1");
    				for (int j = 0; j < this.entities.size(); j++) {
    		    		Entity f = this.entities.get(j);
    		    		if (f == null) continue;
    		    		if (f.getClass() == Player.class 
    		    				&& f.getX() == enemy.getX() && f.getY() == enemy.getY()) {
    		    			this.entities.remove(i);
    		    			f.visible().set(false);
    					}
    				}
    				dead = true;
    			}    			
			}
		}
    	return dead;
    }
    

    
    
    /**
     * Gets an entity that is equal to object other and in the 
     * same coordinates as the x and y coordinates given as param
     * @param other
     * @param x
     * @param y
     * @return
     */
    public Entity getEntity(Object other, int x, int y) {
    	if (other == null) {
    		return null;
    	}
    	
    	int index = this.entities.indexOf(other);
    	
    	 if (index == -1) {
    		 return null;
    	 }else {
    		 return this.entities.get(index);
    	 }
    	 	
    }
    
    /**
     * implements getentity but for a treasure entity
     * @param x
     * @param y
     * @return
     */
    public Treasure getTreasure(int x, int y) {
    	Treasure treasure = new Treasure(x,y);
    	return (Treasure) getEntity(treasure,x,y);
    }
    /**
     * implements getentity but for a sword entity
     * @param x
     * @param y
     * @return
     */
    public Sword getSword(int x, int y) {
    	Sword sword = new Sword(x,y);
    	return (Sword) getEntity(sword,x,y);
    }
    /**
     * implements getentity but for a key entity
     * @param x
     * @param y
     * @return
     */
    public Key getKey(int x, int y) {
    	//id doesnt matter as explained before
    	Key key = new Key(x,y, 1);
    	return (Key) getEntity(key,x,y);
    	
    }
    /**
     * implements getentity but for a Door entity
     * @param x
     * @param y
     * @return
     */
    public Door getDoor(int x, int y) {
    	Door door = new Door(x, y, 1);
    	return (Door) getEntity(door,x,y);
    }
    
    /**
     * Gets the doorId through the x and y params by using the func above
     * @param x
     * @param y
     * @return
     */
    public int getDoorID(int x, int y) {
    	Door door = getDoor(x, y);
    	return door.getDoorID();
    }
    
    /**
     * implements getentity but for a potion entity
     * @param x
     * @param y
     * @return
     */
    public Potion getPotion(int x, int y) {
    	Potion potion = new Potion( x,y);
    	return (Potion) getEntity(potion,x,y);
    }
    
    
    /**
     * implements getentity but for a boulder entity
     * @param x
     * @param y
     * @return
     */
    public Boulder getBoulder(int x, int y) {
    	Boulder boulder = new Boulder(this,x,y);
    	return (Boulder) getEntity(boulder,x,y);
    }
    
    /**
     * 
     * @return boolean - based on whether or not all floor switches are active or not
     */
    public boolean BoulderSwitch() {
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == FloorSwitch.class) {
				FloorSwitch floorswitch = (FloorSwitch) e;
				if (floorswitch.checkBoulderSwitch() == false) {
					return false;
				}
			}
		}
    	return true;
    }
    
    /**
     * this is the dungon tick which checks if the player should be teleported 
     * and whether or not all goals have been completed
     */
    public void interact() {
    	this.teleport();
    	if (this.goal == null) {
    		return;
    	}
        if (this.BoulderSwitch() == true) {
        	this.goal.setBoulderComplete();
        }
        if (this.checkExitGoal() == true) {
        	this.goal.setExitComplete();
        }
        if (this.checkEnemyGoal() == true) {
        	this.goal.setEnemiesComplete();
        }
        if (this.checkTreasureGoal() == true) {
        	this.goal.setTreasureComplete();
        }

    }
    
    /**
     * teleports a player if they are on the same block as a teleport entity
     * could be implemented as an observer but had little time to implement 
     */
    public void teleport() {
    	
    	
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Portal.class) {
				Portal portal = (Portal) e;
				if (player.getX() == portal.getX() 
					&& player.getY() == portal.getY()) {
//					System.out.println("JFABNFKAJSNFKA");
					//get coords of next portal
					Point next_portal = this.nextPortal(portal);
					System.out.println(next_portal);
					if (next_portal == null) {
						return;
					}
					
					int x = (int) next_portal.getX();
					int y = (int) next_portal.getY();
					System.out.println(x + " " + y);
					this.player.teleport(x,y);
					break;

					//move player to the coords of the next portal
					
				}if (player2 != null) {
					if (player2.getX() == portal.getX() 
					&& player2.getY() == portal.getY()) {
//					System.out.println("JFABNFKAJSNFKA");
					//get coords of next portal
					Point next_portal = this.nextPortal(portal);
					System.out.println(next_portal);
					if (next_portal == null) {
						return;
					}
					
					int x = (int) next_portal.getX();
					int y = (int) next_portal.getY();
					System.out.println(x + " " + y);
					this.player2.teleport(x,y);
					break;

					//move player to the coords of the next portal
					
					}
				}
			}
		}
    }
    
    /**
     * finds the correspoing portal for the portal given as a paremeter
     * does this by checking protal ids
     * @param portal
     * @return the portal or null otherwise
     */
    public Point nextPortal(Portal portal) {
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Portal.class) {
				Portal portal_next = (Portal) e;
				if (portal_next.getId() == portal.getId() && !portal_next.equals(portal)) {
					return new Point(portal_next.getX(), portal_next.getY());
				}
    		}
		}
		return null;
    }
    
    /**
     * 
     * @return boolean - based on whether or not all switches are activates
     */
    public boolean checkBoulderSwitch() {
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == FloorSwitch.class) {
				FloorSwitch floorswitch = (FloorSwitch) e;
				if (!floorswitch.checkSwitch()) {
					return false;
				}
			}
		}
    	return true;
    }
    
    /**
     * 
     * @return - boolean based on wehter there are any living enemies
     */
    public boolean checkEnemyGoal() {

    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Enemy.class) {
				return false;
			}
		}
    	return true;
    }
    
    /**
     * 
     * @return boolean - whethere or not there are any more trasures or they are all in the palyers inv
     */
    public boolean checkTreasureGoal() {
    	if (this.player2 != null) {
    		if (this.player2.checkInvTreasure() == true 
    				|| this.player.checkInvTreasure() == true) {
    			for (int i = 0; i < this.entities.size(); i++) {
            		Entity e = this.entities.get(i);
            		if (e == null) continue;
            		if (e.getClass() == Treasure.class) {
        				return false;
        			}
        		}
        		return true;
    			
    		}
    	}
    	if(this.player.checkInvTreasure() == true) {
    		for (int i = 0; i < this.entities.size(); i++) {
        		Entity e = this.entities.get(i);
        		if (e == null) continue;
        		if (e.getClass() == Treasure.class) {
    				return false;
    			}
    		}
    		return true;
    	}
    	return false;
    }
    
    /**
     * 
     * @return boolean - based on wehtehr the player has entered the exit entity and completed the goal
     */
    public boolean checkExitGoal() {
    	if (this.player2 != null) {
    		for (int i = 0; i < this.entities.size(); i++) {
        		Entity e = this.entities.get(i);
        		if (e == null) continue;
        		if (e.getClass() == Exit.class) {
        			Exit exit = (Exit) e;
    				if (player2.getX() == exit.getX() 
    					&& player2.getY() == exit.getY()) {
    					return true;
    				}
    			}
    		}
    	}
    	
    	for (int i = 0; i < this.entities.size(); i++) {
    		Entity e = this.entities.get(i);
    		if (e == null) continue;
    		if (e.getClass() == Exit.class) {
    			Exit exit = (Exit) e;
				if (player.getX() == exit.getX() 
					&& player.getY() == exit.getY()) {
					return true;
				}
			}
		}
    	return false;
    }
    
    
    
    
    
    /**
     * checks to see if an enetiy is there or not 
     * checks to see if the object and its x and y coordiantes are int hat specific location
     * @param other
     * @param x
     * @param y
     * @return boolean - based on wehtehr object is there
     */
    public boolean checkEntity(Object other, int x, int y) {
    	if (other == null) {
    		return false;
    	}
    	int index = this.entities.indexOf(other);
    	
    	
//    	System.out.println(index + " " + other.getClass());
//    	System.out.println(this.entities.contains(other));
    	 if (index == -1) {
    		 return false;
    	 }else {
    		 return true;
    	 }
//    	 
//    	for (int i = 0; i < this.entities.size(); i++) {
//    		Entity e = this.entities.get(i);
//    		if (e == null) continue;
//    		if (e.getClass() == other.getClass()) {
//    			if (e.getX() == x && e.getY() == y) {
//    				return true;
//    			}
//			}
//		}
//    	return false;
    }
    
    /** 
     * implementation of checkEntity function using enemy
     * @param x
     * @param y
     * @return boolean based on wehter enemy is there
     */
    public boolean checkEnemy(int x, int y) {
    	Enemy enemy = new Enemy(this, x, y);
    	return this.checkEntity(enemy, x, y);
    }
    
    /** 
     * implementation of checkEntity function using exit
     * @param x
     * @param y
     * @return boolean based on wehter exit is there
     */
    public boolean checkExit(int x, int y) {
    	Exit exit = new Exit(x, y);
    	return this.checkEntity(exit, x, y);
    }
    /** 
     * implementation of checkEntity function using wall
     * @param x
     * @param y
     * @return boolean based on wall enemy is there
     */
    
    public boolean checkWall(int x, int y) {
    	Wall wall = new Wall(x, y);
    	return this.checkEntity(wall, x, y);
    }
    /** 
     * implementation of checkEntity function using enemy
     * @param x
     * @param y
     * @return boolean based on wehter enemy is there
     */
    public boolean checkBoulder(int x, int y) {
    	Boulder boulder = new Boulder(this, x, y);
    	return this.checkEntity(boulder, x, y);
    }
    /** 
     * implementation of checkEntity function using door
     * @param x
     * @param y
     * @return boolean based on wehter door is there
     */
    public boolean checkDoor(int x, int y) {
    	//door id dont matter only coordinated do
    	Door door = new Door(x, y, 1);
    	return this.checkEntity(door, x, y);
    }
    
    /** 
     * implementation of checkEntity function using treasure
     * @param x
     * @param y
     * @return boolean based on wehter treasure is there
     */
    
    public boolean checkTreasure(int x, int y) {
    	Treasure treasure = new Treasure(x, y);
    	return this.checkEntity(treasure, x,y);
    }
    
    /**
     * boolean for if a key exists at that spot
     * @param x
     * @param y
     * @return boolean - based on wthere or not there is a key there 
     */
    public boolean checkKey(int x, int y) {
    	//the equals function only cares about x and y coordinates 
    	//as u cant have 2 keys ontop of one another
    	Key key = new Key(x,y, 1);
    	return this.checkEntity(key, x, y);
//    	for (int i = 0; i < this.entities.size(); i++) {
//    		Entity e = this.entities.get(i);
//    		if (e == null) continue;
//    		if (e.getClass() == Key.class) {
//    			if (e.getX() == x && e.getY() == y) {
//    				return true;
//    			}
//			}
//		}
//    	return false;
    }
    /** 
     * implementation of checkEntity function using sword
     * @param x
     * @param y
     * @return boolean based on wehter sword is there
     */
    public boolean checkSword(int x, int y) {
    	Sword sword = new Sword(x, y);
    	return this.checkEntity(sword, x,y);
    }
    /** 
     * implementation of checkEntity function using potion
     * @param x
     * @param y
     * @return boolean based on wehter potion is there
     */
    public boolean checkPotion(int x, int y) {
    	Potion potion = new Potion( x, y);
    	return this.checkEntity(potion, x, y);
    }

	
	
    
    

}
