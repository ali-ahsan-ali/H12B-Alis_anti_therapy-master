package unsw.dungeon;

/**
 * Goal for the exit goal
 * to be added as a leaf within a composite patten
 * @author ali
 *
 */
public class GoalLeafExit implements Component {
	private boolean complete;
	
	public GoalLeafExit(boolean complete) {
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
	 * sets the enemy goals to be completed, in this case does nothing
	 * as this si the leaf for the exit goal
	 */
	public void setEnemiesComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * sets the treasure goals to be completed, in this case does nothing
	 * as this si the leaf for the exit goal
	 */
	public void setTreasureComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * sets the boulder goals to be completed, in this case does nothing
	 * as this si the leaf for the exit goal
	 */
	public void setBoulderComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	/**
	 * sets the exit goals to be completed,
	 */
	public void setExitComplete() {
		this.complete = true;
		
	}
	
	@Override
	/**
	 * returns the type of goal and if its compelted
	 * @return String- ovveride for easier debugging 
	 */
	public String toString() {
		return "Exit" + this.complete;
	}

	@Override
	/**
	 * adds a component but since this si a single leaf it adds nothing
	 */
	public void add(Component g) {
		// TODO Auto-generated method stub
		
	}
}
