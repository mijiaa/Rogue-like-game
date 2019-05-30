package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Grunt extends Actor {
	
	/**
	 * Constructor
	 * @param name Name to call Grunt in the UI
	 * @param player Character to represent Grunt in the UI
	 * 
	 * Grunt have 50 hitPoints and are always represented with a g
	 * Grunt has a FollowBehaviour that follows player around the game map.
	 */
	public Grunt(String name, Actor player) {
		super(name, 'g',5, 50);
		addBehaviour(new FollowBehaviour(player));

	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {

		//remove random DropItemAction
		actions.remove(actions.get(0));

		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if(action != null)
				return action;
		}
		return super.playTurn(actions,  map,  display);
	}

}
