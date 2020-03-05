package unsw.dungeon;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class SwordTest{
    private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 0, 1);  
    private Sword sword = new Sword(1, 1);
    private Enemy enemy;

	protected void setUp() throws Exception {
	}

	protected void tearDown() throws Exception {
	}
	/*
	 *  - If the player is on top of a tile with a sword and does not yet carry a sword then the sword is picked up (1)
		- If the user already holding a sword, sword remains on the tile.(2)
		- Sword should have a usage counter of 5 (3)
		- Sword should disappear from player’s inventory when usage counter hits 0. (3)
		- When a sword sword usage counter should go down by 1 (3)
		- Enemies die (removed from the dungeon permanently) when struck by a player with a sword one tile away from the enemy (4)

	 */
	
	@Test
	 public void TestSwordPickup() {
		dungeon.setPlayer(player);
		Entity entity = sword;
		dungeon.addEntity(entity);
		
		//player has no sword 
		assert(player.checkSword() == false);
		
		//move to sword
		player.moveRight();
		
		//pick up the sword
		player.pickupSword();
		
		//player has a sword 
		assert(player.checkSword() == true);
		//sword is picked up
		assert(dungeon.checkSword(1, 1) == false);
	}
	
	@Test
	 public void TestSwordPickupIfHaveSword() {
		dungeon.setPlayer(player);
		Entity entity = sword;
		dungeon.addEntity(entity);
		
	    Sword sword1 = new Sword(2, 1);
	    entity = sword1;
		dungeon.addEntity(entity);
		
		//player has no sword 
		assert(player.checkSword() == false);
		
		//move to sword
		player.moveRight();
		
		//pick up the sword
		player.pickupSword();
		
		//player has a sword 
		assert(player.checkSword() == true);
		//sword is picked up
		assert(dungeon.checkSword(1, 1) == false);
		
		//move to sword
		player.moveRight();
		
		//pick up the sword
		player.pickupSword();
		
		//player has a sword 
		assert(player.checkSword() == true);
		//sword is NOT picked up and still on the floor
		assert(dungeon.checkSword(2, 1) == true);
				
	}
	
	@Test
	 public void TestSwordUsage() {
		dungeon.setPlayer(player);
		Entity entity = sword;
		dungeon.addEntity(entity);
		
	    
		
		//player has no sword 
		assert(player.checkSword() == false);
		
		//move to sword
		player.moveRight();
		//pick up the sword
		player.pickupSword();
		//player has a sword 
		assert(player.checkSword() == true);
		//sword is picked up
		assert(dungeon.checkSword(1, 1) == false);
		
		System.out.println("ASFasdasda");
		player.useSword();
		assert(sword.getSwingCount() == 4);
		assert(sword.check_sword() == false);
		player.useSword();
		assert(sword.getSwingCount() == 3);
		assert(sword.check_sword() == false);
		player.useSword();
		assert(sword.getSwingCount() == 2);
		assert(sword.check_sword() == false);
		player.useSword();
		assert(sword.getSwingCount() == 1);
		assert(sword.check_sword() == false);
		player.useSword();
		assert(sword.getSwingCount() == 0);
		assert(sword.check_sword() == true);
		
		//CHECK TO SEE IF SWORD IS NOT IN INV
		assert(player.checkSword() == false);
				
	}
	
	
	
	@Test
	 public void TestEnemiesDie() {
		dungeon.setPlayer(player);
		Entity entity = sword;
		dungeon.addEntity(entity);
		
		//pick up the sword
		player.moveRight();
		player.pickupSword();
		
		//add an enemy in each direction of the player and swing to see if they die
		//right
		enemy = new Enemy(dungeon, 2,1);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(2, 1) == true);
		player.useSword();
		assert(dungeon.checkEnemy(2, 1) == false);


		//add an enemy in each direction of the player and swing to see if they die
		//left
		enemy = new Enemy(dungeon, 0,1);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(0, 1) == true);
		player.useSword();
		assert(dungeon.checkEnemy(0, 1) == false);
		
		//add an enemy in each direction of the player and swing to see if they die
		//up
		enemy = new Enemy(dungeon, 1,2);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(1, 2) == true);
		player.useSword();
		assert(dungeon.checkEnemy(1, 2) == false);
		
		
		//add an enemy in each direction of the player and swing to see if they die
		//down
		enemy = new Enemy(dungeon, 1,0);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(1, 0) == true);
		player.useSword();
		assert(dungeon.checkEnemy(1, 0) == false);
		
		
		//add an enemy in each direction of the player and swing to see if they die
		//up and right
		enemy = new Enemy(dungeon, 2,2);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(2, 2) == true);
		player.useSword();
		assert(dungeon.checkEnemy(2, 2) == false);
		
		
		//YOU USED THE SWORD 5 TIMES SO IM GNNA GIVE THE PLAYER ANOTHER ONE
		sword = new Sword(1,1);
		entity = sword;
		dungeon.addEntity(entity);
		player.pickupSword();
		
		
		//add an enemy in each direction of the player and swing to see if they die
		//up and left
		enemy = new Enemy(dungeon, 2,0);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(2, 0) == true);
		player.useSword();
		assert(dungeon.checkEnemy(2, 0) == false);

		//add an enemy in each direction of the player and swing to see if they die
		//down and left
		enemy = new Enemy(dungeon, 0,0);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(0, 0) == true);
		player.useSword();
		assert(dungeon.checkEnemy(0, 0) == false);
		
		//add an enemy in each direction of the player and swing to see if they die
		//down and right
		enemy = new Enemy(dungeon, 0,1);
		entity  = enemy;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkEnemy(0, 1) == true);
		player.useSword();
		assert(dungeon.checkEnemy(0, 1) == false);

		
	}

}
