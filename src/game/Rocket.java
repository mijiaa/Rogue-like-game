package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;

/**
 * Class representing a Rocket type item
 * Inherited from Item class to have its properties and methods
 */
public class Rocket extends Item {
	//new MoonBase(actor,map);
    public Rocket(String name, char displayChar) {
        super(name, displayChar);
    }
    
    public Actions getAllowableActions() {
    	
    	if(name.equalsIgnoreCase("Rocket")) {
    		//allowableActions.clear();    		
    		Actions flyActions = new Actions();
    		Action flyAction = new FlyToMoon();
    		flyActions.add(flyAction);
    		return flyActions;
    	}
    	else {
    		return allowableActions;
    	}
    	   
    }
    
    
}
