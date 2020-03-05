package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
	private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 5, 5); 

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
	 * A player can only move up, down, left and right(1)
	 * 
	 * THE BELOW IS ALL TESTED IN OTHER FILES AND USE CASES
		Player is bound by the entity rule requirements of other entities and tiles
		E.g Cannot walk through walls as they block movement of other players
		E.g Cannot walk through boulders

	 */
	@Test
	void TestMovement() {
		dungeon.setPlayer(player);
		
		player.moveRight();
  		assert(player.getX() == 6);
  		assert(player.getY() == 5);
  		
  		player.moveDown();
  		assert(player.getX() == 6);
  		assert(player.getY() == 6);
  		
  		player.moveLeft();
  		assert(player.getX() == 5);
  		assert(player.getY() == 6);
  		
  		player.moveUp();
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);

		
	}

}
