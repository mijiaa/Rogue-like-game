package game;



import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;

public class RocketPad extends Ground {
	private int abc = 0;
	private int canBuildRocket = 0;
	
	public RocketPad() {
		super('X');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
	
	public Actions allowableActions(Actor actor, Location location, String direction){
		//System.out.println("actor= " + actor.toString());
		//System.out.println(location.x() + "," + location.y());
		canBuildRocket = 0;
		List<Item> item = actor.getInventory();
		
		for (int i =0; i<item.size(); i++){
            //System.out.println(item.get(i).getDisplayChar());
//            if(item.get(i).getDisplayChar() == '-') { //Rocket Plan
//            	canBuildRocket += 1;
//            }
            
            if(item.get(i).getDisplayChar() == '<') { //Rocket Engine
            	canBuildRocket += 1;
            }
            
            if(item.get(i).getDisplayChar() == '>') { //Rocket Body
            	canBuildRocket += 1;
            }
        }
		
		if(canBuildRocket == 2) {
			Actions buildRocketActions = new Actions();
			//buildRocketActions.clear();
			Action buildRocketAction = new BuildRocket();
			buildRocketActions.add(buildRocketAction );
			
			return buildRocketActions;
			
		}
		
		return new Actions();
		
		//return new Actions();
	}
	
}
