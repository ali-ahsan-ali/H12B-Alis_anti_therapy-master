package unsw.dungeon;


import java.awt.Point;

/**
 * This implements emey action and moves the player in a certain direction towards the clsoest player
 * @author ali
 *
 */
//https://stackoverflow.com/questions/2625021/game-enemy-move-towards-player
public class MoveToPlayer implements EnemyAction{

	// vectors and simple going to the coordinate 
	private Dungeon dungeon;
	private Enemy enemy;
	
	/**
	 * creates a strategy apttern based onthe dungeon and enemy implemented
	 * @param dungeon
	 * @param enemy
	 */
	public MoveToPlayer(Dungeon dungeon, Enemy enemy) {
		this.dungeon = dungeon;
		this.enemy = enemy;
	}
	
	@Override
	/**
	 * ovverising implementation of the enemy action interfact and moves to from whichever player is clsoest
	 * using distance in point format
	 */
	public void Move() {
    	//System.out.println("interface move to player");

		Point playerLoc = new Point(dungeon.getPlayer().getX(), dungeon.getPlayer().getY());
		
		Point EnemyLoc = new Point(enemy.getX(), enemy.getY());
		
		Point SpaceBetween = new Point(playerLoc.x - EnemyLoc.x, playerLoc.y - EnemyLoc.y);
		
		System.out.println(dungeon.getPlayer2());
		if (dungeon.getPlayer2() != null) {
			Point playerLoc2 = new Point(dungeon.getPlayer2().getX(), dungeon.getPlayer2().getY());
			Point SpaceBetween2 = new Point(playerLoc2.x - EnemyLoc.x, playerLoc2.y - EnemyLoc.y);	
			if (Math.abs(SpaceBetween2.x) + Math.abs(SpaceBetween2.y) < 
				Math.abs(SpaceBetween.x) + Math.abs(SpaceBetween2.y)) {
				SpaceBetween = SpaceBetween2;
			}
		}
		//System.out.println("TO" + SpaceBetween);
		
		if (Math.abs(SpaceBetween.x) > Math.abs(SpaceBetween.y)) {
			if (SpaceBetween.x > 0) {
				this.enemy.moveRight();
			}else {
				this.enemy.moveLeft();
			}
		}else if (SpaceBetween.x == 0 && SpaceBetween.y == 0){
			//System.out.print("Player DEAD DELETE IT");
		}else {
		
			if (SpaceBetween.y > 0) {
				this.enemy.moveDown();
			}else {
				this.enemy.moveUp();
			}
		}
		
	}
	
}