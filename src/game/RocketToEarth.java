package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Item;

public class RocketToEarth extends Item{

	public RocketToEarth(String name, char displayChar) {
		super(name, displayChar);
		// TODO Auto-generated constructor stub
	}
	
	public Actions getAllowableActions() {
		Actions flyActions = new Actions();
		Action flyAction = new FlyToEarth();
		flyActions.add(flyAction);
		return flyActions;
	}
}
