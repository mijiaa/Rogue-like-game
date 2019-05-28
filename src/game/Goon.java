package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

/**
 * Class representing new character Goon
 */

public class Goon extends Actor {

	/**
	 * Constructor.
	 *{@inheritDoc}
	 * @param name Name to call Goon in the UI
	 * @param player Character to represent Goon in the UI
	 *
	 * Goon have 50 hitPoints and are always represented with a w
	 * Goon has a FollowBehaviour that follows player around the game map.
	 */
	public Goon(String name, Actor player) {
		super(name, 'w', 6, 50);
		addBehaviour(new FollowBehaviour(player));
	}

	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	/**
	 *
	 *{@inheritDoc}
	 * Goon has twice the damage of a Grunt.
	 * @return a freshly-instantiated IntrinsicWeapon .
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
		//return new IntrinsicWeapon(10000, "punches"); //test when player is knocked out
	}

	/**
	 *
	 *{@inheritDoc}
	 */
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		Display printResult = new Display();

		//remove DropItemAction from actions
		actions.remove(actions.get(0));

		//generate a random number in the range of 0-9
		Random rand = new Random();
		int prob = rand.nextInt(10);

		//print insult string if the random number generated is equivalent to 0(10 % chance of insulting player)
		if(prob == 0) {
			printResult.println(Insult());
		}

		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			if (action != null)
				return action;
			}

		return super.playTurn(actions,  map,  display);
			
	}

	/***
	 *
	 *method to perform action Insult
	 *@return a string of insult done by Goon to player
	 */
	public String Insult() {
		return name + ": STOP RUNNING AWAY YOU SLOWPOKE ! ";
	}
}

