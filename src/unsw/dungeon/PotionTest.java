package unsw.dungeon;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;


public class PotionTest{
    private Dungeon dungeon = new Dungeon(10, 10);
    private Player player = new Player(dungeon, 0, 1); 
	private Potion potion = new Potion(1, 1); 

    private Enemy enemy;

	@BeforeClass
	protected void setUp() throws Exception {
		//set up the dungeon where the player has not interacted with the potion
		Entity entity = potion;
        dungeon.setPlayer(player);
        dungeon.addEntity(entity);
		 
	}
	
	//Numbers next to the user acceptance criteria indicates which # function they have been completed in
	
	/* - If the player enters the same tile as the invincible potion entity the player is then invincible to enemies (1)
	   - If the player is already under the effect of invincibility whilst stepping over the tile with an 
	   	 invincibility potion it refreshes the invincibility timer (4)
	   - Colliding with an enemy whilst invinceable results in their immediate destruction (3)
        - The effect of the invincibility  potion only lasts a limited time(1)
    - Enemies run away from a player whilst they are under the effects of the invincibility potion (5)
    - If there is an enemy on top of the invincibility potion then the play dies before becoming invincible(2)
	 */
	
	 @Test
	 public void TestPotionTime() {
		 Entity entity = potion;
        dungeon.setPlayer(player);
        dungeon.addEntity(entity);
	        
		 System.out.println("~~~~~~Running test for TestPotionTime~~~~~~");
		 
		 assert (player.equals(dungeon.getPlayer()));
		 //check the player is invulnerable 
		 assert(potion.checkPotionFinished() == false);
		 assert(player.checkInvulnerability() == false);
		 
		 //check a potion is there
		 assert(dungeon.checkPotion(1, 1) == true);
		 
		 //check a potion is not ontop of the player
		 assert(dungeon.checkPotion(0, 1) == false);
		 
		 //move player to the potion
		 player.moveRight();
		//if no enemy then pick up whatever is on the floor
		player.interact();
		//potion is correctly in players invenotry
		
		assert(player.getPotion().getClass() ==  Potion.class);
		assert(player.getPotion().equals(potion));
		
		
		 
		//check the player is invulnerable  after picking up pothion
		assert(potion.checkPotionFinished() == false);
		assert(player.checkInvulnerability() == true);
		//check a potion is there
		assert(dungeon.checkPotion(1, 1) == false);
		//check a potion is not ontop of the player
		assert(dungeon.checkPotion(0, 1) == false);
		
		//on pick up it has 11 ticks lefts and uses 1 instantly
		
		
		//check lasts for 10 ticks
		player.moveDown();
		dungeon.game();
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(potion.checkPotionFinished() == true);
		
		//after the 10th move it uses all of its charges and therefore the potion is conplete
		//this also means that the acceptance criteria is met that the potion lasts for a cert
		//-ain amount of time.

	 }
	 
	 @Test
	 public void TestPotionDieBeforePickingUpPotion() {
		Entity entity = potion;
        dungeon.setPlayer(player);
        dungeon.addEntity(entity);
        enemy = new Enemy(dungeon, 1,1);
        entity = enemy;
        dungeon.addEntity(enemy);
	        
		 System.out.println("~~~~~~Running test for TestPotionDieBeforePickingUpPotion~~~~~~");
		 
		 assert (player.equals(dungeon.getPlayer()));
		 //check the player is invulnerable 
		 assert(potion.checkPotionFinished() == false);
		 assert(player.checkInvulnerability() == false);
		 
		 //check a potion is there
		 assert(dungeon.checkPotion(1, 1) == true);
		 
		 //check a potion is not ontop of the player
		 assert(dungeon.checkPotion(0, 1) == false);
		 
		 //move player to the potion
		 player.moveRight();
		 // run the game with its rules for 1 tick
		 
		 // this basically asserts that the game is over and everything should exit
		 assert(dungeon.game() == false);
		 //game returning false means you died

	 }
	 
