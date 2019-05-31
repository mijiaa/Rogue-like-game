package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * Class representing a RocketPad
 */
public class RocketPad extends Ground {
	private static GameMap earthMapObj;
	private static GameMap moonMapObj;
	private static ActorLocations actorLocationObj;
	/**
     * Set the instance of earthMap and moonMap
     * @param earthMap GameMap object representing the earth map
	 * @param moonMap GameMap object representing the moon map
	 * @param actorLocat ActorLocations object representing all the locations of actor
     */
	public void setMap(GameMap earthMap,GameMap moonMap,ActorLocations actorLocat) {
		earthMapObj = earthMap;
		moonMapObj = moonMap;
		actorLocationObj = actorLocat;
	}
	
	/**
	 * Method to get the earthMap object
	 * @return GameMap object representing the earthMap
	 */
	public GameMap getEarthMap() {
		return earthMapObj;
	}
	
	/**
	 * Method to get the moonMap object
	 * @return GameMap object representing the moonMap
	 */
	public GameMap getMoonMap() {
		return moonMapObj;
	}
	
	private int canBuildRocket = 0;
	/**
	 * Constructor.
	 *{@inheritDoc}
	 */
	public RocketPad() {
		super('X');
	}

	/**
	 *{@inheritDoc}
	 * Do not let an actor enter
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 *This method check if player has Rocket Body and RocketEngine
	 *and add new BuildRocket action to player
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){

		canBuildRocket = 0; //indicator to determine whether the player fulfil the requirements to build rocket
		List<Item> items_list = actor.getInventory(); //accessing the player's item inventory
		
		//iterate over player's item inventory to check whether the player contains Rocket Engine and Rocket Body
		for (Item item : items_list){
            if(item.getDisplayChar() == '<') { //Rocket Engine
            	canBuildRocket += 1;
            }
            if(item.getDisplayChar() == '>') { //Rocket Body
            	canBuildRocket += 1;
            }
        }

		//if player has both Rocket Body and Rocket Engine,add new actions of building rocket to player
		if(canBuildRocket >= 2) {
			Actions buildRocketActions = new Actions();
			Action buildRocketAction = new BuildRocket(earthMapObj,moonMapObj,actorLocationObj);
			buildRocketActions.add(buildRocketAction);
			return buildRocketActions;
		}
		return new Actions();
	}
	
}
