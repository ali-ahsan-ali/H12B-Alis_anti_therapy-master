package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BoulderTest {
    Dungeon dungeon = new Dungeon(10, 10);
	Boulder boulder; 
    Player player = new Player(dungeon, 5, 5);  
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	/* 
	 *  Player cannot move to the same tile as the boulder(1)
		Player should be able to move a boulder adjacently one square at a time(1)
		If there is a boulder or wall behind a boulder that the player is pushing, 
		movement is obstructed and the boulder cannot be moved (2 and 3)

	 */
	
	@Test
	public void TestMoveOntoBoulder() {
		// for the first user acceptance story, moving from any direction onto a boulder wont allow u to 
		// enter the same tile as the boulder
		dungeon.setPlayer(player);
		Entity entity = boulder;
		dungeon.addEntity(entity);
		
		boulder = new Boulder(dungeon, 4,5);
        entity = boulder;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveLeft();
  		
  		assert(player.getX() == 4);
  		assert(player.getY() == 5);
  		assert(boulder.getX() == 3);
  		assert(boulder.getY() == 5);
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder = new Boulder(dungeon, 6,5);
        entity = boulder;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveRight();
  		
  		assert(player.getX() == 6);
  		assert(player.getY() == 5);
  		assert(boulder.getX() == 7);
  		assert(boulder.getY() == 5);
  		
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder = new Boulder(dungeon, 5,4);
        entity = boulder;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveUp();
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 4);
  		assert(boulder.getX() == 5);
  		assert(boulder.getY() == 3);
  		
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder = new Boulder(dungeon, 5,6);
        entity = boulder;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveDown();
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 6);
  		assert(boulder.getX() == 5);
  		assert(boulder.getY() == 7);
  		
	}
	
	@Test
	void TestMoveOntoBoulderWithWall() {
		
		//these are the exact same tests as above but adding walls to the other sie
		//will cope paste and do this again but change walls to boulders
		Wall wall;
		dungeon.setPlayer(player);
		Entity entity = boulder;
		dungeon.addEntity(entity);
		
		boulder = new Boulder(dungeon, 4,5);
        entity = boulder;
        dungeon.addEntity(entity);
        wall = new Wall(3,5);
        entity = wall;
        dungeon.addEntity(entity);
       
        
        //try move onto boulder
  		player.moveLeft();
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder.getX() == 4);
  		assert(boulder.getY() == 5);
  		
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder = new Boulder(dungeon, 6,5);
        entity = boulder;
        dungeon.addEntity(entity);
        wall = new Wall(7,5);
        entity = wall;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveRight();
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder.getX() == 6);
  		assert(boulder.getY() == 5);
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder = new Boulder(dungeon, 5,4);
        entity = boulder;
        dungeon.addEntity(entity);
        wall = new Wall(5,3);
        entity = wall;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveUp();
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder.getX() == 5);
  		assert(boulder.getY() == 4);
  		
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder = new Boulder(dungeon, 5,6);
        entity = boulder;
        dungeon.addEntity(entity);
        wall = new Wall(5,7);
        entity = wall;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveDown();
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder.getX() == 5);
  		assert(boulder.getY() == 6);
  		
  		
		
	}
	
	@Test
	void TestMoveOntoBoulderWithBoulder() {
		
		//these are the exact same tests as above but adding walls to the other sie
		//will cope paste and do this again but change walls to boulders
		dungeon.setPlayer(player);
		Entity entity = boulder;
		dungeon.addEntity(entity);
		
		Boulder boulder1 = new Boulder(dungeon, 4,5);
        entity = boulder1;
        dungeon.addEntity(entity);
        Boulder boulder2 = new Boulder(dungeon, 3,5);
        entity = boulder2;
        dungeon.addEntity(entity);
       
        
        //try move onto boulder
  		player.moveLeft();
  		System.out.print(player.getX());
  		System.out.println(player.getY());
  		System.out.print(boulder1.getX());
  		System.out.println(boulder1.getY());
  		System.out.print(boulder2.getX());
  		System.out.println(boulder2.getY());
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder1.getX() == 4);
  		assert(boulder1.getY() == 5);
  		assert(boulder2.getX() == 3);
  		assert(boulder2.getY() == 5);
  		
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder1 = new Boulder(dungeon, 6,5);
        entity = boulder1;
        dungeon.addEntity(entity);
        boulder2 = new Boulder(dungeon, 7,5);
        entity = boulder2;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveRight();
  		System.out.println(player.getX());
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder1.getX() == 6);
  		assert(boulder1.getY() == 5);
  		assert(boulder2.getX() == 7);
  		assert(boulder2.getY() == 5);
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder1 = new Boulder(dungeon, 5,4);
        entity = boulder1;
        dungeon.addEntity(entity);
        boulder2 = new Boulder(dungeon, 5,3);
        entity = boulder2;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveUp();
  		System.out.println(player.getX());
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder1.getX() == 5);
  		assert(boulder1.getY() == 4);
  		assert(boulder2.getX() == 5);
  		assert(boulder2.getY() == 3);
  		
  		
  		player = new Player(dungeon, 5, 5);
		dungeon.setPlayer(player);
  		boulder1 = new Boulder(dungeon, 5,6);
        entity = boulder1;
        dungeon.addEntity(entity);
        boulder2 = new Boulder(dungeon, 5,7);
        entity = boulder2;
        dungeon.addEntity(entity);
        
        //try move onto boulder
  		player.moveDown();
  		System.out.println(player.getX());
  		
  		assert(player.getX() == 5);
  		assert(player.getY() == 5);
  		assert(boulder1.getX() == 5);
  		assert(boulder1.getY() == 6);
  		assert(boulder2.getX() == 5);
  		assert(boulder2.getY() == 7);
  		
  		
		
	}
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	

}
