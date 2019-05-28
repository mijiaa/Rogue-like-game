package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Rocket type item
 * Inherited from Item class to have its properties and methods
 */
public class Rocket extends Item {

	private static GameMap earthMapObj;
	private static GameMap moonMapObj;
	
	
    public Rocket(String name, char displayChar) {
        super(name, displayChar);
    }
    
    public void setMap(GameMap earthMap,GameMap moonMap) {

		earthMapObj = earthMap;
		moonMapObj = moonMap;
	}
    
    
    public Actions getAllowableActions() {
    	
    	if(name.equalsIgnoreCase("Rocket")) {
    		//allowableActions.clear(); 

    		Actions flyActions = new Actions();
    		//Action flyAction = new FlyToMoon();
    		Action flyAction = new FlyAction(earthMapObj,moonMapObj);
    		flyActions.add(flyAction);
    		return flyActions;
    	}
    	else {
    		return allowableActions;
    	}
    }
}
