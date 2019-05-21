package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class FlyToMoon extends Action {
	
	public FlyToMoon() {
		
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		new MoonBase(actor);
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Fly to Moon";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "12";
	}

}
