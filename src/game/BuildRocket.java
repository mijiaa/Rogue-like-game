
package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Special Action used when the player doesn't want to do anything this turn.
 */
public class BuildRocket extends Action {

	public BuildRocket() {
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> inventoryItems = actor.getInventory();
		//actor.removeItemFromInventory(item);
		
		for (int i =0; i<inventoryItems.size(); i++){
            //System.out.println(item.get(i).getDisplayChar());
            if(inventoryItems.get(i).getDisplayChar() == '-') { //Rocket Plan
            	//inventoryItems.remove(i);
            	actor.removeItemFromInventory(inventoryItems.get(i));
            }
            
            if(inventoryItems.get(i).getDisplayChar() == '<') { //Rocket Engine
            	//inventoryItems.remove(i);
            	actor.removeItemFromInventory(inventoryItems.get(i));
            }
            
            if(inventoryItems.get(i).getDisplayChar() == '>') { //Rocket Body
            	//inventoryItems.remove(i);
            	actor.removeItemFromInventory(inventoryItems.get(i));
            }
        }
        
        
		
		//return menuDescription(actor);
		return "Rocket built";
	}

	@Override
	public String menuDescription(Actor actor) {
		return "Build Rocket";
	}
	
	@Override
	public String hotKey() {
		return "*";
	}
}