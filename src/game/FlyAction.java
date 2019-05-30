package game;


import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.Item;
import java.util.List;

/**
 * New Action class to travel between moon and earth
 */
public class FlyAction extends Action{
	private static GameMap earthMapObj;
	private static GameMap moonMapObj;
	SpaceSuit ss;
	Boolean s_suit = false;
	Boolean win = false;
	ExtendedPlayer player;

	/**
	 *
	 * @param earthMap represents earth map object
	 * @param moonMap represents moon map object
	 */
	public FlyAction(GameMap earthMap,GameMap moonMap) {
		earthMapObj = earthMap;
		moonMapObj = moonMap;
	}
	
	/**
	 * Perform the Action.
	 *
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of what happened that can be displayed to the user.
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Boolean atMoonMap = false;
		
		//iterating over the player's inventory to check for the existence of items
		List<Item> items  = actor.getInventory();
		for (Item item: items){

			if (item.getDisplayChar() == 'S'){
				ss = (SpaceSuit) item;
			}
		}

		for (Item item:items){
			if (item.getDisplayChar() == '%'){
				win = true;
				break;
			}
		}

		if (items.contains(ss)) {
			fly();
			player = (ExtendedPlayer) actor;
			player.atMoon(s_suit, earthMapObj,moonMapObj);
		}
		
		//if the player is currently on earth and contains space suit
		if(map == earthMapObj && s_suit) {
			Location moonLocationRef = moonMapObj.at(22, 10);
			moonMapObj.moveActor(actor,moonLocationRef);
			atMoonMap = true;
			player.atMoon(atMoonMap,earthMapObj,moonMapObj);
		}
		//if the player is currently on moon,contains space suit and the final boss is not defeated
		else if (map == moonMapObj && s_suit & !win) {
			Location earthLocationRef = earthMapObj.at(22, 10);
			earthMapObj.moveActor(actor,earthLocationRef);

			player.atMoon(atMoonMap,earthMapObj,moonMapObj);
		}
		//if the player is currently on moon and the final boss is defeated
		else if(map == moonMapObj && win) {

			map.removeActor(actor);

		}
		
		//if the player does not have a space suit
		else {
			return "---------- You need a Space Suit ! ------------";
		}

		return null;

	}
	

	/**
	 *{@inheritDoc}
	 */
	@Override
	public String menuDescription(Actor actor) {
		// TODO Auto-generated method stub
		return "Fly";
	}
	
	/**
	 *{@inheritDoc}
	 * @return hotKey as 9 as a string on menu
	 */
	@Override
	public String hotKey() {
		// TODO Auto-generated method stub
		return "9";
	}
	
	/**
	 * Method to invoke to change the boolean value of the existence of space suit
	 */
	public void fly(){this.s_suit =true ;}

}
