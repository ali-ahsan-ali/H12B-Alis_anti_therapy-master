package unsw.dungeon;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


public class WallTest {
    private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 1, 1);  
    private Wall wall;
    
    
	protected void setUp()  {
	}
	
	/*
	 *  Player gets blocked when interacting with wall and cannot go through.(1)
		Enemies get blocked when interacting with wall and cannot go through.(2)
		Boulders get blocked when interacting with a wall and cannot go through(3)

	 */
	@Test
	public void TestWallPlayer() {
        dungeon.setPlayer(player);
        
        wall = new Wall(1,2);
        Entity entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(1,0);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(0,1);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(2,1);
        entity = wall;
        dungeon.addEntity(entity);
        
        //try move up down left and right and see if the position has changed
        player.moveRight();
        assert(player.getX() == 1);
        assert(player.getY() == 1);
        
        player.moveLeft();
        assert(player.getX() == 1);
        assert(player.getY() == 1);
        
        player.moveUp();
        assert(player.getX() == 1);
        assert(player.getY() == 1);
        
        player.moveDown();
        assert(player.getX() == 1);
        assert(player.getY() == 1);
                
	}
	
	@Test
	public void TestWallEnemy() {
        dungeon.setPlayer(player);
		Enemy enemy = new Enemy(dungeon,1,1);
        Entity entity = enemy;
        dungeon.addEntity(entity);
        
        wall = new Wall(1,2);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(1,0);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(0,1);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(2,1);
        entity = wall;
        dungeon.addEntity(entity);
        
        //try move up down left and right and see if the position has changed
        enemy.moveRight();
        assert(enemy.getX() == 1);
        assert(enemy.getY() == 1);
        
        enemy.moveLeft();
        assert(enemy.getX() == 1);
        assert(enemy.getY() == 1);
        
        enemy.moveUp();
        assert(enemy.getX() == 1);
        assert(enemy.getY() == 1);
        
        enemy.moveDown();
        assert(enemy.getX() == 1);
        assert(enemy.getY() == 1);
                
	}
	
	@Test
	public void TestWallBoulder() {
		Boulder boulder = new Boulder(dungeon,1,1);
        Entity entity = boulder;
        dungeon.addEntity(entity);
        
        wall = new Wall(1,2);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(1,0);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(0,1);
        entity = wall;
        dungeon.addEntity(entity);
        wall = new Wall(2,1);
        entity = wall;
        dungeon.addEntity(entity);
        
        //try move up down left and right and see if the position has changed
        boulder.moveRight();
        assert(boulder.getX() == 1);
        assert(boulder.getY() == 1);
        
        boulder.moveLeft();
        assert(boulder.getX() == 1);
        assert(boulder.getY() == 1);
        
        boulder.moveUp();
        assert(boulder.getX() == 1);
        assert(boulder.getY() == 1);
        
        boulder.moveDown();
        assert(boulder.getX() == 1);
        assert(boulder.getY() == 1);
                
	}

	protected void tearDown() {
	}

}
