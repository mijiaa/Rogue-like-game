package game;

import java.util.List;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class RocketGround extends Ground {
	private int canBuildRocket = 0;
	/**
	 * Constructor.
	 *{@inheritDoc}
	 */
	public RocketGround() {
		super('R');
	}

	/**
	 *{@inheritDoc}
	 * Do not let an actor enter
	 * @return false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return true;
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
		List<Item> item = actor.getInventory(); //accessing the player's item inventory
		
		//iterate over player's item inventory to check whether the player contains Rocket Engine and Rocket Body
		for (int i =0; i<item.size(); i++){           
            if(item.get(i).getDisplayChar() == '~') { //Rocket
            	canBuildRocket += 1;
            }           
        }

		//if player has Rocket,add new actions of building rocket to player
		if(canBuildRocket > 0) {
			Actions flyActions = new Actions();
			Action flyAction = new FlyToMoon();
			flyActions.add(flyAction);
			return flyActions;
		}
		return new Actions();
	}
}
