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
	Boolean s_suit = false;
	ExtendedPlayer player;
	
	public FlyAction(GameMap earthMap,GameMap moonMap) {
		earthMapObj = earthMap;
		moonMapObj = moonMap;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		// TODO Auto-generated method stub
		//System.out.println(map.moveActor(actor, newLocation););


		List<Item> items  = actor.getInventory();
		for (Item item: items){
			if (item.getDisplayChar() == 'S'){
				ss = (SpaceSuit) item;
			}
		}
		if (items.contains(ss)) {
			fly();
			player = (ExtendedPlayer) actor;
			player.atMoon(s_suit, earthMapObj,moonMapObj);

		}

		if(map == earthMapObj && s_suit) {
			Location moonLocationRef = moonMapObj.at(22, 10);
			moonMapObj.moveActor(actor,moonLocationRef);
			System.out.println("earth");
		}
		
		else if (map == moonMapObj && s_suit) {
			Location earthLocationRef = earthMapObj.at(22, 10);
			earthMapObj.moveActor(actor,earthLocationRef);
			System.out.println("moon");
		}
		else {
			return "---------- You need a Space Suit ! ------------";
		}

		return null;
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
		return "12";
	}

	public void fly(){this.s_suit =true ;}
}
