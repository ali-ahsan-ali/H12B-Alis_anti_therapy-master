package unsw.dungeon;

/**
 * the interface to implement a subject for the observer aptten used
 * @author ali
 *
 */
public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers(boolean invul);
}
