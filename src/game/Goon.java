package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.*;

public class Goon extends Actor {
	Display printResult = new Display();
	
	// Goon have 50 hitpoints and are always represented with a w
	public Goon(String name, Actor player) {
		super(name, 'w', 6, 50);		
		addBehaviour(new FollowBehaviour(player));
//		Item key = new Key("key", 'K');
//		addItemToInventory(key);
	}
			
	private List<ActionFactory> actionFactories = new ArrayList<ActionFactory>();

	private void addBehaviour(ActionFactory behaviour) {
		actionFactories.add(behaviour);
	}

	//override InstrinsicWeapon method inside Actor superclass to modify the damage done by Goon
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(10, "punches");
	}
	
	@Override
	public Action playTurn(Actions actions, GameMap map, Display display) {
		
		//generate a random number in the range of 0-9
		Random rand = new Random();
		int prob = rand.nextInt(10);
		
		/*
		//code to test the insult functionality
		int prob = 0;
		printResult.println("test");
		printResult.println(Integer.toString(prob));
		printResult.println("test");
		*/

		//print insult string if the random number generated is equivalent to 0(10 % chance of insulting player)
		if(prob == 0) {
			printResult.println(Insult());
		}
		
		for (ActionFactory factory : actionFactories) {
			Action action = factory.getAction(this, map);
			System.out.println(action);
			if(action != null)
				return action;
		}
		return super.playTurn(actions,  map,  display);
			
	}
	
	//method to return string to insult player
	public String Insult() {
		return name + ":aaaaaaaaa";
	}
}

