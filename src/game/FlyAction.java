package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;
import java.util.List;

public class FlyAction extends Action{
	private static GameMap earthMapObj;
	private static GameMap moonMapObj;
	SpaceSuit ss;
	Boolean moon = false;
	ExtendedPlayer player;
	
	public FlyAction(GameMap earthMap,GameMap moonMap) {
		earthMapObj = earthMap;
		moonMapObj = moonMap;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//System.out.println(map.moveActor(actor, newLocation););
		if(map == earthMapObj) {
			/*
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
	            Location moonLocationRef = moonMapObj.at(22, 10);
				moonMapObj.moveActor(actor,moonLocationRef);
			}
			else {
				return "---------- You need a Space Suit ! ------------";
			}
			*/
			Location moonLocationRef = moonMapObj.at(22, 10);
			moonMapObj.moveActor(actor,moonLocationRef);
			return "Fly to Moon";
		}
		
		if(map == moonMapObj) {
			Location earthLocationRef = earthMapObj.at(22, 10);
			earthMapObj.moveActor(actor,earthLocationRef);
			return "Fly to Earth";
		}
		
		return menuDescription(actor);
		//Location moonLocationRef = moonMapObj.at(22, 10);
		//Location earthLocationRef = earthMapObj.at(22, 10);
		//moonMapObj.moveActor(actor,moonLocationRef);
	}

	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Fly";
	}

	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "21";
	}

	public void fly(){this.moon =true ;}
}
