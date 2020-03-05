package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FloorSwitchTest {
	Dungeon dungeon = new Dungeon(10, 10);
	Boulder boulder; 
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
	 *  Switch’s status acts as a toggle and remains as untriggered (1)
		When a boulder is on a trigger tile it become triggered (1)
		When all switch entities within the dungeon are triggered it complete the goal of the dungeon
		Completes dungeon if that's the only goal(this is more of the other epic story and not to be tested here)

	 */
	@Test
	void test() {
		dungeon.setPlayer(player);
		Entity entity;
		FloorSwitch floorswitch;
		floorswitch = new FloorSwitch(dungeon, 1, 1);
		entity  = floorswitch;
		dungeon.addEntity(entity);
		floorswitch = new FloorSwitch(dungeon, 2, 1);
		entity  = floorswitch;
		dungeon.addEntity(entity);
		floorswitch = new FloorSwitch(dungeon, 3, 1);
		entity  = floorswitch;
		dungeon.addEntity(entity);
		floorswitch = new FloorSwitch(dungeon, 1, 2);
		entity  = floorswitch;
		dungeon.addEntity(entity);
		floorswitch = new FloorSwitch(dungeon, 1, 3);
		entity  = floorswitch;
		dungeon.addEntity(entity);
		assert(dungeon.BoulderSwitch() == false);
		boulder = new Boulder(dungeon, 1, 1);
	    entity = boulder;
	    dungeon.addEntity(entity);
	    assert(dungeon.BoulderSwitch() == false);
		boulder = new Boulder(dungeon, 2, 1);
	    entity = boulder;
	    dungeon.addEntity(entity);
	    assert(dungeon.BoulderSwitch() == false);
		boulder = new Boulder(dungeon, 3, 1);
	    entity = boulder;
	    dungeon.addEntity(entity);
	    assert(dungeon.BoulderSwitch() == false);
		boulder = new Boulder(dungeon, 1, 2);
	    entity = boulder;
	    dungeon.addEntity(entity);
	    assert(dungeon.BoulderSwitch() == false);
		boulder = new Boulder(dungeon, 1, 3);
	    entity = boulder;
	    dungeon.addEntity(entity);
	    assert(dungeon.BoulderSwitch() == true);

	}

}
