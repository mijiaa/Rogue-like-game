package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.World;

public class ExtendedWorld extends World {

	public ExtendedWorld(Display display) {
		super(display);
		// TODO Auto-generated constructor stub
	}
	
	public void run() {
		if(player == null)
			throw new IllegalStateException();
		
		while (stillRunning()) {
			GameMap playersMap = actorLocations.locationOf(player).map();
			playersMap.draw(display);
			
			//for (Actor actor : actorLocations) {
			for (Actor actor : actorLocations) {
				if((playersMap.locationOf(actor).map()).equals(playersMap)) {
					processActorTurn(actor);
				}
				//processActorTurn(actor);
			}
			
			
		}
		display.println(endGameMessage());
	}
}
