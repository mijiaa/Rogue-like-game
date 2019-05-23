package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.MoveActorAction;
import edu.monash.fit2099.engine.World;

public class FlyToEarth extends Action{

	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//World world = new World(new Display());
		//world.addMap(map);
		//Action action = new MoveActorAction();
		
		return null;
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Fly to Earth";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "13";
	}

}
