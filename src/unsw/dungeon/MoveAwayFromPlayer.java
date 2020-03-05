package unsw.dungeon;

import java.awt.Point;

/**
 * This implements emey action and moves the player in a certain direction away form the player
 * @author ali
 *
 */
public class MoveAwayFromPlayer implements EnemyAction{
	
	private Dungeon dungeon;
	private Enemy enemy;
	/**
	 * creates a strategy apttern based onthe dungeon and enemy implemented
	 * @param dungeon
	 * @param enemy
	 */
	public MoveAwayFromPlayer(Dungeon dungeon, Enemy enemy) {
		this.dungeon = dungeon;
		this.enemy = enemy;
	}

	@Override
	/**
	 * ovverising implementation of the enemy action interfact and moves away from whichever player is clsoest
	 * using distance in point format
	 */
	public void Move() {
		System.out.println("interface move away from player");

		Point playerLoc = new Point(this.dungeon.getPlayer().getX(), this.dungeon.getPlayer().getY());
		Point EnemyLoc = new Point(this.enemy.getX(), this.enemy.getY());
		Point SpaceBetween = new Point(playerLoc.x - EnemyLoc.x, playerLoc.y - EnemyLoc.y);
		if (dungeon.getPlayer2() != null) {
			Point playerLoc2 = new Point(dungeon.getPlayer2().getX(), dungeon.getPlayer2().getY());
			Point SpaceBetween2 = new Point(playerLoc2.x - EnemyLoc.x, playerLoc2.y - EnemyLoc.y);
			System.out.println("AWAY"   + playerLoc2);
			System.out.println("AWAY"   + SpaceBetween2);

			if (Math.abs(SpaceBetween2.x) + Math.abs(SpaceBetween2.y) < 
				Math.abs(SpaceBetween.x) + Math.abs(SpaceBetween2.y)) {
				SpaceBetween = SpaceBetween2;
			}
		}
		
		System.out.println("AWAY"   + SpaceBetween);
		
		if (Math.abs(SpaceBetween.x) < Math.abs(SpaceBetween.y)) {
			//System.out.println("1");

			if (SpaceBetween.x > 0) {
				this.enemy.moveLeft();
				//System.out.println("3");

			}else {
				//System.out.println("4");
				this.enemy.moveRight();
			}
			
			
		}else if (SpaceBetween.x == 0 && SpaceBetween.y == 0){
			System.out.print("ENEMY DEAD DELETE IT");
			dungeon.delEnemy(enemy.getX(), enemy.getY());
		}else {
			//System.out.println("2");
			if (SpaceBetween.y > 0) {
				this.enemy.moveUp();
			}else {
				this.enemy.moveDown();
			}
		}
		
	}
	
}