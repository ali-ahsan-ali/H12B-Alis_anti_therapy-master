package unsw.dungeon;

public interface Component {
	public boolean isComplete();
	public void setEnemiesComplete();
	public void setTreasureComplete();
	public void setBoulderComplete();
	public void setExitComplete();
	public void add(Component g);
}
