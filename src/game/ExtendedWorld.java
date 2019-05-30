package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.SkipTurnAction;
import edu.monash.fit2099.engine.World;

/**
 * New World class that extends World in engine to modify and add new functionalities.
 */
public class ExtendedWorld extends World {
	
	/**
	 * Constructor
	 * 
	 * @param display Map display to user on console
	 */
	public ExtendedWorld(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Run the game.
	 *
	 * On each iteration the gameloop does the following:
	 *  - displays the player's map
	 *  - processes the actions of Actor on the same map as player in the game
	 *
	 * We could either only process the actors on the current map, which would make
	 * time stop on the other maps, or we could process all the actors.  We chose to
	 * process actors on the current map
	 *
	 * @throws IllegalStateException if the player doesn't exist 
	 */
	@Override
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			for (Actor actor : actorLocations) {
				if((playersMap.locationOf(actor).map()).equals(playersMap)) {
					processActorTurn(actor);
				}
			}
		}
		display.println(endGameMessage());
	}
	
	/**
	 * Gives an Actor its turn.
	 *
	 * The Actions an Actor can take include:
	 * <ul>
	 *  <li> those conferred by items it is carrying </li>
	 *  <li> movement actions for the current location and terrain </li>
	 *  <li> actions that can be done to Actors in adjacent squares </li>
	 *  <li> actions that can be done using items in the current location </li>
	 *  <li> skipping a turn</li>
	 * </ul>
	 *
	 *	The extra Action that is only added to the player:
	 *<ul>
	 *	<li>quitting the game</li>
	 *</ul>
	 *
	 * @param actor the Actor whose turn it is.
	 */
	@Override
	protected void processActorTurn(Actor actor) {
		Location here = actorLocations.locationOf(actor);
		GameMap map = here.map();

		Actions actions = new Actions();
		for (Item item : actor.getInventory()) {
			actions.add(item.getAllowableActions());
		}

		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (actorLocations.isAnActorAt(destination)) {
				Actor adjacentActor = actorLocations.actorAt(destination);
				actions.add(adjacentActor.getAllowableActions(actor, exit.getName(), map));
			} else {
				Ground adjacentGround = map.groundAt(destination);
				actions.add(adjacentGround.allowableActions(actor, destination, exit.getName()));
				actions.add(adjacentGround.getMoveAction(actor, destination, exit.getName(), exit.getHotKey()));
			}
		}

		for (Item item : here.getItems()) {
			actions.add(item.getAllowableActions());
		}
		
		//extra quitting action that is added to player
		if(actor.toString() == "Player") {
			QuitGameAction quitGame = new QuitGameAction();
			quitGame.addActorLocations(actorLocations);
			actions.add(quitGame);
		}

		actions.add(new SkipTurnAction());
		

		Action action = actor.playTurn(actions, map, display);
		String result = action.execute(actor, map);
		display.println(result);
	}
	
	
}
