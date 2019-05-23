package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;

import java.util.List;

public class FlyToMoon extends Action {

	SpaceSuit ss;
	Boolean moon = false;
	ExtendedPlayer player;
	public FlyToMoon() {}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		List<Item> items  = actor.getInventory();

		for (Item item: items){
			if (item.getDisplayChar() == 'S'){
				ss = (SpaceSuit) item;
			}
		}
		if (items.contains(ss)) {
            fly();
            player=  (ExtendedPlayer) actor;
            player.atMoon(moon);
			new MoonBase(actor, map);

			return null;
		}
		return "---------- You need a Space Suit ! ------------";
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

	public void fly(){this.moon =true ;}
}
