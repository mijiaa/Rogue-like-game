package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Rocket type item
 * Inherited from Item class to have its properties and methods
 */
public class Rocket extends Item {

	private static GameMap earthMapObj;
	private static GameMap moonMapObj;
	private static ActorLocations actorLocationObj;
	
	/**
	 * Constructor
	 * @param name The name of the item
	 * @param displayChar Character to represent the item for display purpose on map
	 */
    public Rocket(String name, char displayChar) {
        super(name, displayChar);
    }
    
    /**
     * Set the instance of earthMap and moonMap
     * @param earthMap GameMap object representing the earthMap
     * @param moonMap GameMap object representing the moonMap
     */
    public void setMap(GameMap earthMap,GameMap moonMap,ActorLocations actorLocat) {

		earthMapObj = earthMap;
		moonMapObj = moonMap;
		actorLocationObj = actorLocat;
	}
    
    /**
     * Adding Fly action to player to allow moving between maps
     */
    @Override
    public Actions getAllowableActions() {
    	
    	if(name.equalsIgnoreCase("Rocket")) {
    		Actions flyActions = new Actions();
    		Action flyAction = new FlyAction(earthMapObj,moonMapObj,actorLocationObj);
    		flyActions.add(flyAction);
    		return flyActions;
    	}
    	else {
    		return allowableActions;
    	}
    }
}
