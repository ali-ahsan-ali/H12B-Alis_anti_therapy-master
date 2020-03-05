package unsw.dungeon;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;

public class EnemyTest{
    private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 0, 1);  
    private Enemy enemy;

	protected void setUp() throws Exception {
	}
	
	/*
	 *  Enemies constantly moves toward the player  (function 5 of TestPotion)
		Enemies will run away from the invinceable player (function 5 of TestPotion)
		If a player attempts to enter same tile as an enemy, the player dies. (1)

	 */

	protected void tearDown() throws Exception {
	}
	
	@Test
	public void TestEnemies() {
		dungeon.setPlayer(player);
		enemy = new Enemy(dungeon, 0,2);
		Entity entity = enemy;
		dungeon.addEntity(entity);
		
		//check player and enemy not on same square
		assert((player.getX() == enemy.getX() && player.getY() == enemy.getY()) == false);
		
		//when they one the same square player dies
		dungeon.game();
		assert((player.getX() == enemy.getX() && player.getY() == enemy.getY()) == true);
		assert(dungeon.killPlayer() == true);
	}

}
