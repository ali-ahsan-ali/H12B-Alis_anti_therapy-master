package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PortalTest {
	Dungeon dungeon = new Dungeon(10, 10);
	Portal portal1 = new Portal(1,6,5); 
	Portal portal2 = new Portal(1,7,5); 
    Player player = new Player(dungeon, 9, 9);  
    Boulder boulder = new Boulder(dungeon, 5, 5);  

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/*
	 *  Only the player can access, the portal, i.e boulders and enemies have no interaction with the portal 
	 *  and simply stays on the tile or passes through them. (1 ,2 3)
		When a player lands on a portal tile, it should be transported to another corresponding portal mapped to the same (3) 
		Portal should always be accessible such that the player can go back and forth freely.(5)
		If there is an enemy on top of the portal then the player dies before being teleported (4)
	 */

	@Test
	void TestEnemy() {
		dungeon.setPlayer(player);
		Entity entity;
		entity = portal1;
		dungeon.addEntity(entity);
		entity = portal2;
		dungeon.addEntity(entity);
	    Enemy enemy = new Enemy(dungeon, 5, 5);  
		entity = enemy;
		dungeon.addEntity(entity);
		
		// enemy is at 5, 5 and portal is at 6,5 so if enemy moves right it should be on the portal and not move to 7,5 
		// the location of the other portal
		
		enemy.moveRight();
		assert(dungeon.game() == true);
		//the enemy has moved according to the game rules
		//but its impossible for it to have moved to 7 and 5 like the teleport would if it was to
		// be teleported
  		assert(enemy.getX() != 7 || enemy.getY() != 5);
		
	}
	
	@Test
	void TestBoulder() {
		dungeon.setPlayer(player);
		Entity entity;
		entity = portal1;
		dungeon.addEntity(entity);
		entity = portal2;
		dungeon.addEntity(entity);
		entity = boulder;
		dungeon.addEntity(entity);
		
		// enemy is at 5, 5 and portal is at 6,5 so if enemy moves right it should be on the portal and not move to 7,5 
		// the location of the other portal
		
		boulder.moveRight();
		assert(dungeon.game() == true);
		assert(boulder.getX() == 6);
  		assert(boulder.getY() == 5);
  		assert(boulder.getX() != 7 && boulder.getY() == 5);
		
	}
	
	@Test
	void TestPlayer() {
		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
		Entity entity;
		entity = portal1;
		dungeon.addEntity(entity);
		entity = portal2;
		dungeon.addEntity(entity);
		
		// enemy is at 5, 5 and portal is at 6,5 so if enemy moves right it should be on the portal and not move to 7,5 
		// the location of the other portal
		
		player.moveRight();
		assert(dungeon.game() == true);
		assert(player.getX() == 7);
  		assert(player.getY() == 5);
		
	}
	
	@Test
	void TestDieBeforeTeleport() {
		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
		Entity entity;
	    Enemy enemy = new Enemy(dungeon, 5, 5);  
		enemy = new Enemy(dungeon, 6, 5);
		entity = enemy;
		dungeon.addEntity(entity);
		entity = portal1;
		dungeon.addEntity(entity);
		entity = portal2;
		dungeon.addEntity(entity);
		
		// enemy is at 5, 5 and portal is at 6,5 so if enemy moves right it should be on the portal and not move to 7,5 
		// the location of the other portal
		
		player.moveRight();
		assert(dungeon.killPlayer() == true);
		assert(dungeon.game() == false);
		assert(player.getX() == 6);
  		assert(player.getY() == 5);
  		//player died before teleporting and is stuck at 6,5
		
	}
	
	@Test
	void TestTeleportBothWays() {
		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
		Entity entity;
		entity = portal1;
		dungeon.addEntity(entity);
		entity = portal2;
		dungeon.addEntity(entity);
		
		
		player.moveRight();
		assert(dungeon.game() == true);
		assert(player.getX() == 7);
  		assert(player.getY() == 5);
  		//teleported
  		
  		player.moveRight();
		assert(dungeon.game() == true);
		assert(player.getX() == 8);
  		assert(player.getY() == 5);
  		
  		//move to be telported again
  		player.moveLeft();
		assert(dungeon.game() == true);
		assert(player.getX() == 6);
  		assert(player.getY() == 5);
  		
  		//teleportation works both ways
		
	}


}
