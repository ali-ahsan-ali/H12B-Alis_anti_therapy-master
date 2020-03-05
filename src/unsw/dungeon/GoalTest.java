package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GoalTest {
	private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 0, 1); 
    private Exit exit = new Exit(1, 1); 

    static final int OR = 0;
    static final int AND = 1;
	
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
	 * If player is in the same tile as the exit entity and there is only the single goal of exiting the dungeon then the dungeon is complete
		If player is in the same tile as the exit entity and there are multiple other goals to the dungeon which are incomplete then the dungeon is incomplete
		If player is in the same tile as the exit entity then the dungeon is complete
		If an enemy is on top of the exit entity and the player enters that entity they die to the enemy(2)

	 */
	@Test
	public void testExitGoal() {
		
		Composite goal = new Composite(OR);
		dungeon.setGoal(goal);
		GoalLeafExit g = new GoalLeafExit(false);
		goal.add(g);
		
		assert(goal.isComplete() == false);
		
		dungeon.setPlayer(player);
		
		Entity entity = exit;
		dungeon.addEntity(entity);
		
		assert(goal.isComplete() == false);
		//ontop of exit
		player.moveRight();
		dungeon.game();		
		assert(goal.isComplete() == true);
		
		
		
		
		Component goal2 = new GoalLeafEnemies( true);
		assert(goal2.isComplete() == true);
		
	}
	
	@Test
	public void testExitGoalEnemy() {
		
		Composite goal = new Composite(OR);
		dungeon.setGoal(goal);
		GoalLeafExit g = new GoalLeafExit( false);
		goal.add(g);
		
		assert(goal.isComplete() == false);
		
		dungeon.setPlayer(player);
		
		Entity entity = exit;
		dungeon.addEntity(entity);
		
		assert(goal.isComplete() == false);
		
		entity = new Enemy(dungeon, 1,1);
		dungeon.addEntity(entity);
		
		//enemy ontop of exit
		player.moveRight();
		//you die
		assert(dungeon.game() == false);	
		assert(goal.isComplete() == false);
		
		
		
		
		Component goal2 = new GoalLeafExit( true);
		assert(goal2.isComplete() == true);
	}
	/*
	 * 	If the goal is only treasure and the player holds all the treasure within their inventory then the goal is complete
		If player has picked up all treasure but there are incomplete conjunction goals, dungeon should be indicate completion.
	 */
	
	@Test
	public void testTreasureGoal() {
		
		Composite goal = new Composite(OR);
		dungeon.setGoal(goal);
		GoalLeafTreasure g = new GoalLeafTreasure( false);
		goal.add(g);
		
		assert(goal.isComplete() == false);
		
		dungeon.setPlayer(player);
		
		Entity entity = new Treasure(1,1);
		dungeon.addEntity(entity);
		entity = new Treasure(2,1);
		dungeon.addEntity(entity);
		assert(goal.isComplete() == false);

		//ontop of treasure1
		player.moveRight();
		dungeon.game();	
				
		assert(goal.isComplete() == false);
		
		//ontop of treasure2
		player.moveRight();
		dungeon.game();	
		assert(goal.isComplete() == true);
			
		
		
	
		
	}
	
	/*
	If there are no enemies alive and the goal of the dungeon is enemies then the dungeon is complete
	If there are no enemies alive and there is still another incomplete conjunction goal, dungeon should not be declared as completed.
	*/
	
	@Test
	public void testEnemyGoal() {
		
		Composite goal = new Composite(OR);
		dungeon.setGoal(goal);
		GoalLeafEnemies g = new GoalLeafEnemies( false);
		goal.add(g);
		
		assert(goal.isComplete() == false);
		
		dungeon.setPlayer(player);
		
		Entity entity = new Enemy(dungeon,4,1);
		dungeon.addEntity(entity);
		
		entity = new Sword(1,1);
		dungeon.addEntity(entity);
		
		assert(goal.isComplete() == false);
		//moev and pickup sword
		player.moveRight();
		dungeon.game();		
		assert(goal.isComplete() == false);
		// enemy is at 3,1 and u are at 1,1
		player.useSword();
		dungeon.game();		
		//enemy is at 2,1 and u are at 1,1
		assert(goal.isComplete() == false);
		player.useSword();
		dungeon.game();		
		//u swung and killed the enemy
		assert(goal.isComplete() == true);
		
		
		
		
	}
	
	@Test
	public void testBoulderSwitchGoal() {
		
		Composite goal = new Composite(OR);
		dungeon.setGoal(goal);
		GoalLeafBoulder g = new GoalLeafBoulder( false);
		goal.add(g);
		
		assert(goal.isComplete() == false);
		
		dungeon.setPlayer(player);
		
		Entity entity = new Boulder(dungeon,1,1);
		dungeon.addEntity(entity);
		entity = new FloorSwitch(dungeon,2,1);
		dungeon.addEntity(entity);
		
		assert(goal.isComplete() == false);
		//move boulder onto switch
		player.moveRight();
		dungeon.game();		
		assert(goal.isComplete() == true);
		
	}
	
	@Test
	public void TestBasicGoal() {
		Component goal1 = new GoalLeafEnemies( false);
		assert(goal1.isComplete() == false);	
		Component goal2 = new GoalLeafEnemies( true);
		assert(goal2.isComplete() == true);
		
	}
	
	/**{ "goal": "AND", "subgoals":
		  [ { "goal": "exit" },
		    { "goal": "OR", "subgoals":
		      [ {"goal": "enemies" },
		        {"goal": "treasure" }
		      ]
		    }
		  ]
		}**/
	
	// below is testing to make sure every combination of goals is checked to make sure
	// our isComplete function works perfectly in every and/or scenario for goals
	// therefore if we can prove that out game works for single goals each time and we prove our 
	// program is logically correct every time then there is no reason to test
	// every combination of goals (infinite or something) by moving player to kill enemy 
	// and stand on exit for example
	
	@Test
	public void TestComplexGoalANDTrue() {
		System.out.println("TESTING THIS");
		System.out.println("TESTING THIS TestComplexGoalANDTrue");
		GoalLeafExit exitGoal = new GoalLeafExit( true);
		GoalLeafEnemies enemies = new GoalLeafEnemies( true);
		GoalLeafTreasure treasure = new GoalLeafTreasure( false);
		
		Composite goals1 = new Composite(AND);
		goals1.add(exitGoal);
		System.out.println("EXIT ADDED ");
		goals1.isComplete();
		System.out.println("DONE \n\n\n\n\n ");
		assert (goals1.isComplete() == true);
		
		Composite goals2 = new Composite(OR);
		goals2.add(enemies);
		goals2.add(treasure);
		goals1.add(goals2);

		assert (goals1.isComplete() == true);
	}
	
	@Test
	public void TestComplexGoalANDFalse() {
		GoalLeafExit exitGoal = new GoalLeafExit( false);
		GoalLeafEnemies enemies = new GoalLeafEnemies( true);
		GoalLeafTreasure treasure = new GoalLeafTreasure( false);
		Composite goals1 = new Composite(AND);
		goals1.add(exitGoal);
		Composite goals2 = new Composite(OR);
		goals2.add(enemies);
		goals2.add(treasure);
		goals1.add(goals2);

		assert (goals1.isComplete() == false);
	}
	
	@Test
	public void TestComplexGoalORTrue() {
		GoalLeafExit exitGoal = new GoalLeafExit( false);
		GoalLeafEnemies enemies = new GoalLeafEnemies( true);
		GoalLeafTreasure treasure = new GoalLeafTreasure( true);
		Composite goals1 = new Composite(OR);
		goals1.add(exitGoal);
		Composite goals2 = new Composite(AND);
		goals1.add(goals2);
		goals2.add(enemies);
		goals2.add(treasure);
		assert (goals1.isComplete() == true);
	}
	
	@Test
	public void TestComplexGoalORFalse() {
		GoalLeafExit exitGoal = new GoalLeafExit( false);
		GoalLeafEnemies enemies = new GoalLeafEnemies( true);
		GoalLeafTreasure treasure = new GoalLeafTreasure( false);
		Composite goals1 = new Composite(OR);
		goals1.add(exitGoal);
		Composite goals2 = new Composite(AND);
		goals1.add(goals2);
		goals2.add(enemies);
		goals2.add(treasure);
		assert (goals1.isComplete() == false);
	}
	
	//(exit and treasure) OR (enemies and exit)
	@Test
	public void TestComplexGoalDuplicateTrue() {
		GoalLeafExit exit = new GoalLeafExit( true);
		GoalLeafEnemies enemies = new GoalLeafEnemies( false);
		GoalLeafTreasure treasure = new GoalLeafTreasure(true);
		//Component exit2 = new GoalLeaf("exit", true);
		Composite goals1 = new Composite(AND);
		goals1.add(exit);
		
		Composite goals2 = new Composite(OR);
		goals1.add(goals2);
		goals2.add(treasure);
		Composite goals3 = new Composite(AND);
		goals2.add(goals3);
		goals3.add(enemies);
		goals3.add(exit);
		assert (goals1.isComplete() == true);	
	}
	
	//(exit and treasure) OR (enemies and exit)
	@Test
	public void TestComplexGoalDuplicateFalse() {
		GoalLeafExit exit = new GoalLeafExit( true);
		GoalLeafEnemies enemies = new GoalLeafEnemies( false);
		GoalLeafTreasure treasure = new GoalLeafTreasure( false);
		//Component exit2 = new GoalLeaf("exit", true);
		Composite goals1 = new Composite(AND);
		goals1.add(exit);
		Composite goals2 = new Composite(OR);
		goals1.add(goals2);
		goals2.add(treasure);
		Composite goals3 = new Composite(AND);
		goals2.add(goals3);
		goals3.add(enemies);
		goals3.add(exit);
		assert (goals1.isComplete() == false);	
	}
	
}
