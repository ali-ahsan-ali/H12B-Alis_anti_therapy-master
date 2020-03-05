package unsw.dungeon;


/**
 * 
 * @author ali
 * Composite class to hold goals.
 * It is a tree format with a left and right component i.e.
 * Left (leaf) Right (Composite)
 * Goals all goals for the game and is of composite class and therefore recursively checking
 * if all goals are met
 */
public class Composite implements Component {

	private Component goalLeft;
	private Component goalRight;
    static final int OR = 0;
    static final int AND = 1;
	private int composition;
	
	/**
	 * 
	 * @param composition - AND/OR condition for the left and right goal
	 * @return nothing
	 * Constructor for the class
	 */
	public Composite(int composition) {
		this.goalLeft = null;
		this.goalRight = null;
		this.composition = composition;
	}
	
	
	
	/**
	 * 
	 * @return goalLeft - the left goal for the class
	 */
	public Component getGoalLeft() {
		return goalLeft;
	}



	/**
	 * 
	 * @return goalRight - the right goal for the class
	 */
	public Component getGoalRight() {
		return goalRight;
	}

	
	@Override
	/**
	 * @return nothing
	 * This is the ovveriding function for the Component interface
	 * This is to check whether the current composition class or leaf class is complete
	 * and checks to see whther the goal has been complete
	 * 
	 * Changes depnding on AND/OR composition
	 */
	public boolean isComplete() {
		if (composition == AND) {
			//if its false returns false
			if (this.goalRight == null && this.goalLeft == null) {
				return false;
			}if (this.goalRight != null && this.goalLeft == null) {
				return this.goalRight.isComplete();
			}if (this.goalRight == null && this.goalLeft != null) {
				return this.goalLeft.isComplete();
			}else {
				return (goalLeft.isComplete() && goalRight.isComplete());
			}
		}if (composition == OR) {
			if (this.goalRight == null && this.goalLeft == null) {
				return false;
			}if (this.goalRight != null && this.goalLeft == null) {
				return this.goalRight.isComplete();
			}if (this.goalRight == null && this.goalLeft != null) {
				return this.goalLeft.isComplete();
			}else {
				return (goalLeft.isComplete() || goalRight.isComplete());
			}
		}
		return false;
	}

	
	/**
	 * @return - nothing
	 * Sets the exit goals within the composition complete 
	 */
	public void setExitComplete() {
		if (this.goalLeft != null) {
			this.goalLeft.setExitComplete();
		}if (this.goalRight != null) {
			this.goalLeft.setExitComplete();
		}
	}
	/**
	 * @return - nothing
	 * Sets the treasure goals within the composition complete 
	 */
	public void setTreasureComplete() {
		if (this.goalLeft != null) {
			this.goalLeft.setTreasureComplete();
		}if (this.goalRight != null) {
			this.goalLeft.setTreasureComplete();
		}
	}
	/**
	 * 
	 * @return - composition of the class (AND/OR)
	 */
	public int getComposition() {
		return composition;
	}



	/**
	 * 
	 * @param composition
	 * Sets the composition for the class
	 */
	public void setComposition(int composition) {
		this.composition = composition;
	}



	/**
	 * 
	 * @param goalLeft
	 * sets the left goal to be a composition of a leaf
	 */
	public void setGoalLeft(Component goalLeft) {
		this.goalLeft = goalLeft;
	}



	/**
	 * 
	 * @param goalRight
	 * sets the right goal to be a composition of a leaf
	 */
	public void setGoalRight(Component goalRight) {
		this.goalRight = goalRight;
	}



	/**
	 * @return - nothing
	 * Sets the boulder goals within the composition complete 
	 */
	public void setBoulderComplete() {
		if (this.goalLeft != null) {
			this.goalLeft.setBoulderComplete();
		}if (this.goalRight != null) {
			this.goalLeft.setBoulderComplete();
		}
	}
	
	/**
	 * @return - nothing
	 * Sets the enemies goals within the composition complete 
	 */
	public void setEnemiesComplete() {
		if (this.goalLeft != null) {
			this.goalLeft.setEnemiesComplete();
		}if (this.goalRight != null) {
			this.goalLeft.setEnemiesComplete();
		}
	}
	
	/**
	 * @param Component g - add a leaf or a composite class
	 * adds to the left and then to the right if elft is not empty
	 * this way it creates a tree which can be recusrively added to or recurisvely checked
	 */
	public void add(Component g) {
//		System.out.println(g.toString() + " " + this.goalLeft  + " " + this.goalRight);
		if (this.goalLeft == null) {
			this.goalLeft = g;
		}else if (this.goalRight == null) {
			this.goalRight = g;
		}else {
			this.goalRight.add(g);
		}
//		System.out.println(this.goalLeft  + " " + this.goalRight);
	}

	@Override
	/**
	 * @return String - ovveride the toString to make it eaasier to debug
	 * Prints the composition type then the left goal then the right goal
	 */
	public String toString() {
		return this.composition + " " + this.goalLeft + " " + this.goalRight + "\n";
		
	}

}

