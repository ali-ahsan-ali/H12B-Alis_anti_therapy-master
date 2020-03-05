package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DoorTest {
	Dungeon dungeon = new Dungeon(20, 20);
	Player player = new Player(dungeon, 10, 10);
	Key key1 = new Key(10, 11, 1);
	Key key2 = new Key(10, 11, 2);
	Door door1 = new Door(10, 12, 1);
	Boulder boulder = new Boulder(dungeon, 10, 9);
	
	
	
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
	
	/**For every door there is a corresponding key mapped so that there are no doors that cannot be unlocked.
 	Door gets opened by a player moving through the tile holding the door entity when holding the corresponding key.
 	Player can pass through a door when door is opened
 	Enemy can pass through a door when door is opened
 	Boulder can pass through a door when door is opened
 	Player gets blocked when interacting with and unopened door without a key and cannot go through.
 	Enemies get blocked when interacting with an  unopened door and cannot go through.
 	Boulders get blocked when interacting with an unopened door and cannot go through
 	A dungeon can contain up to 3 doors**/

	@Test
	void TestKeyOpensDoor() {
		player = new Player(dungeon, 10, 10);
		Entity entity;
		entity = key1;
		dungeon.addEntity(entity);
		entity = door1;
		dungeon.addEntity(entity);
		
		player.moveDown();
		player.pickupKey();
		assert(player.checkInvKey(key1.getKeyID()) == true);
		//unfinished, test for whether door gets opened
		player.moveDown();
		assert(player.getX() == 10 && player.getY() == 12);
		assert(door1.isDoorOpen().get());
		assert(player.checkInvKey(key1.getKeyID()) == false);
	}
	
	@Test
	void TestKeyInvalid() {
		player = new Player(dungeon, 10, 10);
		dungeon.setPlayer(player);
		Entity entity;
		entity = key2;
		dungeon.addEntity(entity);
		entity = door1;
		dungeon.addEntity(entity);
		
		System.out.println("THIS FUNCTION HERE RAT");
		player.moveDown();
		player.pickupKey();
		assert(player.checkInvKey(key2.getKeyID()) == true);
		assert(dungeon.checkDoor(10, 12));
		player.moveDown();
		System.out.println(player.getX() + " " + player.getY());
		assert(player.getX() == 10 && player.getY() == 11);
		
		assert(door1.isDoorOpen().get() == false);
		assert(player.checkInvKey(key2.getKeyID()) == true);
		
	}
	
	@Test
	void TestPassThrough() {
		player = new Player(dungeon, 10, 10);
		Entity entity;
		dungeon.setPlayer(player);
		entity = key1;
		dungeon.addEntity(entity);
		entity = door1;
		dungeon.addEntity(entity);
		
		
		//start moving towards key 
		player.moveDown();
		System.out.println(player.getX() + " " + player.getY());
		player.pickupKey();
		assert(player.checkInvKey(key1.getKeyID()) == true);
		
		//make tests and open door
		//checking if entities pass through opened door
		System.out.println("ABOUT TO MOVE TO DOOR");
		player.moveDown();
		System.out.println(player.getX() + " " + player.getY());
		assert(player.getX() == 10 && player.getY() == 12);
		assert(door1.isDoorOpen().get());
		assert(player.checkInvKey(key1.getKeyID()) == false);
		//player is on same tile as opened door
		player.moveDown();
		player.moveDown();
		player.moveDown();
		//player has gone past the door
		assert(player.getX() == 10 && player.getY() == 15);
		entity = boulder;
		dungeon.addEntity(entity);
		boulder.moveDown();
		boulder.moveDown();
		boulder.moveDown();
		//boulder is on the same tile as the door
		assert(boulder.getX() == 10 && boulder.getY() == 12);
		boulder.moveDown();
		boulder.moveDown();
		//boulder has gone past the door
		assert(boulder.getX() == 10 && boulder.getY() == 14);
		Enemy enemy = new Enemy(dungeon, 10, 8);
		entity = enemy;
		dungeon.addEntity(entity);
		enemy.moveDown();
		enemy.moveDown();
		enemy.moveDown();
		enemy.moveDown();
		//enemy is on the same tile as the door
		assert(enemy.getX() == 10 && enemy.getY() == 12);
		enemy.moveDown();
		//enemy has gone past the door
		assert(enemy.getX() == 10 && enemy.getY() == 13);
	}
	
	@Test
	public void TestUnpassable() {
		player = new Player(dungeon, 10, 10);
		dungeon.setPlayer(player);
		Entity entity;
		entity = key2;
		dungeon.addEntity(entity);
		entity = door1;
		dungeon.addEntity(entity);
		entity = boulder;
		dungeon.addEntity(entity);
		Enemy enemy = new Enemy(dungeon, 10, 8);
		entity = enemy;
		dungeon.addEntity(entity);
		//start moving towards key 
		player.moveDown();
		player.pickupKey();
		assert(player.checkInvKey(key2.getKeyID()) == true);
		player.moveDown();
		assert(player.getX() == 10 && player.getY() == 11);
		assert(!door1.isDoorOpen().get());
		assert(player.checkInvKey(key2.getKeyID()) == true);
	}
}

