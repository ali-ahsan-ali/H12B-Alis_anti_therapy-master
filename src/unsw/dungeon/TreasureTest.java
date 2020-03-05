package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TreasureTest {

	private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 0, 1);  
    private Treasure treasure = new Treasure(1, 1);
    
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
	 * 	These below goals are related to the other epic sotry and are repeated and will be done in another file
	 *  If I pick up all the treasure and it is the only goal required, puzzle is completed.
		If all treasure has been picked up but there are other goal conditions, game should continue.
		
		Once a player enters the tile the same as the treasure, it enters the inventory of the player(1)

	 */

	@Test
	void testTreasurePickup() {
		dungeon.setPlayer(player);
		Entity entity;
		entity = treasure;
		dungeon.addEntity(entity);
		
		assert(player.checkInvTreasure() == false);
		
		player.moveRight();
		assert(dungeon.game() == true);
		assert(player.checkInvTreasure() == true);
		
	}

}
