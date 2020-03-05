package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    static final int AND = 1;
    static final int OR = 0;

    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");
        
        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");

        JSONObject jsonGoals = json.getJSONObject("goal-condition");
        Composite goals = null;        

//        System.out.println(jsonGoals.getString("goal") + " 4");
        if (jsonGoals.get("goal").equals("AND")) {
//            System.out.println("created and");

       	 	goals = new Composite(1);
        }else if (jsonGoals.get("goal").equals("OR")) {
//            System.out.println("created or");

       	 	goals = new Composite(OR);
        }else {
       	 	goals = new Composite(OR);
	       	if (jsonGoals.get("goal").equals("enemies")) {
	      		GoalLeafEnemies g = new GoalLeafEnemies(false);
	      		goals.add(g);
	      	}else if (jsonGoals.get("goal").equals("treasure")) {
	      		GoalLeafTreasure g = new GoalLeafTreasure(false);
	      		goals.add(g);
	      	}else if (jsonGoals.get("goal").equals("boulders")) {
	      		GoalLeafBoulder g = new GoalLeafBoulder(false);
	      		goals.add(g);
	      	}else if (jsonGoals.get("goal").equals("exit")) {
	      		GoalLeafExit g = new GoalLeafExit(false);               
	      		goals.add(g);
	      	}
       	 	
        }
        
        System.out.println(goals.toString() + " HERE");

        
        while(!jsonGoals.isNull("subgoals")) {
        	JSONArray inside = (JSONArray) jsonGoals.get("subgoals");
        	JSONObject first = inside.getJSONObject(0);
        	JSONObject second = inside.getJSONObject(1);
            if (first.get("goal").equals("enemies")) {
         		GoalLeafEnemies g = new GoalLeafEnemies(false);
         		goals.add(g);
         	}else if (first.get("goal").equals("treasure")) {
         		GoalLeafTreasure g = new GoalLeafTreasure(false);
         		goals.add(g);
         	}else if (first.get("goal").equals("boulder")) {
         		GoalLeafBoulder g = new GoalLeafBoulder(false);
         		goals.add(g);
         	}else if (first.get("goal").equals("exit")) {
         		GoalLeafExit g = new GoalLeafExit(false);               
         		goals.add(g);
         	}
        	
        	if (second.get("goal").equals("enemies")) {
         		GoalLeafEnemies g = new GoalLeafEnemies(false);
         		goals.add(g);
         	}else if (second.get("goal").equals("treasure")) {
         		GoalLeafTreasure g = new GoalLeafTreasure(false);
         		goals.add(g);
         	}else if (second.get("goal").equals("boulder")) {
         		GoalLeafBoulder g = new GoalLeafBoulder(false);
         		goals.add(g);
         	}else if (second.get("goal").equals("exit")) {
         		GoalLeafExit g = new GoalLeafExit(false);
         		goals.add(g);
         	}else if (second.get("goal").equals("OR")) {
         		Composite created = new Composite(OR);
         		goals.add(created);
         	}else if (second.get("goal").equals("AND")) {
         		Composite created = new Composite(AND);
         		goals.add(created);
         	}
            jsonGoals = inside.getJSONObject(1);
        }
        System.out.println(goals.toString());
              
        
        dungeon.setGoal(goals);
        
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            if (dungeon.getPlayer() == null) {
                dungeon.setPlayer(player);
            }else {
            	dungeon.setPlayer2(player);
            }
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        case "exit":
        	Exit exit = new Exit(x, y);
        	onLoad(exit);
        	entity = exit;
        	break;
        case "treasure":
        	Treasure treasure = new Treasure(x, y);
        	onLoad(treasure);
        	entity = treasure;
        	break;
        case "door":
        	int id = json.getInt("id");
        	Door door = new Door(x, y, id);
        	onLoad(door);
        	entity = door;
        	break;
        case "boulder":
        	Boulder boulder = new Boulder(dungeon,x, y);
        	onLoad(boulder);
        	entity = boulder;
        	break;
        case "switch":
        	FloorSwitch floorswitch = new FloorSwitch(dungeon, x, y);
        	onLoad(floorswitch);
        	entity = floorswitch;
        	break;
        case "enemy":
        	Enemy enemy = new Enemy(dungeon, x, y);
        	onLoad(enemy);
        	entity = enemy;
        	break;
        case "invincibility":
        	Potion potion = new Potion( x, y);
        	onLoad(potion);
        	entity = potion;
        	break;
        case "sword":
        	Sword sword = new Sword(x, y);
        	onLoad(sword);
        	entity = sword;
        	break;
        case "portal":
        	int id1 = json.getInt("id");
        	System.out.println(id1);
        	Portal portal = new Portal(id1,x, y);
        	onLoad(portal);
        	entity = portal;
        	break;
        case "key":
        	int id2 = json.getInt("id");
        	System.out.println(id2);
        	Key key = new Key(x, y, id2);
        	onLoad(key);
        	entity = key;
        	break;
        // TODO Handle other possible entities
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);
    
    public abstract void onLoad(Key key);
  
    public abstract void onLoad(Sword sword);

    public abstract void onLoad(Wall wall);

	public abstract void onLoad(Exit exit);

	public abstract void onLoad(Treasure treasure);
	
	public abstract void onLoad(Door door);
	
    public abstract void onLoad(Boulder boulder);
	
    public abstract void onLoad(FloorSwitch floorswitch) ;

    public abstract void onLoad(Enemy enemy);

    public abstract void onLoad(Potion potion);

    public abstract void onLoad(Portal portal);


    


		

    // TODO Create additional abstract methods for the other entities

}
