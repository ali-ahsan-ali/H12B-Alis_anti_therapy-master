package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KeyTest {
	Dungeon dungeon = new Dungeon(10, 10);
	Key key = new Key(5,6,1); 
    Player player = new Player(dungeon, 5, 5);  

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
	 *  Once player moves onto the square containing the key entity, key enters player’s inventory.(1)
		A specific key gets mapped to a specific door such that a key can only open a specific door. (this is tested in DoorTest)
		If player moves to a tile containing a key whilst holding a key, the new key is kept on the ground (2)
		If the player attempts to move on the same tile as a door when holding corresponding key, key should disappear. (tested in DoorTest)

	 */

	@Test
	void testSameTileAsKey() {
		dungeon.setPlayer(player);
		Entity entity = key;
		dungeon.addEntity(entity);
		
		assert(player.checkInvKey(1) == false);
		player.moveDown();
		player.pickupKey();
		assert(player.checkInvKey(1) == true);
	}
	
	@Test
	void testPickUpKey() {
		dungeon.setPlayer(player);
		Entity entity = key;
		dungeon.addEntity(entity);
		key = new Key(5,7,2);
		entity = key;
		dungeon.addEntity(entity);
		
		assert(player.checkInvKey(1) == false);
		player.moveDown();
		player.pickupKey();
		assert(player.checkInvKey(1) == true);
		
		player.moveDown();
		player.pickupKey();
		assert(player.checkInvKey(2) == false);
		
		//key 1 is deleted as picked up and key 2 stays
		assert(dungeon.checkKey(5, 6) == false);
		assert(dungeon.checkKey(5, 7) == true);
	}

}
