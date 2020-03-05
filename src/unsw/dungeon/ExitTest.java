package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExitTest {
    private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 5, 5);  


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	/**
	 * 	When the player goes through exit, there is an indication that the puzzle 
	 * has been completed if it is the only goal required (ZHANG) (1)
		When the player goes through exit, there is an indication that the puzzle has been completed only if 
		the user has completed the other required.(ZHANG) (1)
		No other object has any interactions when on top of an exit. (2)
	 * @throws Exception
	 */
	
	@Test
	public void TextExitPlayer() {
		dungeon.setPlayer(player);
		Exit exit = new Exit(6,5);
		Entity entity = exit;
		dungeon.addEntity(entity);
		
		assert(dungeon.checkExitGoal() == false);
		player.moveRight();
		assert(dungeon.checkExitGoal() == true);	
		
	}
	
	@Test
	public void TestNoOtherInteractions() {
		dungeon.setPlayer(player);
		Exit exit = new Exit(6,5);
		Entity entity = exit;
		dungeon.addEntity(entity);
		
		Enemy enemy = new Enemy(dungeon, 6,5);
		entity = enemy;
		dungeon.addEntity(entity);
		assert(dungeon.checkExitGoal() == false);

		Boulder boulder = new Boulder(dungeon, 6,5);
		entity = boulder;
		dungeon.addEntity(entity);
		assert(dungeon.checkExitGoal() == false);
		
		Potion potion = new Potion( 6,5);
		entity = potion;
		dungeon.addEntity(entity);
		assert(dungeon.checkExitGoal() == false);
		
		FloorSwitch floorswitch = new FloorSwitch(dungeon, 6,5);
		entity = floorswitch;
		dungeon.addEntity(entity);
		assert(dungeon.checkExitGoal() == false);
		
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	
}
