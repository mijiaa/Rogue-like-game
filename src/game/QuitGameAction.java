package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.GameMap;

/**
 * New Action that lets player quit game
 */
public class QuitGameAction extends Action {
	private ActorLocations actorLocations;
	
	public QuitGameAction() {}
	
	/**
	 * This method set the instance of actorLocations
	 * @param actorLocat ActorLocations objects
	 */
	public void addActorLocations(ActorLocations actorLocat) {
		actorLocations = actorLocat;
	}

	
	/**
	 * Perform the Quit Game Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		//removing all actors on the map
		for (Actor actorOnMap : actorLocations) {
			map.removeActor(actorOnMap);
		}
		
		return menuDescription(actor);
	}
	
	/**
	 * Returns a descriptive string
	 * @param actor The actor performing the action.
	 * @return the text we put on the menu
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " quit game";
	}
	
	/**
	 *{@inheritDoc}
	 * @return hotKey as 1 as a string on menu
	 */
	@Override
	public String hotKey() {

		return "1";
	}
}
