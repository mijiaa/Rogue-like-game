
package game;

import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * New action to build a Rocket
 */
public class BuildRocket extends Action {
	private Display printResult = new Display();
	private Rocket rocket = new Rocket("Rocket", '~');
	public BuildRocket() {
	}

	/**
	 * {@inheritDoc}
	 *
	 * Player places both Rocket Body and Rocket Engine on Rocket Pad
	 * and build and obtain a Rocket
	 *
	 * Perform the Action.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> inventoryItems = actor.getInventory();

		
		for (int i =0; i<inventoryItems.size(); i++){
            if(inventoryItems.get(i).getDisplayChar() == '<') { //Rocket Engine
            	actor.removeItemFromInventory(inventoryItems.get(i));
            	printResult.println("Player placed Rocket Engine on Rocket Pad.");
            }
            if(inventoryItems.get(i).getDisplayChar() == '>') { //Rocket Body
            	actor.removeItemFromInventory(inventoryItems.get(i));
				printResult.println("Player placed Rocket Body on Rocket Pad.");
            }
        }

		actor.addItemToInventory(rocket);
		//return menuDescription(actor);
		return "ROCKET BUILT, PLAYER ACHIEVED GOAL OF THE GAME";
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Build Rocket";
	}

	/**
	 *{@inheritDoc}
	 * @return hotKey as 11 as a string on menu
	 */
	@Override
	public String hotKey() {
		return "11";
	}
}