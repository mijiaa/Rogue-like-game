package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.GameMap;

public class QuitGameAction extends Action {
	private ActorLocations actorLocations;
	
	public QuitGameAction() {
		//actorLocations = actorLocat;
	}
	
	public void addActorLocations(ActorLocations actorLocat) {
		actorLocations = actorLocat;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//System.out.println(actor.toString());
		//map.removeActor(actor);
		for (Actor actorOnMap : actorLocations) {
			map.removeActor(actorOnMap);
		}
		
		return menuDescription(actor);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		
		return actor + " quit game";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "1";
	}
	
	
}