	 @Test
	 public void TestInvinceablePlayerCollidingWithPlayerKillsEnemy() {
		 Entity entity = potion;
         dungeon.setPlayer(player);
         dungeon.addEntity(entity);
	        
        
		 System.out.println("~~~~~~Running test for TestInvinceablePlayerCollidingWithPlayerKillsEnemy~~~~~~");
		 
		 assert (player.equals(dungeon.getPlayer()));
		 enemy = new Enemy(dungeon, 2,1);
         entity = enemy;
         dungeon.addEntity(enemy);
		 //move player to the potion
		 player.moveRight();
		 player.interact();
		 
	        
//		 System.out.println(enemy.getX() + " " + enemy.getY());
//		 System.out.println(player.getX() + " " + player.getY());
	     
         
		 //player is at 1,1 and enemy is at 2,1
		 player.moveRight();
         
         //since the player and the enemy are now on the same spot and
         assert(player.checkInvulnerability() == true);
         //player is invlulnerable the enemy shoudl die
         assert(dungeon.game() == true);
         //game doesnt end since u are invulnerable
         //the enemy is now dead and deleted from the dungeon
         assert (dungeon.checkEnemyGoal() == true);
         assert(dungeon.checkEnemy(1, 1) == false);
         
	 }
	 
	 @Test
	 public void TestPickingUpPotionWhilstHavingAPotionResetCounter() {
		Entity entity = potion;
        dungeon.setPlayer(player);
        dungeon.addEntity(entity);
        
        Potion newPotion = new Potion( 2,1);
        Entity newEntity = newPotion;
        dungeon.addEntity(newEntity);
        
		System.out.println("~~~~~~Running test for TestPickingUpPotionWhilstHavingAPotionResetCounter~~~~~~");
 		 
		//move player to the potion
		player.moveRight();
		// run the game with its rules for 1 tick
		// 10 ticks left
		assert(dungeon.game() == true);
        //make the player stay there in the same spot
        player.useSword();
        //9 ticks left
		assert(dungeon.game() == true);
		//make the player stay there in the same spot
        player.useSword();
        //8 ticks left
		assert(dungeon.game() == true);
		//make the player stay there in the same spot
        player.useSword();
        //7 ticks left
		assert(dungeon.game() == true);
		//make the player stay there in the same spot
        player.useSword();
        //6 ticks left
		assert(dungeon.game() == true);
		 
		//potion is still usable for more ticks and player still invinceable
		assert(potion.checkPotionFinished() == false);
		assert(player.checkInvulnerability() == true);
		 
		player.moveRight();
		//picks up a new potion
		assert(dungeon.game() == true);
		//newPotion is correctly in players invenotry
		assert(player.getPotion().getClass() ==  Potion.class);
		assert(player.getPotion().equals(newPotion));
		
		//potion we are checking for is the newPotion we added  
		
		
		//check lasts for 10 ticks
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == false);
		player.moveDown();
		assert(dungeon.game() == true);
		assert(newPotion.checkPotionFinished() == true);
	 }
	 
	 @Test
	 public void TestEnemiesRunningAway() {
		Entity entity = potion;
        dungeon.setPlayer(player);
        dungeon.addEntity(entity);
        enemy = new Enemy(dungeon, 5,5);
        entity = enemy;
        dungeon.addEntity(entity);
		System.out.println("~~~~~~Running test for TestEnemiesRunningAway~~~~~~");
		
		double distance1 = Math.sqrt((player.getX()-enemy.getX())*((player.getX()-enemy.getX()))+ ((player.getY()-enemy.getY())*((player.getY()-enemy.getY()))));
		System.out.println(distance1);

		player.useSword();
		dungeon.game();
		player.useSword();
		dungeon.game();
		player.useSword();
		dungeon.game();
		double distance2 = Math.sqrt((player.getX()-enemy.getX())*((player.getX()-enemy.getX()))+ ((player.getY()-enemy.getY())*((player.getY()-enemy.getY()))));
		
		System.out.println(distance2);
		
		//check if enemy moved closer and therefore there is a smaller distance at distance 2
		assert(distance1 > distance2);
		
		//pick up potion
		player.moveRight();
		
		System.out.println("move ontop of potion");
		dungeon.game();
		
		//dummy movement to show enemy running
		//if i was to move the player right it would make no difference to distance 
		// as the enemy and player move the same way (this took me a while to realise)
		player.useSword();
		dungeon.game();
		player.useSword();
		dungeon.game();
		player.useSword();
		dungeon.game();
		player.useSword();
		dungeon.game();
		
		
		//check if enemy moved further away and therefore distance 3 is larger than distance 2
		double distance3 = Math.sqrt((player.getX()-enemy.getX())*((player.getX()-enemy.getX()))+ ((player.getY()-enemy.getY())*((player.getY()-enemy.getY()))));
		System.out.println(distance3);
		
		assert(distance3 > distance2);
		
		
		 
	 }

	
	@AfterClass
	protected void tearDown() throws Exception {
		
	}
	
	

}
