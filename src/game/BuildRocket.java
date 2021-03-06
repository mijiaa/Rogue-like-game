
package game;

import java.util.List;

import edu.monash.fit2099.engine.*;

/**
 * New action to build a Rocket
 */
public class BuildRocket extends Action {
	private Display printResult = new Display();
	private static GameMap earthMapObj;
	private static GameMap moonMapObj;
	private static ActorLocations actorLocationObj;
	
	/**
	 * Constructor.
	 * @param earthMap GameMap object representing the earth map
	 * @param moonMap GameMap object representing the moon map
	 * @param actorLocat ActorLocations object representing all the locations of actor
	 */	
	public BuildRocket(GameMap earthMap,GameMap moonMap,ActorLocations actorLocat) {
		earthMapObj = earthMap;
		moonMapObj = moonMap;
		actorLocationObj = actorLocat;
	}
	
	/**
	 * {@inheritDoc}
	 *
	 * Player places both Rocket Body and Rocket Engine on Rocket Pad
	 * and build Rocket at the same spot as RocketPad
	 *
	 * Perform the Action.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		List<Item> inventoryItems = actor.getInventory();
		
		//declaring rocket object and passing both map objects to rocket
		Rocket rocket = new Rocket("Rocket", '~');
		rocket.setMap(earthMapObj, moonMapObj,actorLocationObj); 
		
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
		//getting the location reference of rocketPad
		Location placeRocketLocationRef = map.at(22, 10);
		
		//remove rocketPad from map
		map.add(new Floor(), placeRocketLocationRef);
		
		//add rocket item on earth map at the location of rocketPad
		map.addItem(rocket, 22, 10);
		
		//add rocket item on moon map with same coordinates as the rocket on earth map
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
	 * @return hotKey as 7 as a string on menu
	 */
	@Override
	public String hotKey() {
		return "7";
	}
}