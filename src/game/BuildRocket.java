
package game;

import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * New action to build a Rocket
 */
public class BuildRocket extends Action {
	private Display printResult = new Display();
	//private Rocket rocket = new Rocket("Rocket", '~');
	private static GameMap earthMapObj;
	private static GameMap moonMapObj;
	
	public BuildRocket(GameMap earthMap,GameMap moonMap) {
		earthMapObj = earthMap;
		moonMapObj = moonMap;
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
		Rocket rocket = new Rocket("Rocket", '~');
		rocket.setMap(earthMapObj, moonMapObj);
		//removing both Rocket Engine and Rocket Body from player's item inventory once the player chosen the build rocket action
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
		//actor.addItemToInventory(rocket);
		//Location placeRocketLocationRef = map.at(22, 10); //getting the location reference of rocketPad
		//map.add(new RocketGround(), placeRocketLocationRef); //replace rocketPad with built Rocket
		//return "ROCKET BUILT, PLAYER ACHIEVED GOAL OF THE GAME";
		
		//actor.addItemToInventory(rocket);
		Location placeRocketLocationRef = map.at(22, 10); //getting the location reference of rocketPad
		map.add(new Floor(), placeRocketLocationRef); //remove rocketPad from map
		map.addItem(rocket, 22, 10); //add rocket item on map at the location of rocketPad
		moonMapObj.addItem(rocket, 22, 10);
		return "ROCKET BUILT!!";
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
		return "20";
	}
}