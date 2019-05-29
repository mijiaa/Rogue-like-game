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
	
	public void addActorLocations(ActorLocations actorLocat) {
		actorLocations = actorLocat;
	}


	@Override
	public String execute(Actor actor, GameMap map) {
		for (Actor actorOnMap : actorLocations) {
			map.removeActor(actorOnMap);
		}
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " quit game";
	}

	@Override
	public String hotKey() {

		return "1";
	}
}
