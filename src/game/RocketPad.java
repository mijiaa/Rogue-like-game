package game;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * Class representing a RocketPad
 */
public class RocketPad extends Ground {
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
	 *This method check if player has Rockey Body and RocketEngine
	 *and add new BuildRocket action to player
	 */
	@Override
	public Actions allowableActions(Actor actor, Location location, String direction){

		canBuildRocket = 0;
		List<Item> item = actor.getInventory();
		
		for (int i =0; i<item.size(); i++){
            
            if(item.get(i).getDisplayChar() == '<') { //Rocket Engine
            	canBuildRocket += 1;
            }
            
            if(item.get(i).getDisplayChar() == '>') { //Rocket Body
            	canBuildRocket += 1;
            }
        }
		
		if(canBuildRocket >= 2) {
			Actions buildRocketActions = new Actions();
			//buildRocketActions.clear();
			Action buildRocketAction = new BuildRocket();
			buildRocketActions.add(buildRocketAction );
			return buildRocketActions;
			
		}
		
		return new Actions();
	}
	
}
