package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 *New player class that extend from player for further modification purpose
 */
public class ExtendedPlayer extends Player {

    private ArrayList<Item> oxy_tanks = new ArrayList<>();
    private int oxygen_point =10;
    private Boolean isAtMoon = false;
    private static GameMap moonMapObj;
    private static GameMap earthMapObj;
    private static ActorLocations actorLocationObj;
    private static final int MIN_OXYGEN_POINTS = 0;
    private static final int MAX_OXYGEN_POINTS = 10;
    private int counter = 0;
    private Display display;
    /**
     * Constructor.
     *
     * @param name Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param priority How early in the turn the player can act
     * @param hitPoints Player's starting number of hitpoints
     */
    public ExtendedPlayer(String name, char displayChar, int priority, int hitPoints) {
        super(name, displayChar, priority, hitPoints);
    }


    /**
     * {@inheritDoc}
     * This method decrement oxygen life point every turn  at isAtMoon and
     * provide a safety system to transport player automatically
     * them back to the rocket’s location on Earth If the player runs out of oxygen on the isAtMoon
     * @return action
     */
    @Override
    public Action playTurn(Actions actions, GameMap map, Display display) {

        List<Item> items = this.getInventory();
        for (Item item : items) {
            if (item.getDisplayChar() == 'K'){
                // only player can have a skill to UNLOCK
                this.addSkill(ItemSkills.UNLOCK);
            }
        }
    	
    	if (isAtMoon) {
	        for (Item item : items) {
                if (item.hasSkill(ItemSkills.OXYGEN)) {
                    // adding all oxygen tank from player list into another list
                    oxy_tanks.add(item);
                    this.removeItemFromInventory(item);
                }
            }

	        //Start oxygen point depletion when player at moon and print it each turn
	        oxygen_point = oxygen_depletion();
	        display.println("Current oxygen points : " + oxygen_point);

	        if (oxygen_point == -1) {
	        	if (counter > 0) {
	        		//adding FlyAction to allowable actions of player
	        		Action flyToEarthAction = new FlyAction(earthMapObj,moonMapObj,actorLocationObj);
	        		actions.add(flyToEarthAction);
	        		counter = 0;
	        		
	        		//invoke the Fly Action which sends player back to earth if oxygen depleted under allowable amount
	            	for(Action action : actions) {
	            		if(action.menuDescription(this).equalsIgnoreCase("Fly")) {
	            		    // reset oxygen point
	            		    oxygen_point = MAX_OXYGEN_POINTS;
	            			return action;
	    				}
	    			}    			
	        	}
	    		counter += 1; //counter acting as a boolean to indicate the needs of adding fly action to allowable actions of player
	        }
    	}

        return showMenu(actions, display);
    }

    /**
     * This method decrement oxygen points of oxygen tank by 1 each turn
     * @return current oxygen points
     */
    public int oxygen_depletion(){
        int index_to_remove = 0;
        // if there are no more oxygen tanks, return a negative value
        if (oxy_tanks.isEmpty()){
        	return -1;
        }

        //if oxygen points equal or lesser than minimum oxygen points, discard current oxygen tank
        if (oxygen_point <= MIN_OXYGEN_POINTS){
            oxy_tanks.remove(oxy_tanks.get(index_to_remove));

            //and if there are still oxygen tanks left, refuel
            if (!oxy_tanks.isEmpty()){
                return MAX_OXYGEN_POINTS;
            }
        }

        // decrement oxygen points
        return oxygen_point - 1;
    }


    /**
     * This method checks if actor is at isAtMoon and act as setters for earth and isAtMoon map objects
     * @param isAtMoon
     * @param earthMap
     * @param moonMap
     */
    public void atMoon(boolean isAtMoon, GameMap earthMap,GameMap moonMap,ActorLocations actorLocat) {
        this.isAtMoon = isAtMoon;
        this.moonMapObj = moonMap;
        this.earthMapObj = earthMap;
        this.actorLocationObj = actorLocat;
    }


}
