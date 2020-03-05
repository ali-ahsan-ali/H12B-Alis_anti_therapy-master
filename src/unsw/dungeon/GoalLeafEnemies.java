package unsw.dungeon;

/**
 * Goal for the enemy goal
 * to be added as a leaf within a composite patten
 * @author ali
 *
 */
public class GoalLeafEnemies implements Component {
	private boolean complete;
	
	/**
	 * creates a goal leaf for the enemy goal and sets it based on the completed status
	 * @param complete
	 */
	public GoalLeafEnemies(boolean complete) {
		this.complete = complete;
	}


	@Override
	/**
	 * checks to see if the goal is completed
	 * @return boolean - based on completedmess 
	 */
	public boolean isComplete() {
		return this.complete;
	}
	
	@Override
	/**
	 * returns the type of goal and if its compelted
	 * @return String- ovveride for easier debugging 
	 */
	public String toString() {
		return "Enemies"+this.complete;
	}

	@Override
	/**
	 * sets the enemy goals as completed
	 */
	public void setEnemiesComplete() {
		this.complete = true;
		
	}

	
	@Override
	/**
	 * sets the treasure goals to be completed, in this case does nothing
	 * as this si the leaf for the enemy goal
	 */
	public void setTreasureComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * sets the boulder goals to be completed, in this case does nothing
	 * as this si the leaf for the enemy goal
	 */
	public void setBoulderComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * sets the exit goals to be completed, in this case does nothing
	 * as this si the leaf for the enemy goal
	 */
	public void setExitComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * adds a component but since this si a single leaf it adds nothing
	 */
	public void add(Component g) {
		// TODO Auto-generated method stub
		
	}
}
