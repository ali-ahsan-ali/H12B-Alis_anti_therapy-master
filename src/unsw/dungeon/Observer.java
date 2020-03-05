package unsw.dungeon;

/**
 * Observer interface to be used in enemy to automatically shift between moving to
 * and away from player bassed on their invulnerability
 * @author ali
 *
 */
public interface Observer {
	public void update(boolean potion);
}
